package fr.clic1prof.viewmodels.contacts;

import androidx.hilt.lifecycle.ViewModelInject;

import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.repositories.contacts.ContactRepository;

public class StudentContactActivityViewModel extends ContactActivityViewModel<TeacherContact> {

    @ViewModelInject
    public StudentContactActivityViewModel(ContactRepository<TeacherContact> repository) {
        super(repository);
    }
}
