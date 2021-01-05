package fr.clic1prof.activities;

import java.util.regex.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.util.ErrorEntrie;
import fr.clic1prof.util.LoadingDialog;
import fr.clic1prof.activities.Profile.ProfileStudentActivity;
import fr.clic1prof.activities.Profile.ProfileTeacherActivity;
import fr.clic1prof.models.session.SessionType;
import fr.clic1prof.models.session.UserSessionModel;
import fr.clic1prof.network.authentication.AuthenticationRequest;
import fr.clic1prof.viewmodels.login.LoginActivityViewModel;
import fr.clic1prof.viewmodels.ResultType;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    @Inject
    public UserSessionModel model;

    private ErrorEntrie error;
    private LoginActivityViewModel viewModel;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        this.loadingDialog = new LoadingDialog(LoginActivity.this);
        this.error = new ErrorEntrie(findViewById(R.id.errorInvisibleViewLogin));
        this.viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        //Setup login observer
        this.setLoginObserver();
    }

    private void setLoginObserver(){

        this.viewModel.getTokenLiveData().observe(this, result ->{


            if(result.getType() == ResultType.SUCCESS) {
                this.loadingDialog.dismissDialog();
                if(model.getSessionType() == SessionType.STUDENT) sendToStudentProfile();
                else sendToTeacherProfile();

            } else if(result.getType() == ResultType.ERROR) {
                this.loadingDialog.errorDialog(R.string.Dialog_error);

            } else {
                this.loadingDialog.launchLoadingDialog();
                this.loadingDialog.startLoadingDialog();
                this.loadingDialog.connectionText();
            }
        });
    }

    public void connect(View view) {
        error.cleanse();
        EditText emailView = findViewById(R.id.mailText);
        EditText passwordView = findViewById(R.id.passwordText);
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        if( verifPwd(password) && verifMail(email)) {
            AuthenticationRequest request = new AuthenticationRequest(email, password);
            this.viewModel.login(request);
        }else {
            error.showError();
        }
    }


    /**
     * Send user to the student profile
     */
    private void sendToStudentProfile(){
        Intent intent = new Intent(this, ProfileStudentActivity.class);
        startActivity(intent);
    }

    /**
     * Send user to the teacher profile
     */
    private void sendToTeacherProfile(){
        Intent intent = new Intent(this, ProfileTeacherActivity.class);
        startActivity(intent);
    }

    /**
     * Send user to the inscription activity
     * @param view View who use this function directly
     */
    public void sendToInscription(View view){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    /**
     * Send user to the Request for password forgotten activity
     * @param view View who use this function directly
     */
    public void sendToRequestPassword(View view){
        Intent intent = new Intent(this, RequestPasswordActivity.class);
        startActivity(intent);
    }

    /**
     * Switch visibility on a password between password type text or class type text
     * @param view View where the visibility switch
     */
    public void visibilityPassword(View view){
        EditText password = findViewById(R.id.passwordText);
        boolean flag = password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if(flag){
            password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        }
    }

    /**
     *  Verification of the password as the form : a1Zz5%+bg
     *  Password need to be between 6 and 9 length
     *  character is a letter, a number or special character (% _ . + -)
     * @param password String from the password TextView
     * @return True if password meets the criteria
     */
    private boolean verifPwd(String password){
        return Pattern.matches("^[a-zA-Z0-9_.%+*-]{6,15}$",password);
    }

    /**
     * Verification of the email as the form : example123@example.com
     * @param mail String from the mail TextView
     * @return True if email meets the criteria
     */
    private boolean verifMail(String mail) {
        return ( !TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches() );
    }


}