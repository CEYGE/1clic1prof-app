package fr.clic1prof.abstractviews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import fr.clic1prof.R;

public abstract class NotebookFragment extends AbstractFragment {

    @Override
    public void onResume() {
        super.onResume();
        TextView title = getActivity().findViewById(R.id.titleFragment);
        title.setText(this.getString(R.string.notebook));
    }

    //TODO add specific method for Notebook here


}
