package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        this.viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        //Setup login observer
        this.setLoginObserver();
    }

    private void setLoginObserver(){

        this.viewModel.getTokenLiveData().observe(this, result ->{


            if(result.getType() == ResultType.SUCCESS) {
                System.out.println("##### REUSSITE ####");
                this.loadingDialog.dismissDialog();
                sendToProfil();

            } else if(result.getType() == ResultType.ERROR) {
                System.out.println("#### ECHEC ####");
                this.loadingDialog.ErrorDialog();

            } else {
                System.out.println("##### ATTENTE ####");
                this.loadingDialog.startLoadingDialog();
            }
        });
    }

    public void connect(View view) {
        String email = findViewById(R.id.mailText).toString();
        String password = findViewById(R.id.passwordText).toString();
        Credentials credentials = new Credentials(email,password);
        this.viewModel.login(credentials);
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

    //Switch between visible caracter or caracter as *
    public void visibilityPassword(View view){
        EditText password = (EditText) findViewById(R.id.passwordText);
        boolean flag = password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if(flag){
            password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        }
    }

}