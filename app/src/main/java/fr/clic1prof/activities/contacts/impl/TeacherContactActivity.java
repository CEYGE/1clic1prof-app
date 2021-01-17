package fr.clic1prof.activities.contacts.impl;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.activities.contacts.ContactActivity;
import fr.clic1prof.adapter.contacts.impl.TeacherContactsAdapter;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.StudentContact;
import fr.clic1prof.viewmodels.ResultType;
import fr.clic1prof.viewmodels.contacts.ContactActivityViewModel;
import fr.clic1prof.viewmodels.contacts.TeacherContactActivityViewModel;

@AndroidEntryPoint
public class TeacherContactActivity extends ContactActivity<StudentContact> {
    @Override
    public void onResume() {
        super.onResume();
        TextView title = getActivity().findViewById(R.id.titleContact);
        title.setText("Contacts\nélèves");
    }

    @Override
    protected void createAdapter() {
        rvContacts = (RecyclerView) getActivity().findViewById(R.id.rvContacts);
        adapter = new TeacherContactsAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected Class<? extends ContactActivityViewModel<StudentContact>> getViewModelClass() {
        return TeacherContactActivityViewModel.class;
    }

    protected void setContactObserver() {

        // Observe the list of contacts and make view update when necessary.
        this.getViewModel().getContactLiveData().observe(getActivity(), result -> {
            // Update view here.
            // If contact list is null, then there is an error.
            // Else, display contacts.

            if (result.getType() == ResultType.SUCCESS) {
                List<? extends Contact> teachers = result.getData();
                createList((List<Contact>) teachers);

            } else if(result.getType() == ResultType.ERROR) {

            } else {

            }
        });
    }
}
