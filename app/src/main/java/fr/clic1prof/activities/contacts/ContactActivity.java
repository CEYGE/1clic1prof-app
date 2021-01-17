package fr.clic1prof.activities.contacts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.AbstractFragment;
import fr.clic1prof.adapter.contacts.ContactsAdapter;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.HeaderContact;
import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.viewmodels.ResultType;
import fr.clic1prof.viewmodels.contacts.StudentContactActivityViewModel;

public abstract class ContactActivity extends AbstractFragment {
    protected List<Contact> contacts;
    private StudentContactActivityViewModel viewModel;
    protected ContactsAdapter adapter;
    protected RecyclerView rvContacts;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.viewModel = new ViewModelProvider(this).get(StudentContactActivityViewModel.class);

        this.setEditTextListener();
        this.setContactObserver();

        createList(new ArrayList<>()); //Empty

        createAdapter();

        this.viewModel.retrieveContacts();
    }

    protected abstract void createAdapter();

    private void createList(List<TeacherContact> toAdd) {
        contacts = new ArrayList<>();
        contacts.addAll(toAdd);
        Collections.sort(contacts);
        contactSorter();
        createAdapter();
    }

    private void contactSorter() {
        char letter = '#';
        for (int i = 0; i < contacts.size(); i++) {
            String contactName = contacts.get(i).getFirstName();
            if (contactName.length() > 0 && contactName.charAt(0) != letter) {
                letter = contactName.charAt(0);
                HeaderContact contact = new HeaderContact(letter);
                contact.setHeader(true);
                contacts.add(i, contact);
            }
        }
    }

    private void setContactObserver() {

        // Observe the list of contacts and make view update when necessary.
        this.viewModel.getContactLiveData().observe(getActivity(), result -> {
            // Update view here.
            // If contact list is null, then there is an error.
            // Else, display contacts.

            if (result.getType() == ResultType.SUCCESS) {
                List<TeacherContact> teachers = result.getData();
                createList(teachers);

            } else if(result.getType() == ResultType.ERROR) {

            } else {

            }
        });
    }

    private void setEditTextListener() {

        EditText text = getActivity().findViewById(R.id.editTextTextPersonName);

        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prefix = s.toString();
                viewModel.searchContacts(prefix);
            }
        });
    }

    public void connect(View view) {
        this.viewModel.retrieveContacts();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_contact, container, false);
    }
}