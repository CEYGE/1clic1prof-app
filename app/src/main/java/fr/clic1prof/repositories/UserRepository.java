package fr.clic1prof.repositories;

import androidx.lifecycle.MutableLiveData;

import fr.clic1prof.models.session.Credentials;
import fr.clic1prof.models.session.UserSessionModel;

public interface UserRepository {

    void login(MutableLiveData<UserSessionModel> data, Credentials credentials);
}
