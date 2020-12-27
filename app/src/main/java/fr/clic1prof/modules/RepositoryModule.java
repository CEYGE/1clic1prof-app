package fr.clic1prof.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import fr.clic1prof.models.contacts.StudentContact;
import fr.clic1prof.models.contacts.TeacherContact;
import fr.clic1prof.models.profile.StudentProfile;
import fr.clic1prof.models.profile.TeacherProfile;
import fr.clic1prof.repositories.contacts.ContactRepository;
import fr.clic1prof.repositories.contacts.StudentContactRepository;
import fr.clic1prof.repositories.contacts.TeacherContactRepository;
import fr.clic1prof.repositories.profile.ProfileRepository;
import fr.clic1prof.repositories.profile.StudentProfileRepository;
import fr.clic1prof.repositories.profile.TeacherProfileRepository;
import fr.clic1prof.repositories.user.UserRepository;
import fr.clic1prof.repositories.user.UserRepositoryImpl;

@Module
@InstallIn(ActivityRetainedComponent.class) // Specific for ViewModel.
public abstract class RepositoryModule {

    @Binds
    public abstract ContactRepository<TeacherContact> bindStudentContactRepository(StudentContactRepository studentContactRepository);

    @Binds
    public abstract ContactRepository<StudentContact> bindTeacherContactRepository(TeacherContactRepository teacherContactRepository);

    @Binds
    public abstract ProfileRepository<TeacherProfile> bindTeacherProfileRepository(TeacherProfileRepository teacherProfileRepository);

    @Binds
    public abstract ProfileRepository<StudentProfile> bindStudentProfileRepository(StudentProfileRepository studentProfileRepository);

    @Binds
    public abstract UserRepository bindUserRepository(UserRepositoryImpl repository);
}
