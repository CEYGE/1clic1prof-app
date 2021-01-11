package fr.clic1prof.repositories.profile;

import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.models.profile.modifier.SpecialityModifier;
import fr.clic1prof.util.DataListener;

public interface TeacherProfileRepository extends ProfileRepository<TeacherProfile> {

    void updateStudies(String studies, DataListener<Void> listener);

    void updateDescription(String description, DataListener<Void> listener);

    void updateSpeciality(SpecialityModifier modifier, DataListener<Void> listener);
}
