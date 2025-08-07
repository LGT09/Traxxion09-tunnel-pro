package com.lilgagatraxx09.traxxion09tunnelpro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.VpnService;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private static final int VPN_REQUEST_CODE = 1;
    
    private Switch vpnSwitch;
    private Switch killSwitch;
    private Spinner serverSpinner;
    private Button connectButton;
    private Button diagnosticButton;
    private TextView statusText;
    private TextView serverInfoText;
    private TextView diagnosticInfoText;
    private String[] servers;
    private String selectedServer;
    private boolean isVpnConnected = false;
    private SharedPreferences preferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        preferences = getSharedPreferences("Traxxion09Prefs", MODE_PRIVATE);
        setupTheme();
        setupToolbar();
        initializeViews();
        setupServerSpinner();
        setupClickListeners();
        loadSettings();
        updateUI();
        runDiagnostics();
    }
    
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Traxxion09 Tunnel Pro");
    }
    
    private void setupTheme() {
        String themeMode = preferences.getString("theme_mode", "system");
        switch (themeMode) {
            case "dark":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case "light":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
        }
    }
    
    private void initializeViews() {
        vpnSwitch = findViewById(R.id.vpnSwitch);
        killSwitch = findViewById(R.id.killSwitch);
        serverSpinner = findViewById(R.id.serverSpinner);
        connectButton = findViewById(R.id.connectButton);
        diagnosticButton = findViewById(R.id.diagnosticButton);
        statusText = findViewById(R.id.statusText);
        serverInfoText = findViewById(R.id.serverInfoText);
        diagnosticInfoText = findViewById(R.id.diagnosticInfoText);
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
        
        killSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("kill_switch", isChecked).apply();
            if (isChecked) {
                Toast.makeText(this, "Kill Switch Enabled", Toast.LENGTH_SHORT).show();
            }
        });
        
        diagnosticButton.setOnClickListener(v -> runDiagnostics());
    }
    
    private void loadSettings() {
        killSwitch.setChecked(preferences.getBoolean("kill_switch", false));
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
    
    private void runDiagnostics() {
        StringBuilder diagnosticInfo = new StringBuilder();
        diagnosticInfo.append("=== DIAGNOSTIC TOOLS ===\n\n");
        
        // IP Checker
        diagnosticInfo.append("🌐 IP Information:\n");
        diagnosticInfo.append("Local IP: ").append(getLocalIpAddress()).append("\n");
        diagnosticInfo.append("Public IP: Checking...\n\n");
        
        // SIM Card Information
        diagnosticInfo.append("📱 SIM Information:\n");
        diagnosticInfo.append(getSimInfo()).append("\n\n");
        
        // Host Key Viewer
        diagnosticInfo.append("🔑 Host Key:\n");
        diagnosticInfo.append("HWID: ").append(getHWID()).append("\n");
        diagnosticInfo.append("Device: ").append(android.os.Build.MODEL).append("\n");
        diagnosticInfo.append("Android: ").append(android.os.Build.VERSION.RELEASE).append("\n\n");
        
        // Data Usage
        diagnosticInfo.append("📊 Data Usage:\n");
        diagnosticInfo.append("VPN Data: ").append(getVpnDataUsage()).append("\n");
        diagnosticInfo.append("Total Data: ").append(getTotalDataUsage()).append("\n");
        
        diagnosticInfoText.setText(diagnosticInfo.toString());
    }
    
    private String getLocalIpAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress() && addr.getHostAddress().indexOf(':') < 0) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
    
    private String getSimInfo() {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (tm != null) {
            String simOperator = tm.getSimOperatorName();
            String simCountry = tm.getSimCountryIso();
            return "Operator: " + (simOperator != null ? simOperator : "Unknown") + "\n" +
                   "Country: " + (simCountry != null ? simCountry.toUpperCase() : "Unknown") + "\n" +
                   "Network: " + (tm.getNetworkOperatorName() != null ? tm.getNetworkOperatorName() : "Unknown");
        }
        return "SIM Info: Not available";
    }
    
    private String getHWID() {
        return android.os.Build.SERIAL + "-" + android.os.Build.FINGERPRINT.substring(0, 8);
    }
    
    private String getVpnDataUsage() {
        // Simulated data usage
        return "2.5 MB (Today)";
    }
    
    private String getTotalDataUsage() {
        // Simulated total data usage
        return "156.7 MB (This month)";
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