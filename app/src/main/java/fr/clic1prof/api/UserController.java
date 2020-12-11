package fr.clic1prof.api;

import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.models.user.Registration;
import fr.clic1prof.models.user.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserController {

    @POST("login")
    Call<Token> login(@Body Credentials credentials);

    @POST("register")
    Call<Void> register(@Body Registration registration);
}
