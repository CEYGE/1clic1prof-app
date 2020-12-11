package fr.clic1prof.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.clic1prof.models.user.UserSession;
import fr.clic1prof.models.user.UserSessionModel;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.network.RetrofitNetworkProvider;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class ApplicationModule {

    @Binds // Binding UserSessionModel with UserSession.
    public abstract UserSessionModel bindUserSessionModel(UserSession session);

    @Binds // Binding NetworkProvider with RetrofitNetworkProvider.
    public abstract NetworkProvider bindNetworkProvider(RetrofitNetworkProvider provider);
}
