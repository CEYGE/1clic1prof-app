package fr.clic1prof.activities.abstractviews;

import androidx.recyclerview.widget.RecyclerView;

import fr.clic1prof.adapter.AbstractAdapter;
import fr.clic1prof.adapter.InvoicePayslipAdapter;

public abstract class AbstractInvoicePayslip extends AbstractFragment {

    // Must be private.
    private AbstractAdapter.RecyclerViewClickListener clickListener;
    private InvoicePayslipAdapter adapter;
    private RecyclerView recyclerView;


    public void setClickListener(AbstractAdapter.RecyclerViewClickListener listener){
        this.clickListener=listener;
    }

    public void setAdapter(InvoicePayslipAdapter adapter){
        this.adapter = adapter;
    }

    public void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    public AbstractAdapter.RecyclerViewClickListener getClickListener() {
        return this.clickListener;
    }

    public InvoicePayslipAdapter getAdapter() {
        return this.adapter;
    }

    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

}
