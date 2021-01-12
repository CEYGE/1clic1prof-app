package fr.clic1prof.repositories.profile;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import fr.clic1prof.api.profile.StudentProfileController;
import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.util.DataListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfileRepositoryImpl extends UserProfileRepository<StudentProfile> implements StudentProfileRepository {

    @Inject
    public StudentProfileRepositoryImpl(NetworkProvider provider) {
        super(provider);
    }

    @Override
    public void getProfile(DataListener<StudentProfile> listener) {

        this.getProfileController().getProfile().enqueue(new Callback<StudentProfile>() {

            @Override
            public void onResponse(@NonNull Call<StudentProfile> call, @NonNull Response<StudentProfile> response) {

                if(response.isSuccessful()) listener.onSuccess(response.body());
                else listener.onError("Cannot retrieve student's profile.");
            }

            @Override
            public void onFailure(@NonNull Call<StudentProfile> call, @NonNull Throwable throwable) {
                listener.onError("Cannot retrieve student's profile.");
            }
        });
    }

    @Override
    public void updateSchoolLevel(SchoolLevel level, DataListener<SchoolLevel> listener) {

        this.getProfileController().updateSchoolLevel(level).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                if(response.isSuccessful()) listener.onSuccess(level);
                else listener.onError("Cannot update student's school level.");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                listener.onError("Cannot update student's school level.");
            }
        });
    }

    @Override
    public StudentProfileController getProfileController() {
        return super.getNetworkProvider().getService(StudentProfileController.class);
    }
}
