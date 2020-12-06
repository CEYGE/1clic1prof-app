package fr.clic1prof.model;

public class PasswordModifier {

    private final String currentPassword;
    private final String newPassword;

    public PasswordModifier(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return this.currentPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }
}
