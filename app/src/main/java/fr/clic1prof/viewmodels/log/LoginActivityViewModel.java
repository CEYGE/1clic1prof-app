package fr.clic1prof.viewmodels.log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

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

        LiveData<Boolean> data = this.repository.login(request);

        this.tokenLiveData.addSource(data, state -> {

            Result<?> result = state ? Result.success(null) : Result.error();

            this.tokenLiveData.postValue(result);
            this.tokenLiveData.removeSource(data);
        });
    }


    public MediatorLiveData<Result<?>> getTokenLiveData() {
        return tokenLiveData;
    }
}
