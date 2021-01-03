package fr.clic1prof.viewmodels.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.repositories.profile.v2.ProfileRepository;
import fr.clic1prof.util.DataListener;

public class ProfileViewModel<T extends Profile> extends ViewModel {

    private final ProfileRepository<T> repository;

    protected final MutableLiveData<T> profileLiveData = new MutableLiveData<>();
    protected final MutableLiveData<Void> errorLiveData = new MutableLiveData<>();

    public ProfileViewModel(ProfileRepository<T> repository) {
        this.repository = repository;
    }

    public void getProfile() {

        this.repository.getProfile(new DataListener<T>() {

            @Override
            public void onSuccess(T value) {
                ProfileViewModel.this.profileLiveData.postValue(value);
            }

            @Override
            public void onError(String message) {
                // Or, post a String with a custom error message.
                ProfileViewModel.this.errorLiveData.postValue(null);
            }
        });
    }

    public void updateFirstName(String firstName) {

        this.repository.updateFirstName(firstName, new DataListener<Void>() {

            @Override
            public void onSuccess(Void value) {

                T profile = ProfileViewModel.this.profileLiveData.getValue();
                profile.setFirstName(firstName);

                ProfileViewModel.this.profileLiveData.postValue(profile);
            }

            @Override
            public void onError(String message) {
                // Or, post a String with a custom error message.
                ProfileViewModel.this.errorLiveData.postValue(null);
            }
        });
    }

    public void updateLastName(String lastName) {

        this.repository.updateFirstName(lastName, new DataListener<Void>() {

            @Override
            public void onSuccess(Void value) {

                T profile = ProfileViewModel.this.profileLiveData.getValue();
                profile.setLastName(lastName);

                ProfileViewModel.this.profileLiveData.postValue(profile);
            }

            @Override
            public void onError(String message) {
                // Or, post a String with a custom error message.
                ProfileViewModel.this.errorLiveData.postValue(null);
            }
        });
    }

    public LiveData<T> getProfileLiveData() {
        return this.profileLiveData;
    }

    public LiveData<Void> getErrorLiveData() {
        return this.errorLiveData;
    }

    public ProfileRepository<T> getRepository() {
        return this.repository;
    }
}
