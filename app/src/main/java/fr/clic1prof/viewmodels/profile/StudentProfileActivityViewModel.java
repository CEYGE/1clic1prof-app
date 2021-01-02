package fr.clic1prof.viewmodels.profile;


import androidx.hilt.lifecycle.ViewModelInject;

import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.repositories.profile.ProfileRepository;

public class StudentProfileActivityViewModel extends ProfileActivityViewModel<StudentProfile> {

    @ViewModelInject
    public StudentProfileActivityViewModel(ProfileRepository<StudentProfile> repository){
        super(repository);
    }
}
