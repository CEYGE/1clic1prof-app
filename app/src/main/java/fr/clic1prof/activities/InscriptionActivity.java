package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import fr.clic1prof.R;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_page);


    }

    public void sendToLogIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void Inscription(View view) {
        Intent intent = new Intent(this, ProfileTeacherActivity.class);
        startActivity(intent);
    }

    public void visiblePassword(View view){
        EditText password = (EditText) findViewById(R.id.passwordText);
        if(password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD) ){
            password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        }
    }


}