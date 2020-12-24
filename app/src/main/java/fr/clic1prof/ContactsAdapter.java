package fr.clic1prof;

import android.content.Context;
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

import fr.clic1prof.models.contacts.Contact;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> implements SectionIndexer {
    private final List<Contact> mContacts;
    private ArrayList<Integer> mSectionPositions;

    // Pass in the contact array into the constructor
    public ContactsAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imageView;
        public TextView nameTextView;
        public TextView studyLevelTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.contact_profilePic);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_firstName);
            studyLevelTextView = (TextView) itemView.findViewById(R.id.contact_study_level);
        }
    }

    @NotNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(v);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Contact contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(contact.getFirstName() + " " + contact.getLastName());
        TextView textView2 = holder.studyLevelTextView;
        textView2.setText(contact.getStudyLevel());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>(27);
        ArrayList<String> alphabetFull = new ArrayList<>();

        mSectionPositions = new ArrayList<>();
        for (int i = 0, size = mContacts.size(); i < size; i++) {
            String section = String.valueOf(mContacts.get(i).getFirstName().charAt(0)).toUpperCase();
            if (!sections.contains(section)) {
                sections.add(section);
                mSectionPositions.add(i);
            }
        }
        final String mSections = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < mSections.length(); i++) {
            alphabetFull.add(String.valueOf(mSections.charAt(i)));
        }

        return alphabetFull.toArray(new String[0]);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return mSectionPositions.get(sectionIndex);
    }
}