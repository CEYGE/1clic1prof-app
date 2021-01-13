package fr.clic1prof.adapter.contacts.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.clic1prof.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public ImageView imageView;
    public TextView nameTextView;
    public TextView studyLevelTextView;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ContactViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        imageView = itemView.findViewById(R.id.contact_profilePic);
        nameTextView = itemView.findViewById(R.id.contact_firstName);
        studyLevelTextView = itemView.findViewById(R.id.contact_study_level);
    }
}