package com.lilgagatraxx09.traxxion09tunnelpro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    
    private static final int SPLASH_DURATION = 10000; // 10 seconds
    private ProgressBar progressBar;
    private TextView loadingText;
    private TextView creatorText;
    private ImageView logo;
    private Handler handler;
    private Runnable progressRunnable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        initializeViews();
        startLoadingAnimation();
        startProgressBar();
        
        // Navigate to MainActivity after 10 seconds
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
    
    private void initializeViews() {
        logo = findViewById(R.id.logo);
        progressBar = findViewById(R.id.progressBar);
        loadingText = findViewById(R.id.loadingText);
        creatorText = findViewById(R.id.creatorText);
        
        // Set creator text
        creatorText.setText("Created by LilGagaTraxx09");
    }
    
    private void startLoadingAnimation() {
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        logo.startAnimation(fadeIn);
        
        Animation slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        creatorText.startAnimation(slideUp);
    }
    
    private void startProgressBar() {
        progressBar.setMax(100);
        progressBar.setProgress(0);
        
        handler = new Handler();
        progressRunnable = new Runnable() {
            int progress = 0;
            
            @Override
            public void run() {
                if (progress <= 100) {
                    progressBar.setProgress(progress);
                    updateLoadingText(progress);
                    progress++;
                    handler.postDelayed(this, SPLASH_DURATION / 100);
                }
            }
        };
        
        handler.post(progressRunnable);
    }
    
    private void updateLoadingText(int progress) {
        if (progress < 25) {
            loadingText.setText("Initializing Traxxion09 Tunnel Pro...");
        } else if (progress < 50) {
            loadingText.setText("Connecting to Zimbabwean servers...");
        } else if (progress < 75) {
            loadingText.setText("Loading Econet & NetOne servers...");
        } else if (progress < 95) {
            loadingText.setText("Preparing VPN service...");
        } else {
            loadingText.setText("Ready to launch!");
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && progressRunnable != null) {
            handler.removeCallbacks(progressRunnable);
        }
    }
}