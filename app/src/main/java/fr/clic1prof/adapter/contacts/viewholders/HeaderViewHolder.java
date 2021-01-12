package fr.clic1prof.adapter.contacts.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.clic1prof.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView headerTextView;

    public HeaderViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        headerTextView = itemView.findViewById(R.id.header_text);
    }
}
