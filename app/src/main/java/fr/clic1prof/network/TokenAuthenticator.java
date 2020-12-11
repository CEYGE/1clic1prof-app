package fr.clic1prof.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import fr.clic1prof.api.UserController;
import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.models.user.Token;
import fr.clic1prof.models.user.UserSessionModel;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class TokenAuthenticator implements Authenticator {

    private static final int MAX_RETRY = 3;

    private final NetworkProvider provider;
    private final UserSessionModel session;

    @Inject
    public TokenAuthenticator(NetworkProvider provider, UserSessionModel session) {
        this.provider = provider;
        this.session = session;
    }

    @Override
    public Request authenticate(Route route, @NonNull Response response) throws IOException {

        // This is the case for login or register.
        // Do not do anything else then.
        if(!this.session.isOpened()) return null;

        // Retry limit exceeded. Do not try anymore.
        if(this.countRetries(response) >= MAX_RETRY) return null;

        UserController controller = this.provider.getService(UserController.class);

        Credentials credentials = this.session.getCredentials();

        Call<Token> call = controller.login(credentials);

        // Trying to refresh the session.
        retrofit2.Response<Token> authentication = call.execute();

        // Cannot authenticate.
        if(!authentication.isSuccessful()) return null;

        Token token = authentication.body();

        this.session.refresh(token);

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
