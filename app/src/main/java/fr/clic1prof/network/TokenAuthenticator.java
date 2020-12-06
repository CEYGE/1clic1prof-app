package fr.clic1prof.network;

import java.io.IOException;

import javax.annotation.Nullable;
import javax.inject.Inject;

import fr.clic1prof.api.UserController;
import fr.clic1prof.model.Credentials;
import fr.clic1prof.model.Token;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class TokenAuthenticator implements Authenticator {

    private static final int MAX_RETRY = 3;

    @Inject
    public NetworkProvider provider;

    @Inject
    public SessionManager manager;

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {

        // This is the case for login or register.
        // Do not do anything else then.
        if(!this.manager.isSessionOpened()) return null;

        // Retry limit exceeded. Do not try anymore.
        if(this.countRetries(response) >= MAX_RETRY) return null;

        UserController controller = this.provider.getService(UserController.class);

        Credentials credentials = this.manager.getCredentials();

        Call<Token> call = controller.login(credentials);

        retrofit2.Response<Token> authentication = call.execute();

        // Cannot authenticate.
        if(!authentication.isSuccessful()) return null;

        Token token = authentication.body();

        this.manager.setToken(token);

        return response.request()
                .newBuilder()
                .addHeader("Authorization", token.getFormattedToken())
                .build();
    }

    private int countRetries(Response response) {

        int count = 1;

        while((response = response.priorResponse()) != null) count++;

        return count;
    }
}
