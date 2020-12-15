package fr.clic1prof.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import fr.clic1prof.api.UserController;
import fr.clic1prof.models.user.Credentials;
import fr.clic1prof.models.user.Registration;
import fr.clic1prof.models.user.Token;
import fr.clic1prof.models.user.UserSessionModel;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.viewmodels.Result;
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
    public LiveData<Token> login(Credentials credentials) {

        MutableLiveData<Token> data = new MutableLiveData<>();

        UserController controller = this.getUserController();

        Call<Token> call = controller.login(credentials);

        call.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {

                if(response.isSuccessful()) {

                    Token token = response.body();

                    UserRepositoryImpl.this.session.open(credentials, token); // Opening a new session in the model.

                    data.postValue(token); // Inform views that a new session has been opened.

                } else data.postValue(null); // Login not successful.
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable throwable) {
                data.postValue(null);
                Log.e(TAG, "An error occurred while trying to login.", throwable);
            }
        });
        return data;
    }

    private UserController getUserController() {
        return this.provider.getService(UserController.class);
    }
}
