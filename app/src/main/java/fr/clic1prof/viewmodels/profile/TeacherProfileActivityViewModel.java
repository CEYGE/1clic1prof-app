package fr.clic1prof.viewmodels.profile;

import androidx.hilt.lifecycle.ViewModelInject;

import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.models.profile.modifier.SpecialityModifier;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.repositories.profile.TeacherProfileRepository;

public class TeacherProfileActivityViewModel extends ProfileActivityViewModel<TeacherProfile>{

    @ViewModelInject
    public TeacherProfileActivityViewModel(ProfileRepository<TeacherProfile> repository){
        super(repository);
    }

    public void updateDescription(String updateDescrip){
        getRepository().updateDescription(updateDescrip);
    }

    public void updateSpeciality(SpecialityModifier specialityModifier){
        getRepository().updateSpeciality(specialityModifier);
    }

    private void updateStudies(String studies){
        getRepository().updateStudies(studies);
    }

    @Override
    public TeacherProfileRepository getRepository() {
        return (TeacherProfileRepository) super.getRepository();
    }
}
