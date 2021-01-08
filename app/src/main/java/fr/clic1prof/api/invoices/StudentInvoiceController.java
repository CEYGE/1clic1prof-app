package fr.clic1prof.api.invoices;

import java.util.List;

import fr.clic1prof.models.document.Document;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentInvoiceController {

    @GET("/student/invoices")
    Call<List<Document>> getInvoices();

    @GET("/student/invoice/{invoiceId}")
    Call<ResponseBody> getInvoice(@Path("invoiceId") int invoiceId);
}
