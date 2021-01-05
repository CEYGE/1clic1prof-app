package fr.clic1prof.repositories.invoices;

import androidx.annotation.NonNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.clic1prof.api.invoices.StudentInvoiceController;
import fr.clic1prof.models.document.Document;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.util.DataListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentInvoiceRepositoryImpl implements StudentInvoiceRepository {

    private final NetworkProvider provider;

    @Inject
    public StudentInvoiceRepositoryImpl(NetworkProvider provider) {
        this.provider = provider;
    }

    @Override
    public void getInvoices(DataListener<List<Document>> listener) {

        this.getInvoiceController().getInvoices().enqueue(new Callback<List<Document>>() {

            @Override
            public void onResponse(@NonNull Call<List<Document>> call, @NonNull Response<List<Document>> response) {

                List<Document> invoices = response.isSuccessful() ? response.body() : new ArrayList<>();

                listener.onSuccess(invoices);
            }

            @Override
            public void onFailure(@NonNull Call<List<Document>> call, @NonNull Throwable throwable) {
                listener.onError("Cannot retrieve student's invoices.");
            }
        });
    }

    @Override
    public void getInvoice(int invoiceId, DataListener<InputStream> listener) {

        this.getInvoiceController().getInvoice(invoiceId).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if(response.isSuccessful()) {

                    InputStream stream = response.body().byteStream();
                    listener.onSuccess(stream);

                } else listener.onError("Cannot retrieve student's invoice with id " + invoiceId + ".");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                listener.onError("Cannot retrieve student's invoice with id " + invoiceId + ".");
            }
        });
    }

    public StudentInvoiceController getInvoiceController() {
        return this.provider.getService(StudentInvoiceController.class);
    }
}
