package fr.clic1prof.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.clic1prof.models.contacts.ContactManager;
import fr.clic1prof.models.contacts.ContactModel;
import fr.clic1prof.models.user.UserSession;
import fr.clic1prof.models.user.UserSessionModel;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class ModelModule {

    @Binds // Binding UserSessionModel with UserSession.
    public abstract UserSessionModel bindUserSessionModel(UserSession session);

    @Binds // Binding ContactModel with ContactManager.
    public abstract ContactModel bindModel(ContactManager manager);
}
