package fr.clic1prof.models.contacts;

public class HeaderContact extends Contact {
    private final char header;

    public HeaderContact(char header) {
        super(-1, "", "", null);
        this.header = header;
    }

    public char getHeader() {
        return this.header;
    }
}
