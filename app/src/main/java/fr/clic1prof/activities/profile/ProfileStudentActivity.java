package fr.clic1prof.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.activities.dashboard.student.MainStudentActivity;
import fr.clic1prof.models.other.SchoolLevel;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.util.ErrorEntrie;
import fr.clic1prof.viewmodels.profile.profileV2.ProfileViewModel;
import fr.clic1prof.viewmodels.profile.profileV2.StudentProfileViewModel;

@AndroidEntryPoint
public class ProfileStudentActivity extends ProfileActivity<StudentProfile> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setImage();
        this.setSwitcher();
        this.setEditText();
        this.setSpinnerObserver();
    }

    @Override
    protected void assignInformation(StudentProfile profile){
        //TextView profile
        TextView textFirst = findViewById(R.id.viewFirstName01);
        if(profile.getFirstName() != null) textFirst.setText(profile.getFirstName());

        TextView textLast = findViewById(R.id.viewLastName01);
        if(profile.getLastName() != null) textLast.setText(profile.getLastName());

        TextView textMail = findViewById(R.id.viewMail01);
        if (profile.getEmail() != null) textMail.setText(profile.getEmail());

        //SchoolLevel profile
        Spinner spinner = findViewById(R.id.spinnerSchoolLevel);
        spinner.setSelection(profile.getLevel().getId());

        //Image bitmap profile
        ImageView imgView = findViewById(R.id.profile_img01);
        //TODO: default pic
        if (profile.getPicture() != null) imgView.setImageBitmap(profile.getPicture());
    }

    @Override
    public void sendHomePage(View view) {
        Intent intent = new Intent(this, MainStudentActivity.class);
        startActivity(intent);
    }

    private void setSpinnerObserver(){
        Spinner spinner = findViewById(R.id.spinnerSchoolLevel);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StudentProfile profile = getViewModel().getProfileLiveData().getValue();
                if(profile == null) return;
                SchoolLevel schoolLevel = new SchoolLevel(position, parent.getItemAtPosition(position).toString());
                if(profile.getLevel().getId() == schoolLevel.getId()) return;
                System.out.println("SchoolLevel"+getViewModel().getErrorLiveData().hasObservers());
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
    protected void setImage() {
        this.setImageFirstName(R.id.switchFirstButton01);
        this.setImageLastName(R.id.switchLastButton01);
        this.setImageMail(R.id.switchMailButton01);
        this.setImagePassword(R.id.switchPasswordButton01);
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
    protected void setLayout() {
        setContentView(R.layout.profile_student_page);
    }

    @Override
    public void setErrorEntry() {
         this.setError(new ErrorEntrie(findViewById(R.id.errorInvisibleViewProfile01)));
    }

    @Override
    public StudentProfileViewModel getViewModel() {
        return (StudentProfileViewModel) super.getViewModel();
    }

    @Override
    protected Class<? extends ProfileViewModel<StudentProfile>> getProfileViewModelClass() {
        return StudentProfileViewModel.class;
    }



}