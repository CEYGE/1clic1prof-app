package fr.clic1prof.models.contacts;

public class Contact implements Comparable<Contact> {
    private final int id;
    private final String firstName, lastName;
    private final Bitmap picture;

    public Contact(int id, String firstName, String lastName, Bitmap picture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
    }

    public int getId() {
        return this.id;
    }

    @NonNull
    public String getFirstName() {
        return this.firstName;
    }

    @NonNull
    public String getLastName() {
        return this.lastName;
    }

    @NonNull
    public Bitmap getPicture() {
        return this.picture;
    }

    @Override
    public int compareTo(Contact o) {
        return Character.toString(firstName.charAt(0)).compareTo(Character.toString(o.firstName.charAt(0)));
    }
}
