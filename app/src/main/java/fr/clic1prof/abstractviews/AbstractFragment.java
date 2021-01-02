package fr.clic1prof.abstractviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

}
