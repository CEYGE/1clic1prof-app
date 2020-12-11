package fr.clic1prof.models.contacts;

public class Contact {

    private final String firstName;
    private final String lastName;
    private final String studyLevel;

    public Contact(String firstName, String lastName, String studyLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studyLevel = studyLevel;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getStudyLevel() {
        return this.studyLevel;
    }
}
