package fr.clic1prof.activities.dashboard.student;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.AbstractActivity;
import fr.clic1prof.activities.profile.ProfileStudentActivity;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.viewmodels.profile.profileV2.ProfileViewModel;
import fr.clic1prof.viewmodels.profile.profileV2.StudentProfileViewModel;

// This annotation has been added after 1 hour of debug.
// So, if one day, you have a "no zero argument constructor" with ViewModel
// using Hilt, think about it and increase the number of hours spent to debug it.
// hours spent : 2
@AndroidEntryPoint
public class MainStudentActivity extends AbstractActivity<StudentProfile> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main_activity);
        this.setListenerMenu();
        setProfileObserver();
    }

    @Override
    public void sendToProfile(View view) {
        Intent intent = new Intent(this, ProfileStudentActivity.class);
        startActivity(intent);
    }

    @Override
    protected void setProfileObserver() {
        TextView drawer_name = findViewById(R.id.textView_docName);
        TextView drawer_studies = findViewById(R.id.textView_studies);
        ImageView profileImage = findViewById(R.id.profile_image);
        getViewModel().getProfileLiveData().observe(this, profile->{
            drawer_name.setText(profile.getFirstName()+" "+ profile.getLastName());
            drawer_studies.setText( profile.getLevel().getLabel());
            profileImage.setImageBitmap(profile.getPicture());
        });
    }

    @Override
    protected Class<? extends ProfileViewModel<StudentProfile>> getProfileViewModelClass() {
        return StudentProfileViewModel.class;
    }

}