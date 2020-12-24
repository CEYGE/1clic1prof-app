package fr.clic1prof.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import fr.clic1prof.R;
import fr.clic1prof.viewmodels.HomeViewModel;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private HomeViewModel ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // TODO Change the way to hide
        setContentView(R.layout.activity_home);

        //TODO this.viewModel = new ViewModelProvider(this).get(.class);

        this.setListenerMenu();
    }

    /**
     * Listener for the navigation menu
     */
    public void setListenerMenu() {

        DrawerLayout drawerLayout = super.findViewById(R.id.drawerLayout);
        // For menu navigation buttons
        NavigationView navigationView = findViewById(R.id.drawer_navigation);
        navigationView.setNavigationItemSelectedListener(this);

        // For menu button
        super.findViewById(R.id.imgMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawerLayout = super.findViewById(R.id.drawerLayout);
        NavController navController = Navigation.findNavController(this, R.id.FragmentContainer);

        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.nav_dashboard) navController.navigate(R.id.action_global_homeFragment);
        else if (item.getItemId() == R.id.nav_agenda) navController.navigate(R.id.action_global_agendaFragment);
        else if (item.getItemId() == R.id.nav_notebook) navController.navigate(R.id.action_global_notebookFragment);

        return true;
    }
}