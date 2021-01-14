package fr.clic1prof.activities.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.security.spec.ECField;
import java.util.regex.Pattern;

import fr.clic1prof.R;
import fr.clic1prof.activities.login.LoginActivity;
import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.util.Camera;
import fr.clic1prof.viewmodels.profile.profileV2.ProfileViewModel;

public abstract class ProfileActivity<T extends Profile> extends AppCompatActivity {

    private ProfileViewModel<T> viewModel;
    private Toast error;
    private AlertDialog dialog;
    private ImageView selectedImage;
    private Camera camera;

    //Button to update information
    private ImageView imageFirstName;
    private ImageView imageLastName;
    private ImageView imageMail;
    private ImageView imagePassword;
    //Switcher between View and EditText
    private ViewSwitcher switcherFirstName;
    private ViewSwitcher switcherLastName;
    private ViewSwitcher switcherMail;
    private ViewSwitcher switcherPassword;
    //EditText to modify
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editMail;
    private EditText editPassword;

    protected abstract void setImage();

    protected abstract void setSwitcher();

    protected abstract void setEditText();

    protected abstract Class<? extends ProfileViewModel<T>> getProfileViewModelClass();

    public abstract void sendHomePage(View view);

    protected abstract void assignInformation(T profile);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setImage();

        this.setError(Toast.makeText(this," ", Toast.LENGTH_SHORT));
        this.setCamera(new Camera(this));

