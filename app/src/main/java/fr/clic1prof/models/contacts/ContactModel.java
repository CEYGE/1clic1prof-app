package fr.clic1prof.models.contacts;

import java.util.Collection;

public interface ContactModel {

    void setContacts(Collection<Contact> contacts);

    boolean hasContacts();

    Collection<Contact> getContacts();
}
