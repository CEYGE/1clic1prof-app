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
    /**
     * Creating listener for showing the bottom sheet dialog about course.
     * Layout ID in paremeter
     * @param R_layout_bottom_sheet
     */
    public void setListenerNotebook(int R_layout_bottom_sheet){
        //TODO Match notebook id with notebook_resume
        LinearLayout notebookResume = getActivity().findViewById(R.id.notebook_resume);
        notebookResume.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R_layout_bottom_sheet,
                                (LinearLayout)getActivity().findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }

}
