package fr.clic1prof.repositories.user;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import fr.clic1prof.api.UserController;
import fr.clic1prof.models.session.Credentials;
import fr.clic1prof.models.session.Token;
import fr.clic1prof.models.session.UserSessionModel;
import fr.clic1prof.models.registration.Registration;
import fr.clic1prof.network.NetworkProvider;
import fr.clic1prof.network.authentication.AuthenticationRequest;
import fr.clic1prof.network.authentication.AuthenticationResponse;
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
    public LiveData<Boolean> login(AuthenticationRequest request) {

        MutableLiveData<Boolean> data = new MutableLiveData<>();
        UserController controller = this.getUserController();

        controller.login(request).enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(@NonNull Call<AuthenticationResponse> call, @NonNull Response<AuthenticationResponse> response) {

                if(response.isSuccessful()) {

                    AuthenticationResponse authResponse = response.body();

                    Credentials credentials = new Credentials(request.getEmail(), request.getPassword());
                    Token token = new Token(authResponse.getToken());

                    // Opening a new session in the model.
                    UserRepositoryImpl.this.session.open(authResponse.getType(), credentials, token);

                    data.postValue(true); // Inform views that a new session has been opened.

                } else data.postValue(false); // Login not successful.
            }

            @Override
            public void onFailure(@NonNull Call<AuthenticationResponse> call, @NonNull Throwable throwable) {
                data.postValue(false); // Login failure.
                Log.e(TAG, "An error occurred while trying to login.", throwable);
            }
        });
        return data;
    }

    @Override
    public LiveData<Boolean> register(Registration registration) {

        MutableLiveData<Boolean> data = new MutableLiveData<>();
        UserController controller = this.getUserController();

        controller.register(registration).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                data.postValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                data.postValue(false);
                Log.e(TAG, "An error occurred while trying to create a new account.");
            }
        });
        return data;
    }

    private UserController getUserController() {
        return this.provider.getService(UserController.class);
    }
}
