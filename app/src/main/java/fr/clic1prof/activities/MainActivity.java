package fr.clic1prof.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.contacts.ContactsAdapter;
import fr.clic1prof.R;
import fr.clic1prof.contacts.impl.StudentContactsAdapter;
import fr.clic1prof.contacts.impl.TeacherContactsAdapter;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.HeaderContact;
import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.network.authentication.AuthenticationRequest;
import fr.clic1prof.viewmodels.ResultType;
import fr.clic1prof.viewmodels.contacts.StudentContactActivityViewModel;
import fr.clic1prof.viewmodels.login.LoginActivityViewModel;

@AndroidEntryPoint
public class MainActivity extends Fragment {
    private List<Contact> contacts;
    private StudentContactActivityViewModel viewModel;
    private ContactsAdapter adapter;
    private RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Hide title bar
        AuthenticationRequest request = new AuthenticationRequest("test1.student@test.com", "UnRenard60**");
        LoginActivityViewModel lavm = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        lavm.login(request);

        setContentView(R.layout.activity_main);

        this.viewModel = new ViewModelProvider(this).get(StudentContactActivityViewModel.class);

        this.setEditTextListener();
        this.setContactObserver();

        createList(new ArrayList<>()); //Empty

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        adapter = new TeacherContactsAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        this.viewModel.retrieveContacts();
    }

    private void createList(List<TeacherContact> toAdd) {
        contacts = new ArrayList<>();
        contacts.addAll(toAdd);
        Collections.sort(contacts);
        contactSorter();

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        adapter = new TeacherContactsAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
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
        this.viewModel.getContactLiveData().observe(this, result -> {
            // Update view here.
            // If contact list is null, then there is an error.
            // Else, display contacts.

            TextView view;// = findViewById(R.id.textView); // TODO To change.
            String text;

            if (result.getType() == ResultType.SUCCESS) {
                List<TeacherContact> teachers = result.getData();
                createList(teachers);
                text = teachers.isEmpty() ? "Aucun contact trouvé" : "Voici vos contacts";

            } else if(result.getType() == ResultType.ERROR) {

                text = "Une erreur est survenue";
                System.out.println(text);
            } else {

                text = "Chargement des contacts...";
            }
            //view.setText(text);
        });
    }

    private void setEditTextListener() {

        EditText text = super.findViewById(R.id.editTextTextPersonName);

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
}