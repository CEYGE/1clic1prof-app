package fr.clic1prof.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import fr.clic1prof.api.UserController;
import fr.clic1prof.models.session.Credentials;
import fr.clic1prof.models.session.Token;
import fr.clic1prof.models.session.UserSessionModel;
import fr.clic1prof.network.NetworkProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {

    private static final String TAG = "UserRepositoryImpl";

    private final NetworkProvider provider;
    private final UserSessionModel session;

    @Inject
    public UserRepositoryImpl(NetworkProvider provider, UserSessionModel session) {
        this.provider = provider;
        this.session = session;
    }

    @Override
    public void login(MutableLiveData<UserSessionModel> data, Credentials credentials) {

        UserController controller = this.getUserController();

        Call<Token> call = controller.login(credentials);

        call.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {

                if(response.isSuccessful()) {

                    Token token = response.body();

                   UserRepositoryImpl.this.session.open(credentials, token); // Opening a new session in the model.

                    data.postValue(UserRepositoryImpl.this.session); // Inform views that a new session has been opened.

                } else data.setValue(null); // Login not successful.
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "An error occurred while trying to login.", throwable);
            }
        });
    }

    private UserController getUserController() {
        return this.provider.getService(UserController.class);
    }
}
