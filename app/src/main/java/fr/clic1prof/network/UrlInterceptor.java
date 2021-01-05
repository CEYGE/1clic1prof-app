package fr.clic1prof.network;

import java.io.IOException;
import java.util.List;

import fr.clic1prof.models.session.UserSessionModel;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UrlInterceptor implements Interceptor {

    private final UserSessionModel session;

    public UrlInterceptor(UserSessionModel session) {
        this.session = session;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        if(!this.session.isOpened()) return chain.proceed(request);

        HttpUrl url = request.url();

        int index = this.getRoleSegmentIndex(url);

        if(index == -1) return chain.proceed(request);

        String role = this.session.getSessionType().name().toLowerCase();

        request = request.newBuilder()
                .url(url.newBuilder().setPathSegment(index, role).build())
                .build();

        return chain.proceed(request);
    }

    private int getRoleSegmentIndex(HttpUrl url) {

        List<String> segments = url.pathSegments();

        for(int i = 0; i < segments.size(); i++) {

            String segment = segments.get(i);

            if(segment.contains("{role}")) return i;
        }
        return -1;
    }
}
