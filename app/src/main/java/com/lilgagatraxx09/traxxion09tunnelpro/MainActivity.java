package com.lilgagatraxx09.traxxion09tunnelpro;

import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    
    private static final int VPN_REQUEST_CODE = 1;
    
    private Switch vpnSwitch;
    private Spinner serverSpinner;
    private Button connectButton;
    private TextView statusText;
    private TextView serverInfoText;
    private String[] servers;
    private String selectedServer;
    private boolean isVpnConnected = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupToolbar();
        initializeViews();
        setupServerSpinner();
        setupClickListeners();
        updateUI();
    }
    
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Traxxion09 Tunnel Pro");
    }
    
    private void initializeViews() {
        vpnSwitch = findViewById(R.id.vpnSwitch);
        serverSpinner = findViewById(R.id.serverSpinner);
        connectButton = findViewById(R.id.connectButton);
        statusText = findViewById(R.id.statusText);
        serverInfoText = findViewById(R.id.serverInfoText);
    }
    
    private void setupServerSpinner() {
        servers = new String[]{
            "Econet Zimbabwe - Harare",
            "Econet Zimbabwe - Bulawayo",
            "NetOne Zimbabwe - Harare",
            "NetOne Zimbabwe - Bulawayo",
            "Econet Zimbabwe - Mutare",
            "NetOne Zimbabwe - Gweru",
            "Econet Zimbabwe - Masvingo",
            "NetOne Zimbabwe - Chinhoyi"
        };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
            android.R.layout.simple_spinner_item, servers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serverSpinner.setAdapter(adapter);
        
        serverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedServer = servers[position];
                updateServerInfo();
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedServer = servers[0];
            }
        });
        
        // Set default selection
        selectedServer = servers[0];
        updateServerInfo();
    }
    
    private void setupClickListeners() {
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVpnConnected) {
                    disconnectVpn();
                } else {
                    connectVpn();
                }
            }
        });
        
        vpnSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked && !isVpnConnected) {
                connectVpn();
            } else if (!isChecked && isVpnConnected) {
                disconnectVpn();
            }
        });
    }
    
    private void connectVpn() {
        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent, VPN_REQUEST_CODE);
        } else {
            startVpnService();
        }
    }
    
    private void disconnectVpn() {
        Intent intent = new Intent(this, com.lilgagatraxx09.traxxion09tunnelpro.VpnService.class);
        intent.setAction("DISCONNECT");
        startService(intent);
        
        isVpnConnected = false;
        updateUI();
        Toast.makeText(this, "VPN Disconnected", Toast.LENGTH_SHORT).show();
    }
    
    private void startVpnService() {
        Intent intent = new Intent(this, com.lilgagatraxx09.traxxion09tunnelpro.VpnService.class);
        intent.putExtra("server", selectedServer);
        startService(intent);
        
        isVpnConnected = true;
        updateUI();
        Toast.makeText(this, "Connected to " + selectedServer, Toast.LENGTH_SHORT).show();
    }
    
    private void updateUI() {
        if (isVpnConnected) {
            vpnSwitch.setChecked(true);
            connectButton.setText("DISCONNECT");
            connectButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            statusText.setText("Connected");
            statusText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            vpnSwitch.setChecked(false);
            connectButton.setText("CONNECT");
            connectButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
            statusText.setText("Disconnected");
            statusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }
    
    private void updateServerInfo() {
        if (selectedServer != null) {
            String info = "Server: " + selectedServer + "\n";
            info += "Status: Available\n";
            info += "Latency: " + (50 + (int)(Math.random() * 50)) + "ms\n";
            info += "Location: Zimbabwe";
            serverInfoText.setText(info);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VPN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                startVpnService();
            } else {
                Toast.makeText(this, "VPN permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_import_export) {
            Intent intent = new Intent(this, ImportExportActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}