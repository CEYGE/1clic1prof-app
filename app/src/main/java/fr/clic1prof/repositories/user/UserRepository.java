package fr.clic1prof.repositories.user;

import androidx.lifecycle.LiveData;

import fr.clic1prof.models.registration.Registration;
import fr.clic1prof.network.authentication.AuthenticationRequest;

public interface UserRepository {

    LiveData<Boolean> login(AuthenticationRequest request);

    LiveData<Boolean> register(Registration registration);
}
