package fr.clic1prof.activities.abstractviews;

import androidx.recyclerview.widget.RecyclerView;

import fr.clic1prof.adapter.AbstractAdapter;
import fr.clic1prof.adapter.InvoicePayslipAdapter;

public abstract class AbstractInvoicePayslip extends AbstractFragment {

    // Must be private.
    protected AbstractAdapter.RecyclerViewClickListener clickListener;
    protected InvoicePayslipAdapter adapter;
    protected RecyclerView recyclerView;

    /*
    public abstract AbstractAdapter.RecyclerViewClickListener setClickListener();

    public abstract InvoicePayslipAdapter setInvoicePayslipAdapter();

    public abstract RecyclerView setRecyclerView();

    public AbstractAdapter.RecyclerViewClickListener getClickListener() {
        return this.clickListener;
    }

    public InvoicePayslipAdapter getAdapter() {
        return this.adapter;
    }

    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }
     */
}
