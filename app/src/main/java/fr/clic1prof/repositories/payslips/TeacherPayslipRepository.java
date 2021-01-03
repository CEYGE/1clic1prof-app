package fr.clic1prof.repositories.payslips;

import java.io.InputStream;
import java.util.List;

import fr.clic1prof.models.document.Document;
import fr.clic1prof.util.DataListener;

public interface TeacherPayslipRepository {

    void getPayslips(DataListener<List<Document>> listener);

    void getPayslip(int payslipId, DataListener<InputStream> listener);
}
