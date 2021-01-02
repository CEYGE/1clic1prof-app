package fr.clic1prof.models.contacts;

import android.graphics.Bitmap;

import javax.annotation.Nullable;

public class TeacherContact extends Contact {

    private final String studies;

    public TeacherContact(int id, String firstName, String lastName, Bitmap picture, String studies) {
        super(id, firstName, lastName, picture);
        this.studies = studies;
    }

    @Nullable
    public String getStudies() {
        return this.studies;
    }
}