package fr.clic1prof.models.profile;

import fr.clic1prof.models.other.SchoolLevel;

public class StudentProfile extends Profile {

    private SchoolLevel level;

    public StudentProfile(String email, String firstName, String lastName, int pictureId, SchoolLevel level) {
        super(email, firstName, lastName, pictureId);
        this.level = level;
    }

    public SchoolLevel getLevel() {
        return this.level;
    }

    public void setLevel(SchoolLevel level) {
        this.level = level;
    }
}
