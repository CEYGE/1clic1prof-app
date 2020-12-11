package fr.clic1prof.model;

import fr.clic1prof.repositories.ProfilStudentRepository;

public class StudentInformation implements ProfilInformation {

    private final String firstName;
    private final String lastName;
    private final String mail;
    private final String password;
    private final String studyLevel;
    private final Boolean notification;

    public StudentInformation(String firstName, String lastName, String mail, String password, String studyLevel, Boolean notification) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.studyLevel = studyLevel;
        this.notification = notification;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getStudyLevel() {
        return this.studyLevel;
    }

    @Override
    public boolean isNotification() {
        return notification;
    }
}

