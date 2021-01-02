package fr.clic1prof.abstractviews;


import android.widget.TextView;

import fr.clic1prof.R;

public abstract class AgendaFragment extends AbstractFragment {
    //TODO add specific method for agenda here

    @Override
    public void onResume() {
        super.onResume();
        TextView title = getActivity().findViewById(R.id.titleFragment);
        title.setText(this.getString(R.string.agenda));
    }
}
