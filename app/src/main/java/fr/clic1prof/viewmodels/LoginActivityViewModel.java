package fr.clic1prof.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import javax.inject.Inject;

import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.models.user.Token;
import fr.clic1prof.repositories.UserRepository;

public class LoginActivityViewModel {

    private final UserRepository repository;
    private final MediatorLiveData<Result<Void>> tokenLiveData = new MediatorLiveData<>();

    @Inject
    public LoginActivityViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void login(Credentials credentials) {

        LiveData<Token> data = this.repository.login(credentials);

        this.tokenLiveData.addSource(data, token -> {

            Result<Void> result = token != null ? Result.success(null) : Result.error();

            this.tokenLiveData.postValue(result);
            this.tokenLiveData.removeSource(data);
        });
    }
}
