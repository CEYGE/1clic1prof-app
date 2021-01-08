package fr.clic1prof.models.contacts;

import android.graphics.Bitmap;

import javax.annotation.Nullable;

import fr.clic1prof.models.other.SchoolLevel;

public class StudentContact extends Contact {

    private final SchoolLevel level;

    public StudentContact(int id, String firstName, String lastName, Bitmap picture, SchoolLevel level) {
        super(id, firstName, lastName, picture);
        this.level = level;
    }

    @Nullable
    public SchoolLevel getLevel() {
        return this.level;
    }
}
