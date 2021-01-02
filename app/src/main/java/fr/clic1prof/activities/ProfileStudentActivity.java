package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.regex.Pattern;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.viewmodels.ResultType;
import fr.clic1prof.viewmodels.profile.StudentProfileActivityViewModel;

@AndroidEntryPoint
public class ProfileStudentActivity extends AppCompatActivity {

    private StudentProfileActivityViewModel viewModel;
    private ErrorEntrie error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_student_page);

        this.viewModel = new ViewModelProvider(this).get(StudentProfileActivityViewModel.class);
        this.error = new ErrorEntrie(findViewById(R.id.errorInvisibleViewProfile01));
        this.setObserver();
    }

    private void setObserver(){
        this.viewModel.getLiveData().observe(this, result ->{
            System.out.println(result.getData().getLevel());
            if(result.getType() == ResultType.SUCCESS){
                StudentProfile profile = result.getData();
                assignInformation(profile);
            }
        });
    }

    private void assignInformation(StudentProfile profile){
        //View to return to dashboard
        TextView view = findViewById(R.id.textReturnView);
        String fullName = profile.getFirstName() + profile.getLastName();
        view.setText(fullName);
        //TextView profile
        TextView textFirst = findViewById(R.id.viewFirstName);
        textFirst.setText(profile.getFirstName());
        TextView textLast = findViewById(R.id.editTextLastName);
        textLast.setText(profile.getLastName());
        TextView textMail = findViewById(R.id.editTextMail);
        textMail.setText(profile.getEmail());
        /*//SchoolLevel profile
        Spinner spinner = findViewById(R.id.spinnerSchoolLevel);
        spinner.setSelection(profile.getLevel());
        *///Image bitmap profile
        ImageView imgView = findViewById(R.id.profile_img);
        imgView.setImageBitmap(profile.getPicture());
    }

    public void switchFirstNameAndUpdate01(View view){
        Button button = findViewById(R.id.switchFirstButton);
        ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.switchFirstName);
        if(button.getText().toString().equals("Modifier")) {
            switcher.showNext();
            changeTextButton(button);
        }else {
            EditText text = switcher.findViewById(R.id.editTextFirstName);
            if(verifString(text.getText().toString())) {
                this.viewModel.updateFirstName(text.getText().toString());
                switcher.showNext();
                changeTextButton(button);
            }else {
                error.setText("Le prénom ne répond pas aux critères.");
                error.showError();
            }
        }
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
    

    private void changeTextButton(Button button){
        button.setText( button.getText().toString().equals("Valider") ? "Modifier" : "Valider");
    }

    private boolean verifString(String value){
        return !Pattern.matches("^\\w$",value);
    }
}