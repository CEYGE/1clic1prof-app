package fr.clic1prof.activities.dashboard.teacher;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.lifecycle.ViewModelProvider;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.AbstractActivity;
import fr.clic1prof.activities.profile.ProfileStudentActivity;
import fr.clic1prof.activities.profile.ProfileTeacherActivity;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.viewmodels.profile.profileV2.ProfileViewModel;
import fr.clic1prof.viewmodels.profile.profileV2.StudentProfileViewModel;
import fr.clic1prof.viewmodels.profile.profileV2.TeacherProfileViewModel;

public class MainTeacherActivity extends AbstractActivity<TeacherProfile> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main_activity);
        this.setListenerMenu();

    }

    @Override
    public void sendToProfile(View view) {
        Intent intent = new Intent(this, ProfileTeacherActivity.class);
        startActivity(intent);
    }

    @Override
    protected void setProfileObserver() {
        getViewModel().getProfileLiveData().observe(this, profile->{
            //TODO : Tu récup ce que tu as besoin

        });
    }

    @Override
    protected Class<? extends ProfileViewModel<TeacherProfile> > getProfileViewModelClass() {
        return TeacherProfileViewModel.class;
    }

}
