package fr.clic1prof.api;

import fr.clic1prof.models.registration.Registration;
import fr.clic1prof.network.authentication.AuthenticationRequest;
import fr.clic1prof.network.authentication.AuthenticationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserController {

    @POST("login")
    Call<AuthenticationResponse> login(@Body AuthenticationRequest request);

    @POST("register")
    Call<Void> register(@Body Registration registration);
}
