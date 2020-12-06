package fr.clic1prof.api;

import fr.clic1prof.model.Registration;
import fr.clic1prof.model.Credentials;
import fr.clic1prof.model.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserController {

    @POST("login")
    Call<Token> login(@Body Credentials credentials);

    @POST("register")
    Call<Void> register(@Body Registration registration);
}
