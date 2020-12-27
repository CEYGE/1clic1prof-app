package fr.clic1prof.models.profile;

import android.graphics.Bitmap;

import fr.clic1prof.models.user.SchoolLevel;

public class StudentProfile extends Profile {

    private SchoolLevel level;

    public StudentProfile(String email, String firstName, String lastName, Bitmap picture, SchoolLevel level) {
        super(email, firstName, lastName, picture);
    }

    public SchoolLevel getLevel() {
        return this.level;
    }

    public void setLevel(SchoolLevel level) {
        this.level = level;
    }
}
