package fr.clic1prof.models.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ContactHandlerImpl<T extends Contact> implements ContactHandler<T> {

    private final List<T> contacts = new ArrayList<>();

    @Inject
    public ContactHandlerImpl() {}

    @Override
    public void updateContacts(Collection<T> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
    }

    @Override
    public boolean hasContacts() {
        return !this.contacts.isEmpty();
    }

    @Override
    public T getContact(int id) {

        for(T contact : this.contacts) {

            if(contact.getId() == id) return contact;
        }
        return null;
    }

    @Override
    public List<T> getContactsByPrefix(String prefix) {

        if(prefix == null || prefix.equals("")) return this.contacts;

        prefix = prefix.toLowerCase();

        List<T> contacts = new ArrayList<>();

        for(T contact : this.contacts) {

            String firstName = contact.getFirstName().toLowerCase();
            String lastName = contact.getLastName().toLowerCase();

            if(firstName.startsWith(prefix) || lastName.startsWith(prefix)) contacts.add(contact);
        }
        return contacts;
    }

    @Override
    public List<T> getContacts() {
        return Collections.unmodifiableList(this.contacts);
    }
}
