package fr.clic1prof.activities.dashboard.teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.AgendaFragment;

public class TeacherAgendaFragment extends AgendaFragment {

    public static TeacherAgendaFragment newInstance() { return new TeacherAgendaFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflateFragment(R.layout.teacher_agenda, inflater, container);
        setListenerCourse(R.layout.bottom_sheet_teacher,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton buttonAddNote = getActivity().findViewById(R.id.button_add_event);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_teacher_agenda_to_creationEventActivity);
            }
        });
    }
}
