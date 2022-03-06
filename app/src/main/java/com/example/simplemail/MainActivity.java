package com.example.simplemail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation);

        //Make the navigation drawer respond when user selects an item.
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer);

        // This adds the three horizontal bar icon to the toolbar.
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.nav_open_drawer,R.string.nav_close_drawer);

        //After creating the drawer toggle add it to the Drawer layout
        //by calling the DrawerLayout addDrawerListener() method, passing
        // the toggle as a parameter
        drawer.addDrawerListener(toggle);

        //Synchronize the icon on the toolbar with the state of the drawer
        // by calling toggle's syncState() method.
        toggle.syncState();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,new InboxFragment());
        transaction.commit();

    }

    //Code to execute when user select an option in the navigation drawer.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        Intent intent=null;
        switch(item.getItemId())
        {
            case R.id.inbox:
                fragment=new InboxFragment();
                break;
            case R.id.draft:
                fragment=new DraftFragment();
                break;
            case R.id.send:
                fragment=new SentFragment();
                break;
            case R.id.trash:
                fragment=new TrashFragment();
                break;
        }
        if(fragment!=null)
        {
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,fragment);
            transaction.commit();
        }
        else
        {
            startActivity(intent);
        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer);
        // This will close the drawer when user slects on of the option.
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //When the Back button gets pressed, this method gets called.
    @Override
    public void onBackPressed() {
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer);
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }
}