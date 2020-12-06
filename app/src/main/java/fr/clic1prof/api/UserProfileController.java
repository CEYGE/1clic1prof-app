package fr.clic1prof.api;

import fr.clic1prof.model.PasswordModifier;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface UserProfileController {

    @PUT("profile/first-name")
    Call<Void> changeFirstName(@Body String firstName);

    @PUT("profile/last-name")
    Call<Void> changeLastName(@Body String lastName);

    @PUT("profile/password")
    Call<Void> changePassword(@Body PasswordModifier modifier);
}
