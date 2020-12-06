package fr.clic1prof;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.network.RetrofitNetworkProvider;
import fr.clic1prof.network.SessionManager;

@Module
@InstallIn(ActivityComponent.class)
public class ApplicationModule {

    @Provides // Let Hilt create the instance.
    public SessionManager getSessionManager() {
        return new SessionManager();
    }

    @Provides // Let Hilt create the instance.
    public NetworkProvider getNetworkProvider() {
        return new RetrofitNetworkProvider();
    }
}
