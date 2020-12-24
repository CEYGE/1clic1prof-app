package fr.clic1prof.network.authentication;

import fr.clic1prof.models.session.SessionType;

public class AuthenticationResponse {

    private final String token;
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
