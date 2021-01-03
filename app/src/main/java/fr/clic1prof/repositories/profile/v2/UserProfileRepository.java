package fr.clic1prof.repositories.profile.v2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import java.io.InputStream;

import fr.clic1prof.api.profile.ProfileController;
import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.models.profile.modifier.PasswordModifier;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.util.DataListener;
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

                InputStream stream = response.body().byteStream();

                Bitmap bitmap = BitmapFactory.decodeStream(stream);

                if(bitmap != null) listener.onSuccess(bitmap);
                else listener.onFailure(null, "Cannot decode bitmap."); // TODO to review.
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot retrieve user's profile picture.");
            }
        });
    }

    @Override
    public void updateFirstName(String firstName, DataListener<Void> listener) {

        this.getProfileController().updateFirstName(firstName).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot update user's first name.");
            }
        });
    }

    @Override
    public void updateLastName(String lastName, DataListener<Void> listener) {

        this.getProfileController().updateFirstName(lastName).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot update user's last name.");
            }
        });
    }

    @Override
    public void updatePassword(PasswordModifier modifier, DataListener<Void> listener) {

        this.getProfileController().updatePassword(modifier).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot update user's password.");
            }
        });
    }

    @Override
    public void updatePicture(Bitmap bitmap, DataListener<Void> listener) {
        // TODO
    }

    @Override
    public void deleteProfilePicture(DataListener<Void> listener) {

        this.getProfileController().deleteProfilePicture().enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot delete user's profile picture.");
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
