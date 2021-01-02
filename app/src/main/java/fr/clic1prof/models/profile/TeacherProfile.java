package fr.clic1prof.models.profile;

import android.graphics.Bitmap;

import java.util.List;

import fr.clic1prof.models.other.Speciality;

public class TeacherProfile extends Profile {

    private String studies;
    private String description;
    private List<Speciality> specialities;

    public TeacherProfile(String email, String firstName, String lastName, int pictureId,
                          String description, String studies, List<Speciality> specialities) {
        super(email, firstName, lastName, pictureId);
        this.studies = studies;
        this.description = description;
        this.specialities = specialities;
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

    public List<Speciality> getSpecialities() {
        return this.specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}