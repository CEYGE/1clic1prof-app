package fr.clic1prof.repositories.contacts;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import fr.clic1prof.api.contacts.TeacherContactController;
import fr.clic1prof.models.contacts.ContactHandler;
import fr.clic1prof.models.contacts.StudentContact;
import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.network.NetworkProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherContactRepository extends UserContactRepository<StudentContact> {

    @Inject
    public TeacherContactRepository(NetworkProvider provider, ContactHandler<StudentContact> model) {
        super(provider, model);
    }

    @Override
    public LiveData<List<StudentContact>> getContacts() {

        MutableLiveData<List<StudentContact>> data = new MutableLiveData<>();

        this.getContactController().getContacts().enqueue(new Callback<List<StudentContact>>() {

            @Override
            public void onResponse(@NonNull Call<List<StudentContact>> call, @NonNull Response<List<StudentContact>> response) {

                if(response.isSuccessful()) {

                    List<StudentContact> contacts = response.body();

                    getContactHandler().updateContacts(response.body());

                    data.postValue(contacts);

                } else data.postValue(null);
            }

            @Override
            public void onFailure(@NonNull Call<List<StudentContact>> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e("TAG", "Cannot retrieve user's contacts.", throwable);
            }
        });
        return data;
    }

    @Override
    public TeacherContactController getContactController() {
        return super.getNetworkProvider().getService(TeacherContactController.class);
    }
}
