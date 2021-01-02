package fr.clic1prof.models.profile.modifier;

public class PasswordModifier {

    private final String oldPassword;
    private final String newPassword;

    public PasswordModifier(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }
}