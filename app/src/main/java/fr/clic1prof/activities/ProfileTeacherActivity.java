package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.viewmodels.ResultType;
import fr.clic1prof.viewmodels.profile.StudentProfileActivityViewModel;
import fr.clic1prof.viewmodels.profile.TeacherProfileActivityViewModel;

@AndroidEntryPoint
public class ProfileTeacherActivity extends AppCompatActivity {

    private TeacherProfileActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_teacher_page);

        this.viewModel = new ViewModelProvider(this).get(TeacherProfileActivityViewModel.class);

        this.setObserver();
    }

    private void setObserver(){
        this.viewModel.getLiveData().observe(this, result ->{
            if(result.getType() == ResultType.SUCCESS){
                TeacherProfile profile = result.getData();
                assignInformation(profile);
            }
        });
    }

    private void assignInformation(TeacherProfile profile){
        //View to return to dashboard
        TextView view = findViewById(R.id.textReturnView);
        String fullName = profile.getFirstName() + profile.getLastName();
        view.setText(fullName);
        //TextView profile
        TextView textFirst = findViewById(R.id.editTextFirstName);
        textFirst.setText(profile.getFirstName());
        TextView textLast = findViewById(R.id.editTextLastName);
        textLast.setText(profile.getLastName());
        TextView textMail = findViewById(R.id.editTextMail);
        textMail.setText(profile.getEmail());
        //SchoolLevel profile
        Spinner spinner = findViewById(R.id.spinnerSpeciality);
        spinner.setSelection(profile.getStudies().getId());
        //Image bitmap profile
        ImageView imgView = findViewById(R.id.profile_img);
        imgView.setImageBitmap(profile.getPicture());
    }

    public void Disconnect(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}