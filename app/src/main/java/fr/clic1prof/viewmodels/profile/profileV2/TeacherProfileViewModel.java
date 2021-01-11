package fr.clic1prof.viewmodels.profile.profileV2;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;

import javax.annotation.Nullable;

import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.models.profile.modifier.SpecialityModifier;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.repositories.profile.TeacherProfileRepository;
import fr.clic1prof.util.DataListener;

public class TeacherProfileViewModel extends ProfileViewModel<TeacherProfile>{

    @ViewModelInject
    public TeacherProfileViewModel(ProfileRepository<TeacherProfile> repository) {
        super(repository);
    }

    public void updateDescription(String description){

        this.getRepository().updateDescription(description, new DataListener<Void>() {
            @Override
            public void onSuccess(@Nullable Void value) {
                TeacherProfile profile = TeacherProfileViewModel.super.profileLiveData.getValue();
                profile.setDescription(description);

                TeacherProfileViewModel.super.profileLiveData.postValue(profile);
            }

            @Override
            public void onError(String message) {
                // Or, post a String with a custom error message.
                TeacherProfileViewModel.super.errorLiveData.postValue(null);
                Log.e("Failure_Description",message);

            }
        });
    }

    public void updateStudies(String studies){
        this.getRepository().updateStudies(studies, new DataListener<Void>() {
            @Override
            public void onSuccess(@Nullable Void value) {
                TeacherProfile profile = TeacherProfileViewModel.super.profileLiveData.getValue();
                profile.setStudies(studies);
                TeacherProfileViewModel.super.profileLiveData.postValue(profile);
            }

            @Override
            public void onError(String message) {
                TeacherProfileViewModel.super.errorLiveData.postValue(null);
                Log.e("Failure_Studies",message);
            }
        });
    }


    public void updateSpeciality(SpecialityModifier specialityModifier){
        this.getRepository().updateSpeciality(specialityModifier, new DataListener<Void>() {
            @Override
            public void onSuccess(@Nullable Void value) {
                //Don't do something, no request
                TeacherProfile profile = TeacherProfileViewModel.super.profileLiveData.getValue();
                TeacherProfileViewModel.super.profileLiveData.postValue(profile);
            }

            @Override
            public void onError(String message) {
                TeacherProfileViewModel.super.errorLiveData.postValue(null);
                Log.e("Failure_Speciality",message);
            }
        });
    }

    @Override
    public TeacherProfileRepository getRepository() {
        return (TeacherProfileRepository) super.getRepository();
    }
}
