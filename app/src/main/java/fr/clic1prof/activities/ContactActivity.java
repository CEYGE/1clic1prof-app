package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import fr.clic1prof.R;
import fr.clic1prof.viewmodels.ContactActivityViewModel;

public class ContactActivity extends AppCompatActivity {

    private ContactActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        this.viewModel = new ViewModelProvider(this).get(ContactActivityViewModel.class);

        this.setEditTextListener();

        // Setup contact observer.
        this.setContactObserver();
    }

    private void setContactObserver() {

        // Observe the list of contacts and make view update when necessary.
        this.viewModel.getContactLiveData().observe(this, contacts -> {
            // Update view here.
            // If contact list is null, then there is an error.
            // Else, display contacts.

            System.out.println("update !"); // TODO To remove.

            TextView view = findViewById(R.id.titleFragment); // TODO To change.
            view.setText(contacts.isEmpty() ? "Aucun contact trouvé !" : "Affichage des contacts.");
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
}