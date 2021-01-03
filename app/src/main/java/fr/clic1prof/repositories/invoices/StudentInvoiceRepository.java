package fr.clic1prof.repositories.invoices;

import java.io.InputStream;
import java.util.List;

import fr.clic1prof.models.document.Document;
import fr.clic1prof.util.DataListener;

public interface StudentInvoiceRepository {

    void getInvoices(DataListener<List<Document>> listener);

    void getInvoice(int invoiceId, DataListener<InputStream> listener);
}
