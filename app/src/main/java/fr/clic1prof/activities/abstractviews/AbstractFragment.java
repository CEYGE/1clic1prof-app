package fr.clic1prof.activities.abstractviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import fr.clic1prof.R;

/**
 * Contain method permitting to create a fragment with the corresponding layout
 */
public abstract class AbstractFragment extends Fragment {


    @Override
    public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Permit to handle layout difference in child
    protected View inflateFragment(int resId, LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(resId, container, false);
    }

    /**
     * Creating listener for showing the bottom sheet dialog about course.
     * Layout ID in paremeter
     * @param R_layout_bottom_sheet
     */
    public void setListenerCourse(int R_layout_bottom_sheet,View view){
        //TODO Match notebook id with notebook_resume
        View notebookResume = view.findViewById(R.id.notebook_resume);
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
