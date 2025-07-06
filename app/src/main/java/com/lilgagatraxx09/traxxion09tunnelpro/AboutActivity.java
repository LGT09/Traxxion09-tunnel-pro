package com.lilgagatraxx09.traxxion09tunnelpro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {
    
    private TextView appNameText;
    private TextView versionText;
    private TextView creatorText;
    private TextView contactText;
    private TextView descriptionText;
    private Button contactButton1;
    private Button contactButton2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        
        setupToolbar();
        initializeViews();
        setupContent();
        setupClickListeners();
    }
    
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    private void initializeViews() {
        appNameText = findViewById(R.id.appNameText);
        versionText = findViewById(R.id.versionText);
        creatorText = findViewById(R.id.creatorText);
        contactText = findViewById(R.id.contactText);
        descriptionText = findViewById(R.id.descriptionText);
        contactButton1 = findViewById(R.id.contactButton1);
        contactButton2 = findViewById(R.id.contactButton2);
    }
    
    private void setupContent() {
        appNameText.setText("Traxxion09 tunnel pro");
        versionText.setText("Version 1.0.0");
        creatorText.setText("Created by: Vincent Ganiza LilGagaTraxx09");
        contactText.setText("Contact Information:");
        
        String description = "Traxxion09 tunnel pro is a premium VPN application designed specifically for Zimbabwe. " +
                "It provides secure and fast connections through dedicated Econet and NetOne servers.\n\n" +
                "Features:\n" +
                "• Multiple Zimbabwean servers (Econet & NetOne)\n" +
                "• Offline mode capability\n" +
                "• Import/Export configuration\n" +
                "• Auto-connect functionality\n" +
                "• Secure encryption protocols\n" +
                "• Real-time connection monitoring\n\n" +
                "This app works seamlessly with Zimbabwe's major network providers and " +
                "ensures your internet privacy and security.";
        
        descriptionText.setText(description);
        
        contactButton1.setText("Call: 263780078177");
        contactButton2.setText("Call: 263716857999");
    }
    
    private void setupClickListeners() {
        contactButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("263780078177");
            }
        });
        
        contactButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("263716857999");
            }
        });
    }
    
    private void makePhoneCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}