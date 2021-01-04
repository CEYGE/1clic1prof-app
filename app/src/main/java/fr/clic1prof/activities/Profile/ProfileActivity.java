package fr.clic1prof.activities.Profile;

import android.app.AlertDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import fr.clic1prof.R;
import fr.clic1prof.Utilitary.Camera;
import fr.clic1prof.Utilitary.ErrorEntrie;
import fr.clic1prof.activities.LoginActivity;
import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.viewmodels.ResultType;
import fr.clic1prof.viewmodels.profile.ProfileActivityViewModel;

public abstract class ProfileActivity<T extends Profile> extends AppCompatActivity {

    private ProfileActivityViewModel<T> viewModel;
    private T profile;
    private ErrorEntrie error;
    private AlertDialog dialog;
    private ImageView selectedImage;
    private Camera camera;

    //Button to update information
    private Button buttonFirstName;
    private Button buttonLastName;
    private Button buttonMail;
    private Button buttonPassword;
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

    /*
     * PART SETTER
     */

    protected abstract void setButton();
    protected abstract void setSwitcher();
    protected abstract void setEditText();

    public void setButtonFirstName(int buttonFirstName) {
        this.buttonFirstName = findViewById(buttonFirstName);
    }

    public void setButtonLastName(int buttonLastName) {
        this.buttonLastName = findViewById(buttonLastName);
    }

    public void setButtonMail(int buttonMail) {
        this.buttonMail = findViewById(buttonMail);
    }

    public void setButtonPassword(int buttonPassword) {
        this.buttonPassword = findViewById(buttonPassword);
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


    public void setError(ErrorEntrie error) {
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

    public void setViewModel(ProfileActivityViewModel<T> viewModel) {
        this.viewModel = viewModel;
    }

    /*
     * PART GETTER
     */

    public ErrorEntrie getError() {
        return error;
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

    public ProfileActivityViewModel<T> getViewModel() {
        return viewModel;
    }

    /*
     * PART MOVE BETWEEN ACTIVITY
     */


    public void Disconnect(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void sendHomePage(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /*
     * PART OBSERVER
     */

    protected void setObserver(){
        this.viewModel.getLiveData().observe(this, result ->{
            if(result.getType() == ResultType.SUCCESS){
                profile = result.getData();
                assignInformation(profile);
            }
        });
    }

    protected abstract void assignInformation(T profile);

    /*
     * PART UPDATE INFORMATIONS
     */

    public void switchFirstNameAndUpdate(View view){
        if(buttonFirstName.getText().toString().equals("Modifier")) {
            switcherFirstName.showNext();
            changeTextButton(buttonFirstName);
        }else {
            if(verifString(editFirstName.getText().toString())) {
                //Update
                this.viewModel.updateFirstName(editFirstName.getText().toString());
                switcherFirstName.showNext();
                changeTextButton(buttonFirstName);
            }else {
                error.setText("Le prénom ne répond pas aux critères.");
                error.showError();
            }
        }
    }

    public void switchLastNameAndUpdate(View view){
        if(buttonLastName.getText().toString().equals("Modifier")) {
            switcherLastName.showNext();
            changeTextButton(buttonLastName);
        }else {
            if(verifString(editLastName.getText().toString())) {
                //Update
                this.viewModel.updateLastName(editLastName.getText().toString());
                switcherLastName.showNext();
                changeTextButton(buttonLastName);
            }else {
                error.setText("Le nom ne répond pas aux critères.");
                error.showError();
            }
        }
    }

    public void switchMailAndUpdate(View view){
        if(buttonMail.getText().toString().equals("Modifier")) {
            switcherMail.showNext();
            changeTextButton(buttonMail);
        }else {
            if(verifString(editMail.getText().toString())) {
                //TODO: Updata MAIL
                switcherMail.showNext();
                changeTextButton(buttonMail);
            }else {
                error.setText("Le mail ne répond pas aux critères.");
                error.showError();
            }
        }
    }

    public void switchPasswordAndUpdate(View view){
        if(buttonPassword.getText().toString().equals("Modifier")) {
            switcherPassword.showNext();
            changeTextButton(buttonPassword);
        }else {
            if(verifString(editPassword.getText().toString())) {
                //TODO: Update MDP
                switcherPassword.showNext();
                changeTextButton(buttonPassword);
            }else {
                error.setText("Le mot de passe ne répond pas aux critères.");
                error.showError();
            }
        }
    }

    /*
     * PART FUNCTION UTILITY
     */

    private void changeTextButton(Button button){
        button.setText( button.getText().toString().equals("Valider") ? "Modifier" : "Valider");
    }

    private boolean verifString(String value){
        return !Pattern.matches("^\\w$",value);
    }

    /*
     * PART PICTURE
     */

    public void modifyImage(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_change_picture,null));
        builder.setCancelable(true);
        this.dialog = builder.create();
        this.dialog.show();
        this.camera = new Camera(this);
        dialog.setOnCancelListener(dialog -> ProfileActivity.this.viewModel.updatePicture(camera.getImage()));
    }

    public void Camera(View view){
        camera.askCameraPermission();
        dialog.dismiss();
    }

    public void Gallery(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivity(intent);
        dialog.dismiss();
    }
}
