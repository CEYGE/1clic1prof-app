package fr.clic1prof.activities.dashboard.student;


import android.os.Bundle;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.AbstractActivity;

public class MainStudentActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main_activity);
        this.setListenerMenu();
    }



}