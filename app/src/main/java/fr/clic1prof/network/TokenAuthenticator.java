package fr.clic1prof.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import fr.clic1prof.api.UserController;
import fr.clic1prof.models.session.Credentials;
import fr.clic1prof.models.session.SessionType;
import fr.clic1prof.models.session.Token;
import fr.clic1prof.models.session.UserSessionModel;
import fr.clic1prof.network.authentication.AuthenticationRequest;
import fr.clic1prof.network.authentication.AuthenticationResponse;
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

        AuthenticationRequest request = new AuthenticationRequest(credentials.getEmail(), credentials.getPassword());

        Call<AuthenticationResponse> call = controller.login(request);

        // Trying to refresh the session.
        retrofit2.Response<AuthenticationResponse> authentication = call.execute();

        // Cannot authenticate.
        if(!authentication.isSuccessful() || authentication.body() == null) return null;

        AuthenticationResponse authResponse = authentication.body();
        Token token = new Token(authResponse.getToken());

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