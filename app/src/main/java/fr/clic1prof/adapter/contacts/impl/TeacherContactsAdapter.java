package fr.clic1prof.adapter.contacts.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import fr.clic1prof.R;
import fr.clic1prof.adapter.contacts.ContactsAdapter;
import fr.clic1prof.adapter.contacts.viewholders.ContactViewHolder;
import fr.clic1prof.adapter.contacts.viewholders.HeaderViewHolder;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.HeaderContact;
import fr.clic1prof.models.contacts.StudentContact;
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
        StudentContact contact = (StudentContact) mContacts.get(position);

        // Set item views based on your views and data model
        Bitmap picture;
        if (contact.getPicture() == null) {
            picture = BitmapFactory.decodeResource(holder.itemView.getResources(), R.mipmap.default_profile_picture_round);
        }
        else {
            picture = contact.getPicture();
        }
        contactVh.imageView.setImageBitmap(picture);
        String contactName = holder.itemView.getResources().getString(R.string.contactName);
        contactName = contactName.replace("{firstName}", contact.getFirstName());
        contactName = contactName.replace("{lastName}", contact.getLastName());
        contactVh.nameTextView.setText(contactName);

        if(contact.getLevel() != null) contactVh.studyLevelTextView.setText(contact.getLevel().getLabel());
    }
}