package fr.clic1prof.viewmodels.profile;


import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.models.other.Speciality;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.repositories.profile.StudentProfileRepository;
import fr.clic1prof.viewmodels.Result;

public class StudentProfileActivityViewModel extends ProfileActivityViewModel<StudentProfile> {

    private final MediatorLiveData<Result<SchoolLevel>> schoolLevelLiveData = new MediatorLiveData<>();

    @ViewModelInject
    public StudentProfileActivityViewModel(ProfileRepository<StudentProfile> repository){
        super(repository);
    }

    public void updateSchoolLevel(SchoolLevel level){
        LiveData<SchoolLevel> data = getRepository().updateSchoolLevel(level);
        this.schoolLevelLiveData.addSource(data, name -> {

            Result<SchoolLevel> result = name != null ? Result.success(name) : Result.error();

            this.schoolLevelLiveData.postValue(result);
            this.schoolLevelLiveData.removeSource(data);
        });
    }


    public StudentProfileRepository getRepository() {
        return (StudentProfileRepository) super.getRepository();
    }
}
