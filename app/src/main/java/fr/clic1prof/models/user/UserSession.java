package fr.clic1prof.models.user;

public class UserSession implements UserSessionModel {

    private Credentials credentials;
    private Token token;

    @Override
    public void open(Credentials credentials, Token token) {
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
    public Credentials getCredentials() {
        return this.credentials;
    }

    @Override
    public Token getToken() {
        return this.token;
    }
}
