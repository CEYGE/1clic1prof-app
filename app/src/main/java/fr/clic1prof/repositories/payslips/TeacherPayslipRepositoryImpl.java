package fr.clic1prof.repositories.payslips;

import androidx.annotation.NonNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.clic1prof.api.payslips.TeacherPayslipController;
import fr.clic1prof.models.document.Document;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.util.DataListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherPayslipRepositoryImpl implements TeacherPayslipRepository {

    private final NetworkProvider provider;

    @Inject
    public TeacherPayslipRepositoryImpl(NetworkProvider provider) {
        this.provider = provider;
    }

    @Override
    public void getPayslips(DataListener<List<Document>> listener) {

        this.getPayslipController().getPayslips().enqueue(new Callback<List<Document>>() {

            @Override
            public void onResponse(@NonNull Call<List<Document>> call, @NonNull Response<List<Document>> response) {

                List<Document> invoices = response.isSuccessful() ? response.body() : new ArrayList<>();

                listener.onSuccess(invoices);
            }

            @Override
            public void onFailure(@NonNull Call<List<Document>> call, @NonNull Throwable throwable) {
                listener.onError("Cannot retrieve teacher's payslips.");
            }
        });
    }

    @Override
    public void getPayslip(int payslipId, DataListener<InputStream> listener) {

        this.getPayslipController().getPayslip(payslipId).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if(response.isSuccessful()) {

                    InputStream stream = response.body().byteStream();
                    listener.onSuccess(stream);

                } else listener.onError("Cannot retrieve student's invoice with id " + payslipId + ".");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                listener.onError("Cannot retrieve teacher's payslip with id " + payslipId + ".");
            }
        });
    }

    public TeacherPayslipController getPayslipController() {
        return this.provider.getService(TeacherPayslipController.class);
    }
}
