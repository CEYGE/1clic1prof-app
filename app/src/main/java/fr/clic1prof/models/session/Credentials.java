package fr.clic1prof.models.session;

import com.google.gson.annotations.SerializedName;

public class Credentials {

    @SerializedName("username")
    private final String email;

    private final String password;

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
