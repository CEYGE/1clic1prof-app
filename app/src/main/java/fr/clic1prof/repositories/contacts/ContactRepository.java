package fr.clic1prof.repositories.contacts;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.clic1prof.models.contacts.Contact;

public interface ContactRepository<T extends Contact> {

    LiveData<List<T>> getContacts();

    LiveData<List<T>> getContactsByPrefix(String prefix);
}
