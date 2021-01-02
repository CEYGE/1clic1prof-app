package fr.clic1prof.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.clic1prof.R;
import fr.clic1prof.abstractviews.AgendaFragment;

public class TeacherAgendaFragment extends AgendaFragment {

    public static TeacherAgendaFragment newInstance() { return new TeacherAgendaFragment(); }
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflateFragment(R.layout.teacher_agenda, inflater, container);
    }

    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton buttonAddNote = getActivity().findViewById(R.id.button_add_event);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CreationEventActivity.class);
                startActivity(i);
            }
        });
    }
}
