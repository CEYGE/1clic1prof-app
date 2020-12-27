package fr.clic1prof.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.network.RetrofitNetworkProvider;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class ApplicationModule {

    @Binds
    public abstract NetworkProvider bindNetworkProvider(RetrofitNetworkProvider provider);
}
