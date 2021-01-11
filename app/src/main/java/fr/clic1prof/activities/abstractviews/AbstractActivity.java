package fr.clic1prof.activities.abstractviews;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;

import fr.clic1prof.R;

public abstract class AbstractActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        super.findViewById(R.id.imgMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        DrawerLayout drawerLayout = super.findViewById(R.id.drawerLayout);
        NavController navController = Navigation.findNavController(this, R.id.FragmentContainer);

        drawerLayout.closeDrawer(GravityCompat.START);
        switch (id){
            case R.id.nav_dashboard :
                navController.navigate(R.id.action_global_dashboardFragment);
                break;
            case R.id.nav_agenda :
                navController.navigate(R.id.action_global_agendaFragment);
                break;
            case R.id.nav_notebook :
                navController.navigate(R.id.action_global_notebookFragment);
                break;
            case R.id.nav_invoice :
                navController.navigate(R.id.action_global_payslipFragment);
                break;
            default :
                break;
        }
        return true;
    }

}



