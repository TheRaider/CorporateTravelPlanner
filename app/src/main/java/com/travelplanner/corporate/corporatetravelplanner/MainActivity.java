package com.travelplanner.corporate.corporatetravelplanner;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView ivDrawerIcon,ivRefresh;
    TextView tvLogo;
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView tvNavEstName,tvNavEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvLogo = (TextView)findViewById(R.id.tvLogo);
        // Changing Font of Corporate Travel Planner
        Typeface ocrExtendedFont = Typeface.createFromAsset(getAssets(),  "fonts/ocrExtended.TTF");
        tvLogo.setTypeface(ocrExtendedFont);


        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Corp. Travel Planner");
        }

        // Side Navigation Bar
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ivDrawerIcon = (ImageView) findViewById(R.id.ivDrawerIcon);
        ivDrawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_dashboard));


        View headerView = navigationView.getHeaderView(0);
        tvNavEstName = (TextView) headerView.findViewById(R.id.tvNavEstName);
        tvNavEmail = (TextView) headerView.findViewById(R.id.tvNavEmail);


        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);

        final Animation rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotation);

        // Set onClick listener for button press action
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(rotation);
                // Call methods to load data
            }
        });

    }



    public void openDrawer(){
        drawer.openDrawer(Gravity.START);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_dashboard) {
            DashboardFragment dashBoardFragment = new DashboardFragment();
            transaction.replace(R.id.fragment_container, dashBoardFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            openActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void openActivity(final Intent intent){
        drawer.closeDrawer(Gravity.START);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        },100);


    }

    boolean doubleBackToExitPressedOnce = false;
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    finish();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 2000);

            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}

