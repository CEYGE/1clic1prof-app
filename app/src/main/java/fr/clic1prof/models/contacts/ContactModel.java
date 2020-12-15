package fr.clic1prof.models.contacts;

import java.util.Collection;
import java.util.List;

public interface ContactModel {

    void setContacts(Collection<Contact> contacts);

    boolean hasContacts();

    List<Contact> getContactsByPrefix(String prefix);

    List<Contact> getContacts();
}
