package fr.clic1prof.models.registration;

public class Registration {

    private final String firstName, lastName;
    private final String email, password;
    private final RegistrationType type;

    public Registration(String firstName, String lastName, String email, String password, RegistrationType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public RegistrationType getType() {
        return this.type;
    }
}