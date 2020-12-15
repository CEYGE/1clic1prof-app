package fr.clic1prof.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.repositories.ContactRepository;

public class ContactActivityViewModel extends ViewModel {

    private final ContactRepository repository;
    private final MediatorLiveData<Result<List<Contact>>> contactsLiveData = new MediatorLiveData<>();

    @ViewModelInject // ViewModel injection annotation.
    public ContactActivityViewModel(ContactRepository repository) {
        this.repository = repository;
        this.retrieveContacts();
    }

    public void retrieveContacts() {

        this.contactsLiveData.postValue(Result.loading());

        LiveData<List<Contact>> data = this.repository.getContacts();
        this.assignData(data);
    }

    public void searchContacts(String prefix) {

        LiveData<List<Contact>> data = this.repository.getContactsByPrefix(prefix);
        this.assignData(data);
    }

    private void assignData(LiveData<List<Contact>> data) {

        this.contactsLiveData.addSource(data, contacts -> {

            Result<List<Contact>> result = contacts != null ? Result.success(contacts) : Result.error();

            this.contactsLiveData.postValue(result);
            this.contactsLiveData.removeSource(data);
        });
    }

    public LiveData<Result<List<Contact>>> getContactLiveData() {
        return this.contactsLiveData;
    }
}
