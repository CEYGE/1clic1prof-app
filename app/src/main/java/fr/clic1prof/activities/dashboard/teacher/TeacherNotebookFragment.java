package fr.clic1prof.activities.dashboard.teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.NotebookFragment;

public class TeacherNotebookFragment extends NotebookFragment {

    public static TeacherNotebookFragment newInstance() {
        return new TeacherNotebookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflateFragment(R.layout.teacher_notebook, inflater, container);
        setListenerCourse(R.layout.bottom_sheet_teacher, view);
        return view;
    }


}
