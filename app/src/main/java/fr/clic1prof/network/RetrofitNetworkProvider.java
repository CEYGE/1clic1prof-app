package fr.clic1prof.network;

import javax.inject.Inject;

import fr.clic1prof.models.session.UserSessionModel;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetworkProvider implements NetworkProvider {

    private static final String BASE_URL = "http://10.0.2.2:8080";
    private static final Converter.Factory CONVERTER_FACTORY = GsonConverterFactory.create();

    private final Retrofit retrofit;
    private final UserSessionModel model;

    @Inject
    public RetrofitNetworkProvider(UserSessionModel model) {
        this.model = model;
        this.retrofit = this.provideRetrofit();
    }

    @Override
    public <T> T getService(Class<T> clazz) {
        return this.retrofit.create(clazz);
    }

    private Retrofit provideRetrofit() {

        OkHttpClient client = this.provideHttpClient();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(CONVERTER_FACTORY)
                .build();
    }

    private OkHttpClient provideHttpClient() {

        return new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(this.model))
                .addInterceptor(new UrlInterceptor(this.model))
                .authenticator(new TokenAuthenticator(this, this.model))
                .build();
    }
}