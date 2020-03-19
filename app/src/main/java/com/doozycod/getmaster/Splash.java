package com.doozycod.getmaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.VelocityTracker;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.doozycod.getmaster.Activities.MainActivity;

public class Splash extends AppCompatActivity {
    ImageView logo;
    Animation animation;
    VelocityTracker vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.splash_logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, 3000);
        /*SpringAnimation springAnimation = new SpringAnimation(logo, DynamicAnimation.TRANSLATION_Y);
        springAnimation.setStartVelocity(-12000);
        SpringForce springForce = new SpringForce();
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springForce.setStiffness(SpringForce.STIFFNESS_LOW);
        springForce.setFinalPosition(0);
        springAnimation.setSpring(springForce);
        springAnimation.start();
        springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {

            }
        });*/
    }
}
