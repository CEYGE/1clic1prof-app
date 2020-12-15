package fr.clic1prof.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import fr.clic1prof.repositories.ContactRepository;
import fr.clic1prof.repositories.ContactRepositoryImpl;
import fr.clic1prof.repositories.UserRepository;
import fr.clic1prof.repositories.UserRepositoryImpl;

@Module
@InstallIn(ActivityRetainedComponent.class) // Specific for ViewModel.
public abstract class RepositoryModule {

    @Binds // Binding ContactRepository with ContactRepositoryImpl.
    public abstract ContactRepository bindContactRepository(ContactRepositoryImpl repository);

    @Binds // Binding UserRepository with UserRepositoryImpl.
    public abstract UserRepository bindUserRepository(UserRepositoryImpl repository);
}
