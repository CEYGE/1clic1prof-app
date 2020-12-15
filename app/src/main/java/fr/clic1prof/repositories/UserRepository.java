package fr.clic1prof.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.models.user.Registration;
import fr.clic1prof.models.user.Token;
import fr.clic1prof.models.user.UserSessionModel;
import fr.clic1prof.viewmodels.Result;

public interface UserRepository {

    LiveData<Token> login(Credentials credentials);
}
