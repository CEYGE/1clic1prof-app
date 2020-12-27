package fr.clic1prof.repositories.profile;

import androidx.lifecycle.LiveData;

import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.models.profile.modifier.SpecialityModifier;

public interface TeacherProfileRepository extends ProfileRepository<TeacherProfile> {

    LiveData<String> updateStudies(String studies);

    LiveData<String> updateDescription(String description);

    LiveData<SpecialityModifier> updateSpeciality(SpecialityModifier modifier);
}
