package fr.clic1prof.model;

public class Token {

    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public String getFormattedToken() {
        return String.format("Bearer %s", this.token);
    }
}
