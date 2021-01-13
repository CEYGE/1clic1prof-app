package fr.clic1prof.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import fr.clic1prof.R;
import fr.clic1prof.models.document.Document;

public class InvoicePayslipAdapter extends AbstractAdapter<Document, InvoicePayslipViewHolder> {

    private final RecyclerViewClickListener clickListener;

    public InvoicePayslipAdapter(Context context, RecyclerViewClickListener clickListener) {
        super(context,clickListener);
        this.clickListener = clickListener;
    }

    @NotNull
    @Override
    public InvoicePayslipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InvoicePayslipViewHolder(inflate(R.layout.container_payslip_bill, parent) , clickListener);
    }
}