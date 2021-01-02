package fr.clic1prof.models.profile;

import android.graphics.Bitmap;

public class Profile {

    private final String email;
    private String firstName, lastName;
    private int pictureId;
    private Bitmap picture;

    public Profile(String email, String firstName, String lastName, int pictureId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureId = pictureId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public Bitmap getPicture() {
        return this.picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}