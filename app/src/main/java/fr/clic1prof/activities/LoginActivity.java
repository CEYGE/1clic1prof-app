package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import fr.clic1prof.R;
import fr.clic1prof.viewmodels.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        this.viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
    }

    //Method sans VMMV
    public void sendToProfil(View view){
        Intent intent = new Intent(this, ProfileTeacherActivity.class);
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