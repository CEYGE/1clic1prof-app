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
        System.out.println("Modify image's profil");
    }

    public void sendHomePage(View view){
        System.out.println("Return to Home page");
    }

    public void onOffNotification(){
        System.out.println("Activate or desactivate notification");
    }
    

}