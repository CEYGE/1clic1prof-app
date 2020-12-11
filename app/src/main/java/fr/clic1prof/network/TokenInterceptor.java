package fr.clic1prof.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import fr.clic1prof.models.user.Token;
import fr.clic1prof.models.user.UserSessionModel;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private final UserSessionModel session;

    @Inject
    public TokenInterceptor(UserSessionModel session) {
        this.session = session;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        // User not authenticated so do not do anything more.
        if(!this.session.isOpened()) return chain.proceed(request);

        Token token = this.session.getToken();

        Request modified = request.newBuilder()
                .addHeader("Authorization", token.getFormattedToken())
                .build();

        return chain.proceed(modified);
    }
}
