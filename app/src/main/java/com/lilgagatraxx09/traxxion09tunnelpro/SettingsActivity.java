package com.lilgagatraxx09.traxxion09tunnelpro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {
    
    private CheckBox autoConnectCheckbox;
    private CheckBox offlineModeCheckbox;
    private CheckBox notificationsCheckbox;
    private CheckBox autoReconnectCheckbox;
    private SeekBar connectionTimeoutSeekBar;
    private TextView timeoutValueText;
    private Spinner protocolSpinner;
    private SharedPreferences sharedPreferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        setupToolbar();
        initializeViews();
        loadSettings();
        setupListeners();
    }
    
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    private void initializeViews() {
        autoConnectCheckbox = findViewById(R.id.autoConnectCheckbox);
        offlineModeCheckbox = findViewById(R.id.offlineModeCheckbox);
        notificationsCheckbox = findViewById(R.id.notificationsCheckbox);
        autoReconnectCheckbox = findViewById(R.id.autoReconnectCheckbox);
        connectionTimeoutSeekBar = findViewById(R.id.connectionTimeoutSeekBar);
        timeoutValueText = findViewById(R.id.timeoutValueText);
        protocolSpinner = findViewById(R.id.protocolSpinner);
        
        sharedPreferences = getSharedPreferences("TraxxionSettings", MODE_PRIVATE);
    }
    
    private void loadSettings() {
        autoConnectCheckbox.setChecked(sharedPreferences.getBoolean("auto_connect", false));
        offlineModeCheckbox.setChecked(sharedPreferences.getBoolean("offline_mode", true));
        notificationsCheckbox.setChecked(sharedPreferences.getBoolean("notifications", true));
        autoReconnectCheckbox.setChecked(sharedPreferences.getBoolean("auto_reconnect", true));
        
        int timeout = sharedPreferences.getInt("connection_timeout", 30);
        connectionTimeoutSeekBar.setProgress(timeout);
        timeoutValueText.setText(timeout + " seconds");
        
        int protocol = sharedPreferences.getInt("protocol", 0);
        protocolSpinner.setSelection(protocol);
    }
    
    private void setupListeners() {
        autoConnectCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettings();
            Toast.makeText(this, "Auto-connect " + (isChecked ? "enabled" : "disabled"), 
                Toast.LENGTH_SHORT).show();
        });
        
        offlineModeCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettings();
            Toast.makeText(this, "Offline mode " + (isChecked ? "enabled" : "disabled"), 
                Toast.LENGTH_SHORT).show();
        });
        
        notificationsCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettings();
            Toast.makeText(this, "Notifications " + (isChecked ? "enabled" : "disabled"), 
                Toast.LENGTH_SHORT).show();
        });
        
        autoReconnectCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettings();
            Toast.makeText(this, "Auto-reconnect " + (isChecked ? "enabled" : "disabled"), 
                Toast.LENGTH_SHORT).show();
        });
        
        connectionTimeoutSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 10) progress = 10;
                if (progress > 120) progress = 120;
                timeoutValueText.setText(progress + " seconds");
            }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                saveSettings();
                Toast.makeText(SettingsActivity.this, "Connection timeout updated", 
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void saveSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("auto_connect", autoConnectCheckbox.isChecked());
        editor.putBoolean("offline_mode", offlineModeCheckbox.isChecked());
        editor.putBoolean("notifications", notificationsCheckbox.isChecked());
        editor.putBoolean("auto_reconnect", autoReconnectCheckbox.isChecked());
        editor.putInt("connection_timeout", connectionTimeoutSeekBar.getProgress());
        editor.putInt("protocol", protocolSpinner.getSelectedItemPosition());
        editor.apply();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        saveSettings();
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}