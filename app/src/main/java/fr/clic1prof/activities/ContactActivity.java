package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import fr.clic1prof.R;
import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.viewmodels.ResultType;
import fr.clic1prof.viewmodels.contacts.StudentContactActivityViewModel;

public class ContactActivity extends AppCompatActivity {
    private StudentContactActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        this.viewModel = new ViewModelProvider(this).get(StudentContactActivityViewModel.class);

        this.setEditTextListener();

        // Setup contact observer.
        this.setContactObserver();
    }

    private void setContactObserver() {

        // Observe the list of contacts and make view update when necessary.
        this.viewModel.getContactLiveData().observe(this, result -> {
            // Update view here.
            // If contact list is null, then there is an error.
            // Else, display contacts.

            TextView view = findViewById(R.id.textView); // TODO To change.
            String text;

            if(result.getType() == ResultType.SUCCESS) {

                List<TeacherContact> contacts = result.getData();

                text = contacts.isEmpty() ? "Aucun contact trouvé" : "Voici vos contacts";

            } else if(result.getType() == ResultType.ERROR) {

                text = "Une erreur est survenue";

            } else {

                text = "Chargement des contacts...";
            }
            view.setText(text);
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
                text.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String prefix = s.toString();
                viewModel.searchContacts(prefix);
            }
        });
    }
}