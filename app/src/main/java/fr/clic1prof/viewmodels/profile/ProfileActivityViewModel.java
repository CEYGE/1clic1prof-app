package fr.clic1prof.viewmodels.profile;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;


import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.viewmodels.Result;

public abstract class ProfileActivityViewModel<T extends Profile> extends ViewModel {

    private final ProfileRepository<T> repository;
    private final MediatorLiveData<Result<T>> profileLiveData = new MediatorLiveData<>();

    private final MediatorLiveData<Result<String>> firstNameLiveData = new MediatorLiveData<>();
    private final MediatorLiveData<Result<String>> lastNameLiveData = new MediatorLiveData<>();
    private final MediatorLiveData<Result<Bitmap>> pictureLiveData = new MediatorLiveData<>();


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
        this.pictureLiveData.addSource(data, picture ->{

            Result<Bitmap> result = picture != null ? Result.success(picture) : Result.error();
            this.pictureLiveData.postValue(result);
            this.pictureLiveData.removeSource(data);
        });
    }

    public void updateFirstName(String value){
        LiveData<String> data = this.repository.updateFirstName(value);
        this.firstNameLiveData.addSource(data, name -> {

            Result<String> result = name != null ? Result.success(name) : Result.error();

            this.firstNameLiveData.postValue(result);
            this.firstNameLiveData.removeSource(data);
        });
    }
    public void updateLastName(String value){
        LiveData<String> data = this.repository.updateLastName(value);
        this.lastNameLiveData.addSource(data, name -> {

            Result<String> result = name != null ? Result.success(name) : Result.error();

            this.lastNameLiveData.postValue(result);
            this.lastNameLiveData.removeSource(data);
        });
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

}
