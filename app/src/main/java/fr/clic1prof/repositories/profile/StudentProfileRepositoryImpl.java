package fr.clic1prof.repositories.profile;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import fr.clic1prof.api.profile.StudentProfileController;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.network.NetworkProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfileRepositoryImpl extends UserProfileRepository<StudentProfile> implements StudentProfileRepository {

    private static final String TAG = "SProfileRepositoryImpl";

    @Inject
    public StudentProfileRepositoryImpl(NetworkProvider provider) {
        super(provider);
    }

    @Override
    public LiveData<SchoolLevel> updateSchoolLevel(SchoolLevel level) {

        MutableLiveData<SchoolLevel> data = new MutableLiveData<>();

        this.getProfileController().updateSchoolLevel(level).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                data.postValue(response.isSuccessful() ? level : null);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot update student school level.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<StudentProfile> getProfile() {

        MutableLiveData<StudentProfile> data = new MutableLiveData<>();

        this.getProfileController().getProfile().enqueue(new Callback<StudentProfile>() {

            @Override
            public void onResponse(@NonNull Call<StudentProfile> call, @NonNull Response<StudentProfile> response) {
                data.postValue(response.isSuccessful() ? response.body() : null);
            }

            @Override
            public void onFailure(@NonNull Call<StudentProfile> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot retrieve student profile.", throwable);
            }
        });
        return data;
    }

    @Override
    public StudentProfileController getProfileController() {
        return super.getNetworkProvider().getService(StudentProfileController.class);
    }
}
