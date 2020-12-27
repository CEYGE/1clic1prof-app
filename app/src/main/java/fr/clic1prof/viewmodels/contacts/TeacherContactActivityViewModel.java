package fr.clic1prof.viewmodels.contacts;

import androidx.hilt.lifecycle.ViewModelInject;

import fr.clic1prof.models.contacts.StudentContact;
import fr.clic1prof.repositories.contacts.ContactRepository;

public class TeacherContactActivityViewModel extends ContactActivityViewModel<StudentContact> {

    @ViewModelInject
    public TeacherContactActivityViewModel(ContactRepository<StudentContact> repository) {
        super(repository);
    }
}
