package fr.clic1prof.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.clic1prof.models.contacts.Contact;

public interface ContactRepository {

    LiveData<List<Contact>> getContacts();

    LiveData<List<Contact>> getContactsByPrefix(String prefix);
}
