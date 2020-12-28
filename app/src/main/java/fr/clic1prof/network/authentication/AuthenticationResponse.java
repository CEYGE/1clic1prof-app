package fr.clic1prof.network.authentication;

import com.google.gson.annotations.SerializedName;

import fr.clic1prof.models.session.SessionType;

public class AuthenticationResponse {

    @SerializedName("token")
    private final String token;

    @SerializedName("role")
    private final SessionType type;

    public AuthenticationResponse(String token, SessionType type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return this.token;
    }

    public SessionType getType() {
        return this.type;
    }
}
