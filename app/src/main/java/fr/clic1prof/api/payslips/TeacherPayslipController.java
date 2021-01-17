package fr.clic1prof.api.payslips;

import java.util.List;

import fr.clic1prof.models.document.Document;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeacherPayslipController {

    @GET("/teacher/payslips")
    Call<List<Document>> getPayslips();

    @GET("/teacher/payslip/{payslipId}")
    Call<ResponseBody> getPayslip(@Path("payslipId") int payslipId);
}
