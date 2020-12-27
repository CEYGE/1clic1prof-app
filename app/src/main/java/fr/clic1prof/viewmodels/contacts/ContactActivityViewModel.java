package fr.clic1prof.viewmodels.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.repositories.contacts.ContactRepository;
import fr.clic1prof.viewmodels.Result;

public abstract class ContactActivityViewModel<T extends Contact> extends ViewModel {

    private final ContactRepository<T> repository;

    private final MediatorLiveData<Result<List<T>>> contactsLiveData = new MediatorLiveData<>();

    public ContactActivityViewModel(ContactRepository<T> repository) {
        this.repository = repository;
        this.retrieveContacts();
    }

    public void retrieveContacts() {

        this.contactsLiveData.postValue(Result.loading());

        LiveData<List<T>> data = this.repository.getContacts();
        this.assignData(data);
    }

    public void searchContacts(String prefix) {

        LiveData<List<T>> data = this.repository.getContactsByPrefix(prefix);
        this.assignData(data);
    }

    private void assignData(LiveData<List<T>> data) {

        this.contactsLiveData.addSource(data, contacts -> {

            Result<List<T>> result = contacts != null ? Result.success(contacts) : Result.error();

            this.contactsLiveData.postValue(result);
            this.contactsLiveData.removeSource(data);
        });
    }

    public LiveData<Result<List<T>>> getContactLiveData() {
        return this.contactsLiveData;
    }
}
