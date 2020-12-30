package fr.clic1prof.activities;

import android.os.Bundle;

import fr.clic1prof.R;
import fr.clic1prof.abstractviews.AbstractActivity;

public class TeacherActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main_activity);
        this.setListenerMenu();
    }

}
