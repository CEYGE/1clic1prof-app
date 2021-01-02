package fr.clic1prof.api.contacts;

import java.util.List;

import fr.clic1prof.models.contacts.TeacherContact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentContactController extends ContactController {

    @GET("student/contacts")
    Call<List<TeacherContact>> getContacts();
}
