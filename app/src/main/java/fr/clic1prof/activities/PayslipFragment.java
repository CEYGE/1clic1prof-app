package fr.clic1prof.activities;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.clic1prof.R;
import fr.clic1prof.abstractviews.AbstractFragment;
import fr.clic1prof.viewmodels.PayslipViewModel;

public class PayslipFragment extends AbstractFragment {


    public static PayslipFragment newInstance() {
        return new PayslipFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.payslip_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}