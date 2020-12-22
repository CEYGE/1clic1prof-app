package fr.clic1prof.activities;

import java.util.regex.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.viewmodels.LoginActivityViewModel;
import fr.clic1prof.viewmodels.ResultType;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private LoginActivityViewModel viewModel;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        this.loadingDialog = new LoadingDialog(LoginActivity.this);

        System.out.println("\n"+"TEST de test@gmail.com:"+verifMail("test@gmail.com") +"\n");
        System.out.println("\n"+"TEST de test68@gmail.com:"+verifMail("test68@gmail.com") +"\n");
        System.out.println("\n"+"TEST de test@gmail54.com:"+verifMail("test@gmail54.com") +"\n");
        System.out.println("\n"+"TEST de test.548@gmail.com:"+verifMail("test.548@gmail.com") +"\n");

        this.viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        //Setup login observer
        this.setLoginObserver();
    }

    private void setLoginObserver(){

        this.viewModel.getTokenLiveData().observe(this, result ->{


            if(result.getType() == ResultType.SUCCESS) {
                this.loadingDialog.dismissDialog();
                sendToProfil();

            } else if(result.getType() == ResultType.ERROR) {
                this.loadingDialog.ErrorDialog();

            } else {
                this.loadingDialog.launchLoadingDialog();
                this.loadingDialog.startLoadingDialog();
            }
        });
    }

    public void connect(View view) {
        //TODO : Verification String
        String email = findViewById(R.id.mailText).toString();
        String password = findViewById(R.id.passwordText).toString();
        if(verifMail(email) && verifPwd(password)) {
            Credentials credentials = new Credentials(email, password);
            this.viewModel.login(credentials);
        }else {
            this.loadingDialog.launchLoadingDialog();
            this.loadingDialog.startLoadingDialog();
            this.loadingDialog.errorEntries(getResources().getColor(R.color.red));
        }
    }



    //Method sans VMMV
    private void sendToProfil(){
        Intent intent = new Intent(this, ProfileStudentActivity.class);
        startActivity(intent);
    }

    public void sendToInscription(View view){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    /**
     * Switch visibility on a password between password type text or class type text
     * @param view View where the visibility switch
     */
    public void visibilityPassword(View view){
        EditText password = (EditText) findViewById(R.id.passwordText);
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
        return Pattern.matches("^[a-zA-Z1-9_.%+-]{6,9}$",password);
    }

    /**
     * Verification of the email as the form : example123@example.com
     * @param mail String from the mail TextView
     * @return True if email meets the criteria
     */
    private boolean verifMail(String mail) {
        return Pattern.matches("^[a-zA-Z1-9._%+-]*@[a-zA-Z1-9]*\\.(com|net|fr){1}$", mail);
    }

}