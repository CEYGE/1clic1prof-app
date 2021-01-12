package fr.clic1prof.activities.dashboard.teacher;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.DashboardFragment;

public class TeacherDashboardFragment extends DashboardFragment {

    public static TeacherDashboardFragment newInstance() {
        return new TeacherDashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflateFragment(R.layout.teacher_dashboard, inflater, container);
    }

}