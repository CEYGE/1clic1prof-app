package fr.clic1prof.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.viewmodels.ContactActivityViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ContactActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            System.out.println("update !");

            TextView view = findViewById(R.id.titleFragment);
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
                MainActivity.this.viewModel.searchContacts(prefix);
            }
        });
    }

    public void connect(View view) {
        this.viewModel.searchContacts("prefix");
    }
}