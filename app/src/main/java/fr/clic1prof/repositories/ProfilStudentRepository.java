package fr.clic1prof.repositories;

import androidx.lifecycle.MutableLiveData;

import fr.clic1prof.model.ProfilInformation;

public interface ProfilStudentRepository {

    void getInformation(MutableLiveData<ProfilInformation> data);
}
