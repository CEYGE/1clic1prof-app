package fr.clic1prof.models.session;

public interface UserSessionModel {

    void open(SessionType type, Credentials credentials, Token token);

    void close();

    void refresh(Token token);

    boolean isOpened();

    SessionType getSessionType();

    Credentials getCredentials();

    Token getToken();
}
