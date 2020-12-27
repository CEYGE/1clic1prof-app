package fr.clic1prof.api.contacts;

import java.util.List;

import fr.clic1prof.models.contacts.StudentContact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TeacherContactController extends ContactController {

    @GET("teacher/contacts")
    Call<List<StudentContact>> getContacts();
}
