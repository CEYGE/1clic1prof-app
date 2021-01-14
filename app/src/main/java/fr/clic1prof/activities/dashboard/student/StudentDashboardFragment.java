package fr.clic1prof.activities.dashboard.student;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.DashboardFragment;

public class StudentDashboardFragment extends DashboardFragment {

    public static StudentDashboardFragment newInstance() {
        return new StudentDashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflateFragment(R.layout.student_dashboard, inflater, container);
    }
}