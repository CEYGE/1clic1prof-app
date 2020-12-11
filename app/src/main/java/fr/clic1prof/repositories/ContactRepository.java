package fr.clic1prof.repositories;

import androidx.lifecycle.MutableLiveData;

import fr.clic1prof.models.contacts.ContactModel;

public interface ContactRepository {

    void getContacts(MutableLiveData<ContactModel> data);
}
