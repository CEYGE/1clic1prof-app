package fr.clic1prof.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.clic1prof.models.contacts.ContactManager;
import fr.clic1prof.models.contacts.ContactModel;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class ModelModule {

    @Binds
    public abstract ContactModel bindModel(ContactManager manager);
}
