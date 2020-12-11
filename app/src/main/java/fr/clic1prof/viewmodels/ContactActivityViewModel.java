package fr.clic1prof.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import fr.clic1prof.Application;
import fr.clic1prof.ApplicationModule;
import fr.clic1prof.models.contacts.ContactModel;
import fr.clic1prof.repositories.ContactRepository;
import fr.clic1prof.repositories.ContactRepositoryImpl;

public class ContactActivityViewModel extends ViewModel {

    private final ContactRepository repository;
    private final MutableLiveData<ContactModel> contacts = new MutableLiveData<>();

    public ContactActivityViewModel() {
        // TODO Remove this fucking shit.
        this.repository = new ContactRepositoryImpl(ApplicationModule.getNetworkProvider());
    }

    public void retrieveContacts() {
        this.repository.getContacts(this.contacts);
    }

    public MutableLiveData<ContactModel> getContactMutableLiveData() {
        return this.contacts;
    }
}
