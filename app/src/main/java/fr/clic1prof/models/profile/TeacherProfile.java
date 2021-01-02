package fr.clic1prof.models.profile;

import android.graphics.Bitmap;

import fr.clic1prof.models.other.Speciality;

public class TeacherProfile extends Profile {

    private Speciality studies;
    private String description;

    public TeacherProfile(String email, String firstName, String lastName, Bitmap picture,
                          String description, Speciality studies) {
        super(email, firstName, lastName, picture);
        this.studies = studies;
        this.description = description;
    }

    public Speciality getStudies() {
        return this.studies;
    }

    public void setStudies(Speciality studies) {
        this.studies = studies;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}