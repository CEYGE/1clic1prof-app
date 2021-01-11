package fr.clic1prof.activities.dashboard.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.NotebookFragment;

public class StudentNotebookFragment extends NotebookFragment {

    public static StudentNotebookFragment newInstance() {
        return new StudentNotebookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflateFragment(R.layout.student_notebook, inflater, container);
        setListenerCourse(R.layout.bottom_sheet_student, view);
        return view;
    }


}