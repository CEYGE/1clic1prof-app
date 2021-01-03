package fr.clic1prof.contacts;

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
import fr.clic1prof.contacts.viewholders.ContactViewHolder;
import fr.clic1prof.contacts.viewholders.HeaderViewHolder;
import fr.clic1prof.models.contacts.Contact;
import fr.clic1prof.models.contacts.HeaderContact;
import fr.clic1prof.models.contacts.TeacherContact;

public abstract class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SectionIndexer {
    public static final int HEADER_VIEW = 0;
    public static final int CONTENT_VIEW = 1;

    protected final List<Contact> mContacts;
    private ArrayList<Integer> mSectionPositions;

    // Pass in the contact array into the constructor
    public ContactsAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_header, parent, false));
        }
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false));
    }

    // Involves populating data into the item through holder
    @Override
    public abstract void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        return mContacts.get(position).isHeader() ? HEADER_VIEW : CONTENT_VIEW;
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
            if (mContacts.get(i).getFirstName().length() > 0) {
                String section = String.valueOf(mContacts.get(i).getFirstName().charAt(0)).toUpperCase();
                if (!sections.contains(section)) {
                    sections.add(section);
                    mSectionPositions.add(i);
                }
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