package fr.clic1prof.repositories.profile.v2;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import fr.clic1prof.api.profile.TeacherProfileController;
import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.models.profile.modifier.SpecialityModifier;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.util.DataListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherProfileRepositoryImpl extends UserProfileRepository<TeacherProfile> implements TeacherProfileRepository  {

    @Inject
    public TeacherProfileRepositoryImpl(NetworkProvider provider) {
        super(provider);
    }

    @Override
    public void updateStudies(String studies, DataListener<Void> listener) {

        this.getProfileController().updateStudies(studies).enqueue(new Callback<String>() {

            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot update teacher's studies.");
            }
        });
    }

    @Override
    public void updateDescription(String description, DataListener<Void> listener) {

        this.getProfileController().updateDescription(description).enqueue(new Callback<String>() {

            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot update teacher's description.");
            }
        });
    }

    @Override
    public void updateSpeciality(SpecialityModifier modifier, DataListener<Void> listener) {

        this.getProfileController().updateSpeciality(modifier).enqueue(new Callback<SpecialityModifier>() {

            @Override
            public void onResponse(@NonNull Call<SpecialityModifier> call, @NonNull Response<SpecialityModifier> response) {
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(@NonNull Call<SpecialityModifier> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot update teacher's specialities.");
            }
        });
    }

    @Override
    public void getProfile(DataListener<TeacherProfile> listener) {

        this.getProfileController().getProfile().enqueue(new Callback<TeacherProfile>() {

            @Override
            public void onResponse(@NonNull Call<TeacherProfile> call, @NonNull Response<TeacherProfile> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TeacherProfile> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, "Cannot retrieve teacher's profile.");
            }
        });
    }

    @Override
    public TeacherProfileController getProfileController() {
        return super.getNetworkProvider().getService(TeacherProfileController.class);
    }
}
