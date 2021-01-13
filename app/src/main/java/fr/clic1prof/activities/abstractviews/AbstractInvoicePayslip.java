package fr.clic1prof.activities.abstractviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.clic1prof.R;
import fr.clic1prof.adapter.AbstractAdapter;
import fr.clic1prof.adapter.InvoicePayslipAdapter;
import fr.clic1prof.viewmodels.invoice.StudentInvoiceViewModel;

public abstract class AbstractInvoicePayslip extends AbstractFragment {
    protected AbstractAdapter.RecyclerViewClickListener clickListener;
    protected InvoicePayslipAdapter adapter;
    protected RecyclerView recyclerView;



}
