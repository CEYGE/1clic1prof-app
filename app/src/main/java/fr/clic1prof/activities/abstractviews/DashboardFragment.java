package fr.clic1prof.activities.abstractviews;

import android.widget.TextView;

import fr.clic1prof.R;

public abstract class DashboardFragment extends AbstractFragment {
    //TODO add specific method for dashboard here

    @Override
    public void onResume() {
        super.onResume();
        TextView title = getActivity().findViewById(R.id.titleFragment);
        title.setText(this.getString(R.string.Dashboard));
    }

}
