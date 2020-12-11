package fr.clic1prof.api;

import java.util.List;

import fr.clic1prof.models.contacts.Contact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactController {

    @GET("contacts")
    Call<List<Contact>> getContacts();
}