        this.viewModel = new ViewModelProvider(this).get(getProfileViewModelClass());
        this.setObserverError("Failure to retrieve profile");
        this.setObserverProfile();
        this.viewModel.getProfile();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        if (requestCode == 102) {
            if(resultCode == Activity.RESULT_OK) {
                File f = new File(camera.currentPhotoPath);
                Uri contentUri = Uri.fromFile(f);
                camera.setImage(contentUri);
                setObserverError("Erreur de la prise de photo");
                this.viewModel.updatePicture(f);
            }
        }else if( requestCode == 103){
            if(resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                File f = new File(contentUri.getPath());
                camera.setImage(contentUri);
                setObserverError("Erreur d'envoi de la photo");
                this.viewModel.updatePicture(f);

            }
        }
    }

    public void disconnect(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void switchFirstNameAndUpdate(View view){
        if(imageFirstName.getContentDescription() == getResources().getText(R.string.versionModification)) {
            this.alternate(imageFirstName, R.string.versionConfirmation, switcherFirstName);
        }else {
            if(verifString(editFirstName.getText().toString())) {
                //Update
                setObserverError("Le prénom n'a pas pu s'update");
                this.viewModel.updateFirstName(editFirstName.getText().toString());
                this.alternate(imageFirstName, R.string.versionModification, switcherFirstName);
            }else {
                error.setText("Le prénom ne répond pas aux critères.");
                error.show();
            }
        }
    }

    public void switchLastNameAndUpdate(View view){
        if(imageLastName.getContentDescription() == getResources().getText(R.string.versionModification)) {
            this.alternate(imageLastName, R.string.versionConfirmation, switcherLastName);
        }else {
            if(verifString(editLastName.getText().toString())) {
                //Update
                setObserverError("Le nom n'a pas pu s'update");
                this.viewModel.updateLastName(editLastName.getText().toString());
                this.alternate(imageLastName, R.string.versionModification, switcherLastName);
            }else {
                error.setText("Le nom ne répond pas aux critères.");
                error.show();
            }
        }
    }

    public void switchMailAndUpdate(View view){
        if(imageMail.getContentDescription() == getResources().getText(R.string.versionModification)) {
            this.alternate(imageMail, R.string.versionConfirmation, switcherMail);
        }else {
            if(verifString(editMail.getText().toString())) {
                //TODO: Update MAIL
                this.alternate(imageMail, R.string.versionModification, switcherMail);
            }else {
                error.setText("Le mail ne répond pas aux critères.");
                error.show();
            }
        }
    }

    public void switchPasswordAndUpdate(View view){
        if(imagePassword.getContentDescription() == getResources().getText(R.string.versionModification)) {
            this.alternate(imagePassword, R.string.versionConfirmation, switcherPassword);
        }else {
            if(verifString(editPassword.getText().toString())) {
                //TODO: Update MDP
                this.alternate(imagePassword, R.string.versionModification, switcherPassword);
            }else {
                error.setText("Le mot de passe ne répond pas aux critères.");
                error.show();
            }
        }
    }

    public void modifyImage(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_change_picture,null));
        builder.setCancelable(true);
        this.setDialog(builder.create());
        this.dialog.show();
    }

    public void camera(View view){
        camera.askCameraPermission();
        dialog.dismiss();

    }

    public void gallery(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivity(intent);
        dialog.dismiss();
    }

    private void alternate(View image,int value, ViewSwitcher switcher){
        switcher.showNext();
        image.setMinimumHeight(20);
        image.setMinimumWidth(20);

        image.setBackground( image.getContentDescription() == getResources().getText(R.string.versionConfirmation) ? ContextCompat.getDrawable(this, R.drawable.write_icon) :ContextCompat.getDrawable(this, R.drawable.check_icon));
        image.setContentDescription(getResources().getText(value));

    }

    private boolean verifString(String value){
        return !Pattern.matches("^\\w$",value);
    }

    //Observer fixe sur model
    private void setObserverProfile(){
        //Action en observant le profile
        this.viewModel.getProfileLiveData().observe(this, this::assignInformation);
    }

    //Observer dynamic sur les errors.
    protected void setObserverError(String message){
        if(error != null){
            this.setError(new Toast(this));
            this.error.setDuration(Toast.LENGTH_SHORT);
        }
        // Supp tous les observer sur le LiveDataError avant de l'observer
        this.viewModel.getErrorLiveData().removeObservers(this);
        this.viewModel.getErrorLiveData().observe(this, result -> {
            //Action en observant une erreur
            error.setText(message);
            error.show();

        });
    }


    public void setImageFirstName(int imageFirstName) {
        this.imageFirstName = findViewById(imageFirstName);
    }

    public void setImageLastName(int imageLastName) {
        this.imageLastName = findViewById(imageLastName);
    }

    public void setImageMail(int imageMail) {
        this.imageMail = findViewById(imageMail);
    }

    public void setImagePassword(int imagePassword) {
        this.imagePassword = findViewById(imagePassword);
    }

    public void setSwitcherFirstName(int switcherFirstName) {
        this.switcherFirstName = findViewById(switcherFirstName);
    }

    public void setSwitcherLastName(int switcherLastName) {
        this.switcherLastName = findViewById(switcherLastName);
    }

    public void setSwitcherMail(int switcherMail) {
        this.switcherMail = findViewById(switcherMail);
    }

    public void setSwitcherPassword(int switcherPassword) {
        this.switcherPassword = findViewById(switcherPassword);
    }

    public void setEditFirstName(int editFirstName) {
        this.editFirstName = findViewById(editFirstName);
    }

    public void setEditLastName(int editLastName) {
        this.editLastName = findViewById(editLastName);
    }

    public void setEditMail(int editMail) {
        this.editMail = findViewById(editMail);
    }

    public void setEditPassword(int editPassword) {
        this.editPassword = findViewById(editPassword);
    }

    public void setError(Toast error) {
        this.error = error;
    }

    public void setSelectedImage(ImageView selectedImage) {
        this.selectedImage = selectedImage;
    }

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setViewModel(ProfileViewModel<T> viewModel) {
        this.viewModel = viewModel;
    }

    public Toast getError() {
        return this.error;
    }

    public ImageView getSelectedImage() {
        return selectedImage;
    }

    public AlertDialog getDialog() {
        return dialog;
    }

    public Camera getCamera() {
        return camera;
    }

    public ProfileViewModel<T> getViewModel() {
        return viewModel;
    }

}
