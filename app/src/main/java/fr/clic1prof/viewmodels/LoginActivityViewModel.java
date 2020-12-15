package fr.clic1prof.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.clic1prof.models.user.UserSessionModel;

public class LoginActivityViewModel extends ViewModel {

    private final MutableLiveData<UserSessionModel> data = new MutableLiveData<>();

    @ViewModelInject
    public LoginActivityViewModel(){}
}
