package fr.clic1prof.activities.contacts.impl;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.clic1prof.R;
import fr.clic1prof.activities.contacts.ContactActivity;
import fr.clic1prof.adapter.contacts.impl.StudentContactsAdapter;

public class StudentContactActivity extends ContactActivity {
    @Override
    public void onResume() {
        super.onResume();
        TextView title = getActivity().findViewById(R.id.titleContact);
        title.setText("Contacts\nprofesseurs");
    }

    @Override
    protected void createAdapter() {
        rvContacts = getActivity().findViewById(R.id.rvContacts);
        adapter = new StudentContactsAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
