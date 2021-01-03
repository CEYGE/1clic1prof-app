package fr.clic1prof.repositories.profile.v2;

import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.util.DataListener;

public interface StudentProfileRepository extends ProfileRepository<StudentProfile> {

    void updateSchoolLevel(SchoolLevel level, DataListener<SchoolLevel> listener);
}
