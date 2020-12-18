package fr.clic1prof.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.models.user.Token;
import fr.clic1prof.repositories.UserRepository;

public class LoginActivityViewModel extends ViewModel {

    private final UserRepository repository;
    private final MediatorLiveData<Result<Void>> tokenLiveData = new MediatorLiveData<>();

    @ViewModelInject
    public LoginActivityViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void login(Credentials credentials) {

        this.tokenLiveData.postValue(Result.loading());
        LiveData<Token> data = this.repository.login(credentials);

        this.tokenLiveData.addSource(data, token -> {

            Result<Void> result = token != null ? Result.success(null) : Result.error();

            this.tokenLiveData.postValue(result);
            this.tokenLiveData.removeSource(data);
        });
    }

    public MediatorLiveData<Result<Void>> getTokenLiveData() {
        return this.tokenLiveData;
    }
}
