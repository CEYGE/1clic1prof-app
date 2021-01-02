package fr.clic1prof.models.contacts;

import java.util.Collection;
import java.util.List;

public interface ContactHandler<T extends Contact> {

    void updateContacts(Collection<T> contacts);

    boolean hasContacts();

    T getContact(int id);

    List<T> getContacts();

    List<T> getContactsByPrefix(String prefix);
}