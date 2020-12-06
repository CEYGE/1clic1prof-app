package fr.clic1prof.network;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetworkProvider implements NetworkProvider {

    private static final String BASE_URL = "http://10.0.2.2:8080";
    private static final Converter.Factory CONVERTER_FACTORY = GsonConverterFactory.create();

    private final Retrofit retrofit;

    public RetrofitNetworkProvider() {
        this.retrofit = this.provideRetrofit();
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
                .addInterceptor(new TokenInterceptor())
                .authenticator(new TokenAuthenticator())
                .build();
    }

    @Override
    public <T> T getService(Class<T> clazz) {
        return this.retrofit.create(clazz);
    }
}
