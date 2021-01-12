package fr.clic1prof.viewmodels.profile.profileV2;

import androidx.hilt.lifecycle.ViewModelInject;

import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.repositories.profile.StudentProfileRepository;
import fr.clic1prof.util.DataListener;

public class StudentProfileViewModel extends ProfileViewModel<StudentProfile> {

    @ViewModelInject
    public StudentProfileViewModel(ProfileRepository<StudentProfile> repository) {
        super(repository);
    }

    public void updateSchoolLevel(SchoolLevel level) {

        this.getRepository().updateSchoolLevel(level, new DataListener<SchoolLevel>() {

            @Override
            public void onSuccess(SchoolLevel value) {

                StudentProfile profile = StudentProfileViewModel.super.profileLiveData.getValue();
                profile.setLevel(value);

                StudentProfileViewModel.super.profileLiveData.postValue(profile);
            }

            @Override
            public void onError(String message) {
                // Or, post a String with a custom error message.
                StudentProfileViewModel.super.errorLiveData.postValue(null);
            }
        });
    }

    @Override
    public StudentProfileRepository getRepository() {
        return (StudentProfileRepository) super.getRepository();
    }
}
