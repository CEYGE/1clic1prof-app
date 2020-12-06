package fr.clic1prof.network;

import java.io.IOException;

import javax.inject.Inject;

import fr.clic1prof.model.Token;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Inject
    public SessionManager manager;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        // User not authenticated so do not do anything more.
        if(!this.manager.isSessionOpened()) return chain.proceed(request);

        Token token = this.manager.getToken();

        Request modified = request.newBuilder()
                .addHeader("Authorization", token.getFormattedToken())
                .build();

        return chain.proceed(modified);
    }
}
