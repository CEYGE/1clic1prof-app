package fr.clic1prof.models.user;

public interface UserSessionModel {

    void open(Credentials credentials, Token token);

    void close();

    void refresh(Token token);

    boolean isOpened();

    Credentials getCredentials();

    Token getToken();
}
