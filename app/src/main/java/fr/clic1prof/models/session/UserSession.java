package fr.clic1prof.models.session;

import javax.inject.Inject;

public class UserSession implements UserSessionModel {

    private Credentials credentials;
    private SessionType type;
    private Token token;

    @Inject
    public UserSession() {
    }

    @Override
    public void open(SessionType type, Credentials credentials, Token token) {
        this.type = type;
        this.credentials = credentials;
        this.token = token;
    }

    @Override
    public void close() {
        this.credentials = null;
        this.token = null;
    }

    @Override
    public void refresh(Token token) {
        this.token = token;
    }

    @Override
    public boolean isOpened() {
        return this.credentials != null && this.token != null;
    }

    @Override
    public SessionType getSessionType() {
        return this.type;
    }

    @Override
    public Credentials getCredentials() {
        return this.credentials;
    }

    @Override
    public Token getToken() {
        return this.token;
    }
}