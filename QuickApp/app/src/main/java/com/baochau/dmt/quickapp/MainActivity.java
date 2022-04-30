package com.baochau.dmt.quickapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.baochau.dmt.quickapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    AppBarConfiguration appBarConfiguration;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Tao menu
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        binding.navView.setNavigationItemSelectedListener(this);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_home) {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.MainActivity);

        } else if (id == R.id.nav_create) {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.CreateQuestionFragment);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(id==R.id.nav_answer){
            Navigation.findNavController(this,R.id.nav_host_fragment_content_main).navigate(R.id.AnswerFragment);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(id==R.id.nav_history)
        {
            Navigation.findNavController(this,R.id.nav_host_fragment_content_main).navigate(R.id.HistoryFragment);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else {
        }
        return false;
    }

}
