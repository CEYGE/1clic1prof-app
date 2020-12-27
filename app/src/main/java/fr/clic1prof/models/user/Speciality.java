package fr.clic1prof.models.user;

public class Speciality {

    private final int id;
    private final String label;

    public Speciality(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }
}
