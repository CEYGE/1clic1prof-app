package fr.clic1prof.repositories.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.clic1prof.api.contacts.ContactController;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.ContactHandler;
import fr.clic1prof.network.NetworkProvider;

public abstract class UserContactRepository<T extends Contact> implements ContactRepository<T> {

    private static final String TAG = "UserContactRepository";

    private final ContactHandler<T> model;
    private final NetworkProvider provider;

    public UserContactRepository(NetworkProvider provider, ContactHandler<T> model) {
        this.provider = provider;
        this.model = model;
    }

    public abstract ContactController getContactController();

    @Override
    public LiveData<List<T>> getContactsByPrefix(String prefix) {

        List<T> contacts = this.model.getContactsByPrefix(prefix);

        MutableLiveData<List<T>> data = new MutableLiveData<>();
        data.postValue(contacts);

        return data;
    }

    public ContactHandler<T> getContactHandler() {
        return this.model;
    }

    public NetworkProvider getNetworkProvider() {
        return this.provider;
    }
}
