package fr.clic1prof.contacts.impl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import fr.clic1prof.R;
import fr.clic1prof.contacts.ContactsAdapter;
import fr.clic1prof.contacts.viewholders.ContactViewHolder;
import fr.clic1prof.contacts.viewholders.HeaderViewHolder;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.HeaderContact;
import fr.clic1prof.models.contacts.TeacherContact;

public  class TeacherContactsAdapter extends ContactsAdapter {
    // Pass in the contact array into the constructor
    public TeacherContactsAdapter(List<Contact> contacts) {
        super(contacts);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        // Get the data model based on position
        if (HEADER_VIEW == getItemViewType(position)) {
            HeaderViewHolder headerVh = (HeaderViewHolder)holder;
            HeaderContact contact = (HeaderContact) mContacts.get(position);
            headerVh.headerTextView.setText(Character.toString(contact.getHeader()));
            return;
        }

        ContactViewHolder contactVh = (ContactViewHolder)holder;
        TeacherContact contact = (TeacherContact) mContacts.get(position);

        // Set item views based on your views and data model
        contactVh.imageView.setImageBitmap(contact.getPicture());
        //TODO: Image par défaut
        contactVh.nameTextView.setText(contact.getFirstName() + " " + contact.getLastName());
        contactVh.studyLevelTextView.setText(contact.getStudies());
    }
}