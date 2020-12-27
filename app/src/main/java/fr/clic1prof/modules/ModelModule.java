package fr.clic1prof.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.clic1prof.models.contacts.ContactHandler;
import fr.clic1prof.models.contacts.ContactHandlerImpl;
import fr.clic1prof.models.contacts.StudentContact;
import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.models.session.UserSession;
import fr.clic1prof.models.session.UserSessionModel;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class ModelModule {

    @Binds
    public abstract UserSessionModel bindUserSessionModel(UserSession session);

    @Binds
    public abstract ContactHandler<StudentContact> bindStudentContactHandler(ContactHandlerImpl<StudentContact> handler);

    @Binds
    public abstract ContactHandler<TeacherContact> bindTeacherContactHandler(ContactHandlerImpl<TeacherContact> handler);
}
