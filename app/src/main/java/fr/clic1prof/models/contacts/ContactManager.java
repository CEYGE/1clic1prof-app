package fr.clic1prof.models.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ContactManager implements ContactModel {

    private final List<Contact> contacts = new ArrayList<>();

    @Override
    public void setContacts(Collection<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
    }

    @Override
    public boolean hasContacts() {
        return !this.contacts.isEmpty();
    }

    @Override
    public Collection<Contact> getContacts() {
        return Collections.unmodifiableCollection(this.contacts);
    }
}
