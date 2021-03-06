package fr.clic1prof.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.chip.ChipGroup;

import dagger.hilt.android.AndroidEntryPoint;
import fr.clic1prof.R;
import fr.clic1prof.util.ErrorEntrie;
import fr.clic1prof.util.LoadingDialog;
import fr.clic1prof.models.registration.Registration;
import fr.clic1prof.models.registration.RegistrationType;
import fr.clic1prof.viewmodels.log.InscriptionActivityViewModel;
import fr.clic1prof.viewmodels.ResultType;

@AndroidEntryPoint
public class InscriptionActivity extends AppCompatActivity {

    private InscriptionActivityViewModel viewModel;
    private ErrorEntrie error;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_page);

        this.viewModel = new ViewModelProvider(this).get(InscriptionActivityViewModel.class);
        this.dialog = new LoadingDialog(this);
        this.error = new ErrorEntrie(findViewById(R.id.errorInvisibleViewInscription));

        //Setup observer
        this.setInscriptionObserver();
    }

    private void setInscriptionObserver(){

        this.viewModel.getLiveData().observe(this ,result -> {

            if(result.getType() == ResultType.SUCCESS) {
                this.dialog.dismissDialog();
                this.sendToLogIn();
            }else if(result.getType() == ResultType.ERROR){
                this.dialog.dismissDialog();
                this.error.setText(R.string.Dialog_error);
                this.error.showError();
            }else {
                this.dialog.launchLoadingDialog();
                this.dialog.startLoadingDialog();
                this.dialog.inscriptionText();
            }
        });
    }

    /**
     * Send user to Login activity
     * @param view View using directly this function
     */
    public void sendToLogIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void sendToLogIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void inscription(View view) {
        this.error.cleanse();
        if(isStudent()) {

            EditText lastView = findViewById(R.id.InscriptionlastNameText);
            EditText firstView = findViewById(R.id.InscriptionfirstNameText);
            EditText mailView = findViewById(R.id.InscriptionmailText);
            EditText passwordView = findViewById(R.id.InscriptionpasswordText);

            String lastName = lastView.getText().toString();
            String firstName = firstView.getText().toString();
            String email = mailView.getText().toString();
            String password = passwordView.getText().toString();

            Registration register = new Registration(firstName, lastName, email, password, RegistrationType.STUDENT);
            this.viewModel.register(register);
        }else{
            error.setText(R.string.Dialog_teacher);
            error.showError();
        }
    }


    public void visiblePassword(View view){
        EditText password =findViewById(R.id.InscriptionpasswordText);
        boolean flag = password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if(flag){
            password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        }
    }

    private boolean isStudent(){
        ChipGroup chipGroup = findViewById(R.id.ChipGroup);
        int identifier = chipGroup.getCheckedChipId();
        return identifier - 2131296393 == 0;
    }
}