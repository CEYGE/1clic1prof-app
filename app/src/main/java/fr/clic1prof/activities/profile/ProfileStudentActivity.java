package fr.clic1prof.activities.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.util.Camera;
import fr.clic1prof.viewmodels.profile.profileV2.StudentProfileViewModel;

@AndroidEntryPoint
public class ProfileStudentActivity extends ProfileActivity<StudentProfile> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_student_page);

        this.setButton();
        this.setSwitcher();
        this.setEditText();

        this.setViewModel( new ViewModelProvider(this).get(StudentProfileViewModel.class) );
        this.setError(Toast.makeText(this," ",Toast.LENGTH_SHORT));
        this.setObserverError("Failure to retrieve profile");
        this.setObserverProfile();
        this.getViewModel().getProfile();
        this.setCamera(new Camera(this));
        this.setSpinnerObserver();
    }


    /*
     * PART SETTER
     */

    @Override
    protected void setButton() {
        this.setButtonFirstName(R.id.switchFirstButton01);
        this.setButtonLastName(R.id.switchLastButton01);
        this.setButtonMail(R.id.switchMailButton01);
        this.setButtonPassword(R.id.switchPasswordButton01);
    }

    @Override
    protected void setSwitcher() {
        this.setSwitcherFirstName(R.id.switchFirstName01);
        this.setSwitcherLastName(R.id.switchLastName01);
        this.setSwitcherMail(R.id.switchMail01);
        this.setSwitcherPassword(R.id.switchPassword01);
    }

    @Override
    protected void setEditText() {
        this.setEditFirstName(R.id.editTextFirstName01);
        this.setEditLastName(R.id.editTextLastName01);
        this.setEditMail(R.id.editTextMail01);
        this.setEditPassword(R.id.editTextPassword01);
    }

    @Override
    public StudentProfileViewModel getViewModel() {
        return (StudentProfileViewModel) super.getViewModel();
    }

    private void setSpinnerObserver(){
        Spinner spinner = findViewById(R.id.spinnerSchoolLevel);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SchoolLevel schoolLevel = new SchoolLevel(position,parent.getItemAtPosition(position).toString());
                setObserverError("Le niveau d'élève n'arrive pas à se renouveler");
                getViewModel().updateSchoolLevel(schoolLevel);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing to do if no selection
            }
        });
    }

    @Override
    protected void assignInformation(StudentProfile profile){
        //View to return to dashboard
        TextView view = findViewById(R.id.textReturnView);
        String fullName = profile.getFirstName() + profile.getLastName();
        view.setText(fullName);
        //TextView profile
        TextView textFirst = findViewById(R.id.viewFirstName01);
        textFirst.setText(profile.getFirstName());
        TextView textLast = findViewById(R.id.viewLastName01);
        textLast.setText(profile.getLastName());
        TextView textMail = findViewById(R.id.viewMail01);
        textMail.setText(profile.getEmail());
        //SchoolLevel profile
        Spinner spinner = findViewById(R.id.spinnerSchoolLevel);
        spinner.setSelection(profile.getLevel().getId());
        //Image bitmap profile
        ImageView imgView = findViewById(R.id.profile_img01);
        imgView.setImageBitmap(profile.getPicture());
    }

}