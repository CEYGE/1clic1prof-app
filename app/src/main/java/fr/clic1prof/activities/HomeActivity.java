package fr.clic1prof.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import fr.clic1prof.R;

public class HomeActivity extends AppCompatActivity {
    //TODO Ajouter viewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // TODO Change the way to hide
        setContentView(R.layout.activity_home);
        //TODO this.viewModel = new ViewModelProvider(this).get(.class);

        this.setMenuImg();
    }

    /**
     * Listener for the navigation menu
     */
    public void setMenuImg() {
        ImageView menu = super.findViewById(R.id.imgMenu);

        DrawerLayout drawerLayout = super.findViewById(R.id.drawerLayout);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }




}