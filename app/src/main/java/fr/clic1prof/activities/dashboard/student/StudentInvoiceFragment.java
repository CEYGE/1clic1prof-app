package fr.clic1prof.activities.dashboard.student;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.clic1prof.R;
import fr.clic1prof.activities.abstractviews.AbstractInvoicePayslip;
import fr.clic1prof.adapter.AbstractAdapter;
import fr.clic1prof.adapter.InvoicePayslipAdapter;
import fr.clic1prof.models.document.Document;
import fr.clic1prof.viewmodels.invoice.StudentInvoiceViewModel;

public class StudentInvoiceFragment extends AbstractInvoicePayslip {
    protected StudentInvoiceViewModel viewModel;

    public static StudentDashboardFragment newInstance() {
        return new StudentDashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflateFragment(R.layout.payslip_invoice, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.recycler_invoicePayslip);
        viewModel = new ViewModelProvider(requireActivity()).get(StudentInvoiceViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = getActivity().findViewById(R.id.titleFragment);
        title.setText(this.getString(R.string.invoice));
        viewModel.fetchInvoices();

        viewModel.getdocumentsLiveData().observe(this, new Observer<List<Document>>() {
            @Override
            public void onChanged(List<Document> invoices) {
                if (invoices != null){
                    setOnClickListener();
                    adapter = new InvoicePayslipAdapter(getActivity().getApplicationContext(), clickListener);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
                    adapter.setItems(invoices);
                }
            }
        });

    }

    private void setOnClickListener() {
        clickListener = (v, document_id, document_name) -> viewModel.fetchInvoiceContent(document_id, document_name, getActivity());
    }
}
