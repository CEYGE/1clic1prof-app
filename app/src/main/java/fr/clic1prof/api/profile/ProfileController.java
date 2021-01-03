package fr.clic1prof.api.profile;

import fr.clic1prof.models.profile.modifier.PasswordModifier;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ProfileController {

    @Multipart
    @GET("{role}/profile/picture")
    Call<ResponseBody> getProfilePicture();

    @PUT("{role}/profile/first-name")
    Call<Void> updateFirstName(@Body String firstName);

    @PUT("{role}/profile/last-name")
    Call<Void> updateLastName(@Body String lastName);

    @PUT("{role}/profile/password")
    Call<Void> updatePassword(@Body PasswordModifier modifier);

    @Multipart
    @PUT("{role}/profile/picture")
    Call<Void> updateProfilePicture(@Part("picture") MultipartBody.Part picture);

    @DELETE("{role}/profile/picture")
    Call<Void> deleteProfilePicture();
}
