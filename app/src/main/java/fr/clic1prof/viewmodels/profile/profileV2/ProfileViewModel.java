package fr.clic1prof.viewmodels.profile.profileV2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

import javax.annotation.Nullable;

import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.util.DataListener;

public class ProfileViewModel<T extends Profile> extends ViewModel {

    private final ProfileRepository<T> repository;

    protected final MutableLiveData<T> profileLiveData = new MutableLiveData<>();
    protected final MutableLiveData<Void> errorLiveData = new MutableLiveData<>();

    public ProfileViewModel(ProfileRepository<T> repository) {
        this.repository = repository;
    }

    public void getProfile() {

        if(isProfileLoaded()){
            this.profileLiveData.postValue(this.profileLiveData.getValue());
            this.getProfilePicture();
            return;
        }

        this.repository.getProfile(new DataListener<T>() {

            @Override
            public void onSuccess(T value) {
                ProfileViewModel.this.profileLiveData.postValue(value);
                ProfileViewModel.this.getProfilePicture();
            }

            @Override
            public void onError(String message) {
                // Or, post a String with a custom error message.
                Log.e("Failure_Profile",message);
                ProfileViewModel.this.errorLiveData.postValue(null);
            }
        });
    }

    public void getProfilePicture(){
        this.repository.getProfilePicture(new DataListener<Bitmap>() {

            @Override
            public void onSuccess(@Nullable Bitmap value) {
                ProfileViewModel.this.profileLiveData.getValue().setPicture(value);
            }

            @Override
            public void onError(String message) {
                Log.e("Failure_Picture",message);
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
                Log.e("Failure_FirstName",message);
            }
        });
    }

    public void updateLastName(String lastName) {

        this.repository.updateLastName(lastName, new DataListener<Void>() {

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
                Log.e("Failure_LastName",message);
            }
        });
    }

    public void updatePicture(File picture){

        this.repository.updatePicture(picture, new DataListener<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer value) {
                T profile = ProfileViewModel.this.profileLiveData.getValue();
                Bitmap image = BitmapFactory.decodeFile(picture.getAbsolutePath());
                profile.setPicture(image);
                profile.setPictureId(value);
                System.out.println("Picture viewModel : "+profile.getPicture());
                ProfileViewModel.this.profileLiveData.postValue(profile);
            }

            @Override
            public void onError(String message) {
                System.out.println("Error viewModel");
                ProfileViewModel.this.errorLiveData.postValue(null);
                Log.e("Failure_Picture",message);
            }
        });
    }

    public boolean isProfileLoaded(){
        return this.profileLiveData.getValue() != null;
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
