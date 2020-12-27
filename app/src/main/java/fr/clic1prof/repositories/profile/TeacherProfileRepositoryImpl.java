package fr.clic1prof.repositories.profile;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import fr.clic1prof.api.profile.TeacherProfileController;
import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.models.profile.modifier.SpecialityModifier;
import fr.clic1prof.network.NetworkProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherProfileRepositoryImpl extends UserProfileRepository<TeacherProfile> implements TeacherProfileRepository {

    private static final String TAG = "TProfileRepositoryImpl";

    @Inject
    public TeacherProfileRepositoryImpl(NetworkProvider provider) {
        super(provider);
    }

    @Override
    public LiveData<String> updateStudies(String studies) {

        MutableLiveData<String> data = new MutableLiveData<>();

        this.getProfileController().updateStudies(studies).enqueue(new Callback<String>() {

            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                data.postValue(response.isSuccessful() ? studies : null);
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot update teacher studies.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<String> updateDescription(String description) {

        MutableLiveData<String> data = new MutableLiveData<>();

        this.getProfileController().updateDescription(description).enqueue(new Callback<String>() {

            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                data.postValue(response.isSuccessful() ? description : null);
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot update teacher description.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<SpecialityModifier> updateSpeciality(SpecialityModifier modifier) {

        MutableLiveData<SpecialityModifier> data = new MutableLiveData<>();

        this.getProfileController().updateSpeciality(modifier).enqueue(new Callback<SpecialityModifier>() {

            @Override
            public void onResponse(@NonNull Call<SpecialityModifier> call, @NonNull Response<SpecialityModifier> response) {
                data.postValue(response.isSuccessful() ? modifier : null);
            }

            @Override
            public void onFailure(@NonNull Call<SpecialityModifier> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot update teacher speciality.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<TeacherProfile> getProfile() {

        MutableLiveData<TeacherProfile> data = new MutableLiveData<>();

        this.getProfileController().getProfile().enqueue(new Callback<TeacherProfile>() {

            @Override
            public void onResponse(@NonNull Call<TeacherProfile> call, @NonNull Response<TeacherProfile> response) {
                data.postValue(response.isSuccessful() ? response.body() : null);
            }

            @Override
            public void onFailure(@NonNull Call<TeacherProfile> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot retrieve student profile.", throwable);
            }
        });
        return data;
    }

    @Override
    public TeacherProfileController getProfileController() {
        return super.getNetworkProvider().getService(TeacherProfileController.class);
    }
}
