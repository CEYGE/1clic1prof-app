package fr.clic1prof.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.clic1prof.models.contacts.ContactModel;
import fr.clic1prof.repositories.ContactRepository;

public class ContactActivityViewModel extends ViewModel {

    private final ContactRepository repository;
    private final MutableLiveData<ContactModel> contacts = new MutableLiveData<>();

    @ViewModelInject
    public ContactActivityViewModel(ContactRepository repository) {
        this.repository = repository;
    }

    public void retrieveContacts() {
        this.repository.getContacts(this.contacts);
    }

    public MutableLiveData<ContactModel> getContactMutableLiveData() {
        return this.contacts;
    }
}
