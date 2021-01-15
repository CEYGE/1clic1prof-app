package fr.clic1prof.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.activities.dashboard.teacher.MainTeacherActivity;
import fr.clic1prof.models.other.Speciality;
import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.util.ErrorEntrie;
import fr.clic1prof.viewmodels.profile.profileV2.ProfileViewModel;
import fr.clic1prof.viewmodels.profile.profileV2.TeacherProfileViewModel;

@AndroidEntryPoint
public class ProfileTeacherActivity extends ProfileActivity<TeacherProfile> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setImage();
        this.setSwitcher();
        this.setEditText();
    }

    @Override
    protected void assignInformation(TeacherProfile profile){
        //TextView profile
        TextView textFirst = findViewById(R.id.viewFirstName02);
        if(profile.getFirstName() != null) textFirst.setText(profile.getFirstName());

        TextView textLast = findViewById(R.id.viewLastName02);
        if(profile.getLastName() != null) textLast.setText(profile.getLastName());

        TextView textMail = findViewById(R.id.viewMail02);
        if (profile.getEmail() != null) textMail.setText(profile.getEmail());

        //Description profile
        EditText description = findViewById(R.id.editDescription02);
        description.setText(profile.getDescription());

        //Studies profile
        EditText studies = findViewById(R.id.editStudies02);
        studies.setText(profile.getStudies());

        //Speciality profile
        ChipGroup chipGroup = findViewById(R.id.Speciality);
        for (Speciality speciality : profile.getSpecialities()){
            for (int i=0; i<chipGroup.getChildCount();i++){
                Chip chip = (Chip)chipGroup.getChildAt(i);
                if(speciality.getId() == (i+1)) {
                    chip.setChecked(true);
                }
            }
        }
        //Image bitmap profile
        ImageView imgView = findViewById(R.id.profile_img);
        if(profile.getPicture() != null) imgView.setImageBitmap(profile.getPicture());
    }

    @Override
    public void sendHomePage(View view) {
        Intent intent = new Intent(this, MainTeacherActivity.class);
        startActivity(intent);
    }


    public void switchDescriptionAndUpdate(View view){
        EditText description = findViewById(R.id.editDescription02);
        getViewModel().updateDescription(description.getText().toString());

    }

    public void switchStudiesAndUpdate(View view){
        EditText studies = findViewById(R.id.editStudies02);
        getViewModel().updateStudies(studies.getText().toString());
    }

    public void setSpecialityListener(){
        ChipGroup chipGroup = findViewById(R.id.Speciality);
        for(int i=0; i<chipGroup.getChildCount();i++){
            chipGroup.getChildAt(i).setOnClickListener(v -> {
                //TODO : update Speciality
            });
        }

    }

    @Override
    protected void setImage() {
        this.setImageFirstName(R.id.switchFirstButton02);
        this.setImageLastName(R.id.switchLastButton02);
        this.setImageMail(R.id.switchMailButton02);
        this.setImagePassword(R.id.switchPasswordButton02);
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
    protected void setLayout() {
        setContentView(R.layout.profile_teacher_page);
    }

    @Override
    public void setErrorEntry() {
        this.setError(new ErrorEntrie(findViewById(R.id.errorInvisibleViewProfile02)));
    }

    @Override
    public TeacherProfileViewModel getViewModel() {
        return (TeacherProfileViewModel)super.getViewModel();
    }

    @Override
    protected Class<? extends ProfileViewModel<TeacherProfile>> getProfileViewModelClass() {
        return TeacherProfileViewModel.class;
    }


}