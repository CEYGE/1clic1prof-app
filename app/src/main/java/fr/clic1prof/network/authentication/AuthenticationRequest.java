package fr.clic1prof.network.authentication;

import com.google.gson.annotations.SerializedName;

public class AuthenticationRequest {

    @SerializedName("username")
    private final String email;

    @SerializedName("password")
    private final String password;

    public AuthenticationRequest(String email, String password) {
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