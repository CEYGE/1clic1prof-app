package fr.clic1prof.repositories.profile;

import androidx.lifecycle.LiveData;

import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.models.user.SchoolLevel;

public interface StudentProfileRepository extends ProfileRepository<StudentProfile> {

    LiveData<SchoolLevel> updateSchoolLevel(SchoolLevel level);
}
