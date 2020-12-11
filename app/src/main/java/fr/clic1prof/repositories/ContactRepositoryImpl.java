package fr.clic1prof.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import fr.clic1prof.api.ContactController;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.ContactManager;
import fr.clic1prof.models.contacts.ContactModel;
import fr.clic1prof.network.NetworkProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepositoryImpl implements ContactRepository {

    private static final String TAG = "ContactRepositoryImpl";

    private final NetworkProvider provider;

    @Inject
    public ContactRepositoryImpl(NetworkProvider provider) {
        this.provider = provider;
    }

    @Override
    public void getContacts(MutableLiveData<ContactModel> data) {

        ContactController controller = this.getController();

        Call<List<Contact>> call = controller.getContacts();

        call.enqueue(new Callback<List<Contact>>() {

            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {

                if(response.isSuccessful()) {

                    List<Contact> contacts = response.body();

                    ContactModel model = new ContactManager();
                    model.setContacts(contacts);

                    data.postValue(model);

                } else data.postValue(null);
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "Cannot retrieve user's contacts.", throwable);
            }
        });
    }

    private ContactController getController() {
        return this.provider.getService(ContactController.class);
    }
}
