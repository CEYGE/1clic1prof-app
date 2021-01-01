package fr.clic1prof.viewmodels.login;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import fr.clic1prof.models.session.Credentials;
import fr.clic1prof.models.session.Token;
import fr.clic1prof.network.authentication.AuthenticationRequest;
import fr.clic1prof.repositories.user.UserRepository;
import fr.clic1prof.viewmodels.Result;

public class LoginActivityViewModel extends ViewModel {

    private final UserRepository repository;
    private final MediatorLiveData<Result<?>> tokenLiveData = new MediatorLiveData<>();

    @ViewModelInject
    public LoginActivityViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void login(AuthenticationRequest request) {
        this.tokenLiveData.postValue(Result.loading());
        LiveData<Boolean> data = this.repository.login(request);

        this.tokenLiveData.addSource(data, state -> {

            Result<?> result = state ? Result.success(null) : Result.error();

            this.tokenLiveData.postValue(result);
            this.tokenLiveData.removeSource(data);
        });
    }

    public MediatorLiveData<Result<?>> getTokenLiveData() {
        return this.tokenLiveData;
    }

}
