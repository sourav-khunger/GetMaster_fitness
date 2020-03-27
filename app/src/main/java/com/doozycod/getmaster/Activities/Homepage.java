package com.doozycod.getmaster.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.doozycod.getmaster.Fragments.HomeFragment;
import com.doozycod.getmaster.Fragments.NavigationFragment;
import com.doozycod.getmaster.R;

public class Homepage extends AppCompatActivity {
    ImageView navigationOpen, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        navigationOpen = findViewById(R.id.openNavigation);
        backButton = findViewById(R.id.backfragment);
        navigationOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new NavigationFragment(), "navigationfragment")
                        .addToBackStack("")
                        .commit();
                backButton.setVisibility(View.VISIBLE);
                navigationOpen.setVisibility(View.GONE);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment(), "").commit();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        if (navigationOpen.getVisibility() == View.GONE) {
//            navigationOpen.setVisibility(View.VISIBLE);
//            backButton.setVisibility(View.GONE);
//
//        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {

                navigationOpen.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.GONE);
            }
        } else {
            super.onBackPressed();
        }
        Log.e("On Back Pressed", "onBackPressed: " + getSupportFragmentManager().getBackStackEntryCount());

    }
}
