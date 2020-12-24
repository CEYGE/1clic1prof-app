package fr.clic1prof.api.profile;

import fr.clic1prof.models.profile.modifier.PasswordModifier;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ProfileController {

    @PUT("{role}/first-name")
    Call<Void> updateFirstName(@Body String firstName);

    @PUT("{role}/last-name")
    Call<Void> updateLastName(@Body String lastName);

    @PUT("{role}/password")
    Call<Void> updatePassword(@Body PasswordModifier modifier);

    @PUT("{role}/picture") @Multipart
    Call<Void> updatePicture(@Part("picture") MultipartBody.Part picture);
}
