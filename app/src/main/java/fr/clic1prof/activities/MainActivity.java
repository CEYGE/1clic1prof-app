package fr.clic1prof.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.viewmodels.contacts.ContactActivityViewModel;
import fr.clic1prof.viewmodels.ResultType;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ContactActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonLog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLog();
            }
        });



        this.viewModel = new ViewModelProvider(this).get(ContactActivityViewModel.class);

        this.setEditTextListener();

        // Setup contact observer.
        /*
        this.setContactObserver();*/
    }

    private void sendLog() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void setContactObserver() {
        /*
        // Observe the list of contacts and make view update when necessary.
        this.viewModel.getContactLiveData().observe(this, result -> {
            // Update view here.
            // If contact list is null, then there is an error.
            // Else, display contacts.

            TextView view = findViewById(R.id.textView); // TODO To change.
            String text;

            if(result.getType() == ResultType.SUCCESS) {

                List<Contact> contacts = result.getData();

                text = contacts.isEmpty() ? "Aucun contact trouvé" : "Voici vos contacts";
                sendLog();

            } else if(result.getType() == ResultType.ERROR) {

                text = "Une erreur est survenue";

            } else {

                text = "Chargement des contacts...";
            }
            view.setText(text);
        });*/
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
                MainActivity.this.viewModel.searchContacts(prefix);
            }
        });
    }

    public void connect(View view) {
        this.viewModel.retrieveContacts();
    }


}