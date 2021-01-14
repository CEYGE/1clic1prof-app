package fr.clic1prof.repositories.contacts;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import fr.clic1prof.api.contacts.StudentContactController;
import fr.clic1prof.models.contacts.ContactHandler;
import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.network.NetworkProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentContactRepository extends UserContactRepository<TeacherContact> {

    @Inject
    public StudentContactRepository(NetworkProvider provider, ContactHandler<TeacherContact> model) {
        super(provider, model);
    }

    @Override
    public LiveData<List<TeacherContact>> getContacts() {

        MutableLiveData<List<TeacherContact>> data = new MutableLiveData<>();

        this.getContactController().getContacts().enqueue(new Callback<List<TeacherContact>>() {

            @Override
            public void onResponse(@NonNull Call<List<TeacherContact>> call, @NonNull Response<List<TeacherContact>> response) {
                if(response.isSuccessful()) {

                    List<TeacherContact> contacts = response.body();

                    StudentContactRepository.super.getContactHandler().updateContacts(response.body());

                    data.postValue(contacts);

                } else data.postValue(null);
            }

            @Override
            public void onFailure(@NonNull Call<List<TeacherContact>> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e("TAG", "Cannot retrieve user's contacts.", throwable);
            }
        });
        return data;
    }

    @Override
    public StudentContactController getContactController() {
        return super.getNetworkProvider().getService(StudentContactController.class);
    }
}
