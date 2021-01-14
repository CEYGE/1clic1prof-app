package fr.clic1prof.adapter;

import android.view.View;
import android.widget.TextView;

import fr.clic1prof.R;
import fr.clic1prof.models.document.Document;

public class InvoicePayslipViewHolder extends AbstractViewHolder<Document> {
    private int document_id;
    private final TextView textViewName;
    private String document_name;
    private final AbstractAdapter.RecyclerViewClickListener clickListener;

    public InvoicePayslipViewHolder(View itemView, AbstractAdapter.RecyclerViewClickListener clickListener) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textView_docName);
        this.clickListener = clickListener;
    }

    @Override
    public void onBind(Document item) {
        textViewName.setText(item.getName());
        document_id = item.getId();
        document_name = item.getName();
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, document_id,document_name);
    }
}
