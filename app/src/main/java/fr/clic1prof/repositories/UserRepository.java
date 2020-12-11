package fr.clic1prof.repositories;

import androidx.lifecycle.MutableLiveData;

import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.models.user.Registration;
import fr.clic1prof.models.user.UserSessionModel;

public interface UserRepository {

    void login(MutableLiveData<UserSessionModel> data, Credentials credentials);
}
