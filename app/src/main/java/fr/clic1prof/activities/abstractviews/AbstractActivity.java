package fr.clic1prof.activities.abstractviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import fr.clic1prof.R;
import fr.clic1prof.activities.dashboard.student.StudentInvoiceFragment;
import fr.clic1prof.activities.login.LoginActivity;
import fr.clic1prof.models.profile.Profile;
import fr.clic1prof.models.session.UserSession;
import fr.clic1prof.viewmodels.profile.profileV2.ProfileViewModel;

public abstract class AbstractActivity<T extends Profile> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    public UserSession model;

    private ProfileViewModel<T> viewModel;

    public abstract void sendToProfile(View view);

    protected abstract void setProfileObserver();

    protected abstract Class<? extends ProfileViewModel<T>> getProfileViewModelClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(this.getProfileViewModelClass());
        this.setProfileObserver();
    }

    /**
     * Listener for the navigation menu
     */
    public void setListenerMenu() {

        DrawerLayout drawerLayout = super.findViewById(R.id.drawerLayout);
        // For menu navigation buttons
        NavigationView navigationView = findViewById(R.id.drawer_navigation);
        navigationView.setNavigationItemSelectedListener(this);

        // To display menu navigation
        super.findViewById(R.id.imgMenu).setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
            this.getViewModel().getProfile();
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        DrawerLayout drawerLayout = super.findViewById(R.id.drawerLayout);
        NavController navController = Navigation.findNavController(this, R.id.FragmentContainer);

        drawerLayout.closeDrawer(GravityCompat.START);
        switch (id){
            case R.id.nav_dashboard:
                navController.navigate(R.id.action_global_dashboardFragment);
                break;
            case R.id.nav_agenda:
                navController.navigate(R.id.action_global_agendaFragment);
                break;
            case R.id.nav_notebook:
                navController.navigate(R.id.action_global_notebookFragment);
                break;
            case R.id.nav_invoice:
                // Resolving the error "No adapter attached", skipping layout occurring when navigate to the invoice / payslip
                // when being already to the invoice / payslip.
                // If already in invoice / payslip, it does not navigate again.
                int currentDestination = navController.getCurrentDestination().getId();
                if ((currentDestination != R.id.studentInvoiceFragment)||(currentDestination != R.id.teacherPayslipFragment)){
                    navController.navigate(R.id.action_global_payslipInvoiceFragment);
                }else{ return false;}
                break;
            case R.id.nav_teacher:
                navController.navigate(R.id.action_global_contactActivity);
                break;
            case R.id.nav_disconnection:
                Intent intent = new Intent(this, LoginActivity.class);
                model.close();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default :
                break;
        }
        return true;
    }

    public ProfileViewModel<T> getViewModel() {
        return viewModel;
    }
}



