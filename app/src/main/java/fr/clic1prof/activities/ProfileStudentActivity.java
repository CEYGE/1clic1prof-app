package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import fr.clic1prof.R;

public class ProfileStudentActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_student_page);

    }

    public void Disconnect(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void modifyImage(View view){

    }

    public void sendHomePage(View view){

    }

    public void onOffNotification(){

    }
    

}