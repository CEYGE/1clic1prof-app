package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import fr.clic1prof.R;
import fr.clic1prof.models.user.Registration;
import fr.clic1prof.viewmodels.InscriptionActivityViewModel;

public class InscriptionActivity extends AppCompatActivity {

    private InscriptionActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_page);


    }

    public void sendToLogIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void inscription(View view) {
        String lastName = findViewById(R.id.InscriptionlastNameText).toString();
        String firstName = findViewById(R.id.InscriptionfirstNameText).toString();
        String email = findViewById(R.id.InscriptionmailText).toString();
        String password = findViewById(R.id.InscriptionpasswordText).toString();
        Registration register = new Registration(firstName, lastName, email, password);


        Intent intent = new Intent(this, ProfileTeacherActivity.class);
        startActivity(intent);
    }

    public void visiblePassword(View view){
        EditText password = (EditText) findViewById(R.id.passwordText);
        boolean flag = password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if(flag){
            password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        }
    }


}