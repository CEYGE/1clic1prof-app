package fr.clic1prof.network;

import fr.clic1prof.model.Credentials;
import fr.clic1prof.model.Token;

public class SessionManager {

    private Credentials credentials;
    private Token token;

    public boolean isSessionOpened() {
        return this.token != null;
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public Token getToken() {
        return this.token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
