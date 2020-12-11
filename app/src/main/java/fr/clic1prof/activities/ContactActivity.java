package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import fr.clic1prof.Application;
import fr.clic1prof.R;
import fr.clic1prof.viewmodels.ContactActivityViewModel;

public class ContactActivity extends AppCompatActivity {

    private ContactActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Associate the ModelView with the current activity.
        this.viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ContactActivityViewModel.class);

        // Setup contact observer.
        this.setContactObserver();
    }

    private void setContactObserver() {

        // Observe the list of contacts and make view update when necessary.
        this.viewModel.getContactMutableLiveData().observe(this, contacts -> {
            // Update view here.
            // If contact list is null, then there is an error.
            // Else, display contacts.
        });
    }
}