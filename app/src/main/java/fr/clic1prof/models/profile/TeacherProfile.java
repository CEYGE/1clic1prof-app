package fr.clic1prof.models.profile;

import android.graphics.Bitmap;

public class TeacherProfile extends Profile {

    private String studies;
    private String description;

    public TeacherProfile(String email, String firstName, String lastName, Bitmap picture,
                          String description, String studies) {
        super(email, firstName, lastName, picture);
        this.studies = studies;
        this.description = description;
    }

    public String getStudies() {
        return this.studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
