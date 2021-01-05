package fr.clic1prof.repositories.profile;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import fr.clic1prof.api.profile.ProfileController;
import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.models.profile.modifier.PasswordModifier;
import fr.clic1prof.network.NetworkProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class UserProfileRepository<T extends Profile> implements ProfileRepository<T> {

    private static final String TAG = "UserProfileRepository";

    private final NetworkProvider provider;

    public UserProfileRepository(NetworkProvider provider) {
        this.provider = provider;
    }

    public abstract ProfileController getProfileController();

    @Override
    public LiveData<String> updateFirstName(final String firstName) {

        MutableLiveData<String> data = new MutableLiveData<>();

        ProfileController controller = this.getProfileController();

        controller.updateFirstName(firstName).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                data.postValue(response.isSuccessful() ? firstName : null);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot update user first name.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<String> updateLastName(final String lastName) {

        MutableLiveData<String> data = new MutableLiveData<>();

        ProfileController controller = this.getProfileController();

        controller.updateFirstName(lastName).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                data.postValue(response.isSuccessful() ? lastName : null);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot update user last name.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<Boolean> updatePassword(PasswordModifier modifier) {

        MutableLiveData<Boolean> data = new MutableLiveData<>();

        ProfileController controller = this.getProfileController();

        controller.updatePassword(modifier).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                data.postValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot update user first name.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<Bitmap> updatePicture(Bitmap bitmap) {
        return null;
    }

    public NetworkProvider getNetworkProvider() {
        return this.provider;
    }
}
