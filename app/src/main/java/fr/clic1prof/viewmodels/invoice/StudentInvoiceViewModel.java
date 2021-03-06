package fr.clic1prof.viewmodels.invoice;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Nullable;

import fr.clic1prof.models.document.Document;
import fr.clic1prof.repositories.invoices.StudentInvoiceRepositoryImpl;
import fr.clic1prof.util.DataListener;

public class StudentInvoiceViewModel extends ViewModel {

    protected MutableLiveData<List<Document>> documentsLiveData = new MutableLiveData<>() ;
    protected MutableLiveData<List<Document>> errorLiveData = new MutableLiveData<>();
    private final StudentInvoiceRepositoryImpl repository;


    @ViewModelInject
    public StudentInvoiceViewModel(StudentInvoiceRepositoryImpl repository) {
        this.repository = repository;
    }

    public void fetchInvoices(){
        this.getRepository().getInvoices(new DataListener<List<Document>>(){
            @Override
            public void onSuccess(@Nullable List<Document> value) {
                StudentInvoiceViewModel.this.documentsLiveData.postValue(value);

            }

            @Override
            public void onError(String message) {
                StudentInvoiceViewModel.this.errorLiveData.postValue(null);
            }
        });
    }

    public void fetchInvoiceContent(int id, String name,Context context){
        this.getRepository().getInvoice(id, new DataListener<InputStream>() {
            @Override
            public void onSuccess(@Nullable InputStream value) {
                FileCreation fc = new FileCreation(context, value, name);
                fc.askPermissionAndWriteFile();
            }

            @Override
            public void onError(String message) {
                StudentInvoiceViewModel.this.errorLiveData.postValue(null);
            }
        });
    }


    public StudentInvoiceRepositoryImpl getRepository() { return repository; }

    public LiveData<List<Document>> getdocumentsLiveData() { return this.documentsLiveData; }


}
