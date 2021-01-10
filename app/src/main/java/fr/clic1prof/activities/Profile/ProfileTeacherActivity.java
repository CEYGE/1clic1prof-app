package fr.clic1prof.activities.Profile;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.util.Camera;
import fr.clic1prof.viewmodels.profile.profileV2.TeacherProfileViewModel;

@AndroidEntryPoint
public class ProfileTeacherActivity extends ProfileActivity<TeacherProfile> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_teacher_page);

        this.setButton();
        this.setSwitcher();
        this.setEditText();

        this.setViewModel(new ViewModelProvider(this).get(TeacherProfileViewModel.class));
        this.setError(Toast.makeText(this," ", Toast.LENGTH_SHORT));
        this.setObserverError("Failure to retrive profile");
        this.setObserverProfile();
        this.getViewModel().getProfile();
        this.setCamera(new Camera(this));
    }

    /*
     * PART SETTER
     */

    @Override
    protected void setButton() {
        this.setButtonFirstName(R.id.switchFirstButton02);
        this.setButtonLastName(R.id.switchLastButton02);
        this.setButtonMail(R.id.switchMailButton02);
        this.setButtonPassword(R.id.switchPasswordButton02);
    }

    @Override
    protected void setSwitcher() {
        this.setSwitcherFirstName(R.id.switchFirstName02);
        this.setSwitcherLastName(R.id.switchLastName02);
        this.setSwitcherMail(R.id.switchMail02);
        this.setSwitcherPassword(R.id.switchPassword02);
    }

    @Override
    protected void setEditText() {
        this.setEditFirstName(R.id.editTextFirstName02);
        this.setEditLastName(R.id.editTextLastName02);
        this.setEditMail(R.id.editTextMail02);
        this.setEditPassword(R.id.editTextPassword02);
    }

    @Override
    public TeacherProfileViewModel getViewModel() {
        return (TeacherProfileViewModel)super.getViewModel();
    }

    public void switchDescriptionAndUpdate(View view){
        EditText description = findViewById(R.id.editDescription02);
        getViewModel().updateDescription(description.getText().toString());

    }

    public void switchStudiesAndUpdate(View view){
        EditText studies = findViewById(R.id.editStudies02);
        getViewModel().updateStudies(studies.getText().toString());
    }



    @Override
    protected void assignInformation(TeacherProfile profile){
        //View to return to dashboard
        TextView view = findViewById(R.id.textReturnView);
        String fullName = profile.getFirstName() + profile.getLastName();
        view.setText(fullName);
        //TextView profile
        TextView textFirst = findViewById(R.id.viewFirstName02);
        textFirst.setText(profile.getFirstName());
        TextView textLast = findViewById(R.id.viewLastName02);
        textLast.setText(profile.getLastName());
        TextView textMail = findViewById(R.id.viewMail02);
        textMail.setText(profile.getEmail());
        //Description profile
        EditText description = findViewById(R.id.editDescription02);
        description.setText(profile.getDescription());
        //Studies profile
        EditText studies = findViewById(R.id.editStudies02);
        studies.setText(profile.getStudies());
        //Speciality profile
        Spinner spinner = findViewById(R.id.spinnerSpeciality);
        spinner.setSelection(profile.getSpecialities().get(1).getId());
        //Image bitmap profile
        ImageView imgView = findViewById(R.id.profile_img);
        imgView.setImageBitmap(profile.getPicture());
    }
}