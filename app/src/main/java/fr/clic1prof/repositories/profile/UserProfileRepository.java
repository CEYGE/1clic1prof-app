package fr.clic1prof.repositories.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.InputStream;

import fr.clic1prof.api.profile.ProfileController;
import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.models.profile.modifier.PasswordModifier;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.util.DataListener;
import fr.clic1prof.util.FileUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class UserProfileRepository<T extends Profile> implements ProfileRepository<T> {

    private final NetworkProvider provider;

    public UserProfileRepository(NetworkProvider provider) {
        this.provider = provider;
    }

    @Override
    public void getProfilePicture(DataListener<Bitmap> listener) {

        this.getProfileController().getProfilePicture().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if(response.isSuccessful() && response.body() != null) {

                    InputStream stream = response.body().byteStream();

                    Bitmap bitmap = BitmapFactory.decodeStream(stream);

                    if(bitmap != null) listener.onSuccess(bitmap);
                    else listener.onError("Cannot decode bitmap."); // TODO to review.

                } else listener.onError("Cannot retrieve user's profile picture.");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                listener.onError("Cannot retrieve user's profile picture.");
            }
        });
    }

    @Override
    public void updateFirstName(String firstName, DataListener<Void> listener) {

        this.getProfileController().updateFirstName(firstName).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.isSuccessful()) listener.onSuccess(null);
                else listener.onError("Cannot update user's first name.");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onError("Cannot update user's first name.");
            }
        });
    }

    @Override
    public void updateLastName(String lastName, DataListener<Void> listener) {

        this.getProfileController().updateFirstName(lastName).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                if(response.isSuccessful()) listener.onSuccess(null);
                else listener.onError("Cannot update user's last name.");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onError("Cannot update user's last name.");
            }
        });
    }

    @Override
    public void updatePassword(PasswordModifier modifier, DataListener<Void> listener) {

        this.getProfileController().updatePassword(modifier).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                if(response.isSuccessful()) listener.onSuccess(null);
                else listener.onError("Cannot update user's password.");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onError("Cannot update user's password.");
            }
        });
    }

    @Override
    public void updatePicture(File picture, DataListener<Integer> listener) {

        String mediaType = FileUtils.getMimeType(Uri.fromFile(picture));

        // MediaType cannot be found.
        if(mediaType == null) {
            listener.onError("MediaType not found.");
            return;
        }

        RequestBody body = RequestBody.create(MediaType.get(mediaType), picture);
        MultipartBody.Part part = MultipartBody.Part.createFormData("picture", picture.getName(), body);

        this.getProfileController().updateProfilePicture(part).enqueue(new Callback<Integer>() {

            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                System.out.println(response.code());
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else listener.onError("Cannot update user's profile picture.");
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable throwable) {
                System.out.println("Error");
                listener.onError("Cannot update user's profile picture.");
            }
        });
    }

    @Override
    public void deleteProfilePicture(DataListener<Boolean> listener) {

        this.getProfileController().deleteProfilePicture().enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                if(response.isSuccessful()) listener.onSuccess(null);
                else listener.onError("Cannot delete user's profile picture.");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onError("Cannot delete user's profile picture.");
            }
        });
    }

    public ProfileController getProfileController() {
        return this.provider.getService(ProfileController.class);
    }

    public NetworkProvider getNetworkProvider() {
        return this.provider;
    }
}
