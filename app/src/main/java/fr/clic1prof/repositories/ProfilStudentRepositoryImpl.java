package fr.clic1prof.repositories;

import androidx.lifecycle.MutableLiveData;
import fr.clic1prof.models.user.ProfilInformation;
import fr.clic1prof.network.NetworkProvider;

public class ProfilStudentRepositoryImpl implements ProfilStudentRepository{

    private final NetworkProvider provider;

    ProfilStudentRepositoryImpl(NetworkProvider provider){
        this.provider = provider;
    }

    /*
    @Override
    public void getInformation(MutableLiveData<ProfilInformation> data) {
    }*/
}
