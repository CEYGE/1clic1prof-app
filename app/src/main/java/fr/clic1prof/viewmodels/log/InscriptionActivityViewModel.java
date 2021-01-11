package fr.clic1prof.viewmodels.log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import fr.clic1prof.models.registration.Registration;
import fr.clic1prof.repositories.user.UserRepository;
import fr.clic1prof.viewmodels.Result;

public class InscriptionActivityViewModel extends ViewModel {

    private final UserRepository repository;
    private final MediatorLiveData<Result<?>> liveData = new MediatorLiveData<>();

    @ViewModelInject
    public InscriptionActivityViewModel(UserRepository repository){
        this.repository = repository;
    }

    public void register(Registration registration){

        this.liveData.postValue(Result.loading());

        LiveData<Boolean> data = this.repository.register(registration);

        this.liveData.addSource(data, state -> {

            Result<?> result = state ? Result.success(null) : Result.error();

            this.liveData.postValue(result);
            this.liveData.removeSource(data);
        });

    }

    public MediatorLiveData<Result<?>> getLiveData() {
        return this.liveData;
    }
}
