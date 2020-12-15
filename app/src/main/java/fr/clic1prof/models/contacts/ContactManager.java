package fr.clic1prof.models.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ContactManager implements ContactModel {

    private final List<Contact> contacts = new ArrayList<>();

    @Inject
    public ContactManager() {}

    @Override
    public void setContacts(Collection<Contact> contacts) {

        if(contacts == null)
            throw new IllegalArgumentException("Contacts list cannot be null.");

        this.contacts.clear();
        this.contacts.addAll(contacts);
    }

    @Override
    public boolean hasContacts() {
        return !this.contacts.isEmpty();
    }

    @Override
    public List<Contact> getContactsByPrefix(String prefix) {

        if(prefix == null || prefix.equals("")) return this.contacts;

        prefix = prefix.toLowerCase();

        List<Contact> contacts = new ArrayList<>();

        for(Contact contact : this.contacts) {

            String firstName = contact.getFirstName().toLowerCase();
            String lastName = contact.getLastName().toLowerCase();

            if(firstName.startsWith(prefix) || lastName.startsWith(prefix)) contacts.add(contact);
        }
        return contacts;
    }

    @Override
    public List<Contact> getContacts() {
        return Collections.unmodifiableList(this.contacts);
    }
}
