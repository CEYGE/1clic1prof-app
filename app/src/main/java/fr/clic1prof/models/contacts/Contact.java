package fr.clic1prof.models.contacts;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

public class Contact {

    private final int id;
    private final String firstName, lastName;
    private final Bitmap picture;

    public Contact(int id, String firstName, String lastName, Bitmap picture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
    }

    public int getId() {
        return this.id;
    }

    @NonNull
    public String getFirstName() {
        return this.firstName;
    }

    @NonNull
    public String getLastName() {
        return this.lastName;
    }

    @NonNull
    public Bitmap getPicture() {
        return this.picture;
    }
}
