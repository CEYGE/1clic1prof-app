package fr.clic1prof.viewmodels.profile;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.models.profile.modifier.PasswordModifier;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.viewmodels.Result;

public abstract class ProfileActivityViewModel<T extends Profile> extends ViewModel {

    private final ProfileRepository<T> repository;
    private final MediatorLiveData<Result<T>> profileLiveData = new MediatorLiveData<>();
    //1 livedata par info indépendant
    //1 livedata qui contient l'ensemble du profile

    public ProfileActivityViewModel(ProfileRepository<T> repository){
        this.repository = repository;
        this.retrieveProfile();
    }

    public void retrieveProfile(){
        this.profileLiveData.postValue(Result.loading());

        LiveData<T> data = this.repository.getProfile();
        this.assignData(data);
    }

    public void updatePicture(Bitmap bitmap){
        LiveData<Bitmap> data = this.repository.updatePicture(bitmap);
    }

    public void updateProfile(String firstName, String lastName, PasswordModifier modifier){
        this.repository.updateFirstName(firstName);
        this.repository.updateLastName(lastName);
        this.repository.updatePassword(modifier);
    }

    public ProfileRepository<T> getRepository() {
        return repository;
    }

    public MediatorLiveData<Result<T>> getLiveData() {
        return this.profileLiveData;
    }


    private void assignData(LiveData<T> data) {

        this.profileLiveData.addSource(data, profile -> {

            Result<T> result = profile != null ? Result.success(profile) : Result.error();

            this.profileLiveData.postValue(result);
            this.profileLiveData.removeSource(data);
        });
    }

    private void assignBitmap(LiveData<Bitmap> data){
        this.profileLiveData.addSource(data, bitmap ->{

            Result<Bitmap> result = bitmap != null ? Result.success(bitmap) : Result.error();
            this.profileLiveData.removeSource(data);
        });
    }

}
