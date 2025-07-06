package com.lilgagatraxx09.traxxion09tunnelpro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImportExportActivity extends AppCompatActivity {
    
    private static final int REQUEST_IMPORT_FILE = 1;
    private static final int REQUEST_EXPORT_PERMISSION = 2;
    private static final int REQUEST_IMPORT_PERMISSION = 3;
    
    private Button importButton;
    private Button exportButton;
    private TextView statusText;
    private TextView infoText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_export);
        
        setupToolbar();
        initializeViews();
        setupClickListeners();
        updateInfo();
    }
    
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Import/Export");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    private void initializeViews() {
        importButton = findViewById(R.id.importButton);
        exportButton = findViewById(R.id.exportButton);
        statusText = findViewById(R.id.statusText);
        infoText = findViewById(R.id.infoText);
    }
    
    private void setupClickListeners() {
        importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importConfiguration();
            }
        });
        
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportConfiguration();
            }
        });
    }
    
    private void importConfiguration() {
        if (checkStoragePermission(REQUEST_IMPORT_PERMISSION)) {
            openFileChooser();
        }
    }
    
    private void exportConfiguration() {
        if (checkStoragePermission(REQUEST_EXPORT_PERMISSION)) {
            performExport();
        }
    }
    
    private boolean checkStoragePermission(int requestCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) 
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
            return false;
        }
        return true;
    }
    
    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Configuration File"), 
            REQUEST_IMPORT_FILE);
    }
    
    private void performExport() {
        try {
            // Create export directory
            File exportDir = new File(Environment.getExternalStorageDirectory(), 
                "Traxxion09TunnelPro");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            
            // Create configuration file
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", 
                Locale.getDefault()).format(new Date());
            String fileName = "traxxion09_config_" + timestamp + ".txt";
            File configFile = new File(exportDir, fileName);
            
            // Write configuration data
            String configData = generateConfigurationData();
            FileOutputStream fos = new FileOutputStream(configFile);
            fos.write(configData.getBytes());
            fos.close();
            
            statusText.setText("Configuration exported successfully!");
            statusText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            
            Toast.makeText(this, "Exported to: " + configFile.getAbsolutePath(), 
                Toast.LENGTH_LONG).show();
            
            updateInfo();
            
        } catch (IOException e) {
            e.printStackTrace();
            statusText.setText("Export failed: " + e.getMessage());
            statusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            Toast.makeText(this, "Export failed!", Toast.LENGTH_SHORT).show();
        }
    }
    
    private String generateConfigurationData() {
        StringBuilder config = new StringBuilder();
        config.append("# Traxxion09 Tunnel Pro Configuration\n");
        config.append("# Generated on: ").append(new Date().toString()).append("\n");
        config.append("# Created by: LilGagaTraxx09\n\n");
        
        config.append("[Server_List]\n");
        config.append("server1=Econet Zimbabwe - Harare,econet-harare.zw,1194\n");
        config.append("server2=Econet Zimbabwe - Bulawayo,econet-bulawayo.zw,1194\n");
        config.append("server3=NetOne Zimbabwe - Harare,netone-harare.zw,1194\n");
        config.append("server4=NetOne Zimbabwe - Bulawayo,netone-bulawayo.zw,1194\n");
        config.append("server5=Econet Zimbabwe - Mutare,econet-mutare.zw,1194\n");
        config.append("server6=NetOne Zimbabwe - Gweru,netone-gweru.zw,1194\n");
        config.append("server7=Econet Zimbabwe - Masvingo,econet-masvingo.zw,1194\n");
        config.append("server8=NetOne Zimbabwe - Chinhoyi,netone-chinhoyi.zw,1194\n\n");
        
        config.append("[Settings]\n");
        config.append("auto_connect=false\n");
        config.append("protocol=udp\n");
        config.append("port=1194\n");
        config.append("cipher=AES-256-CBC\n");
        config.append("auth=SHA256\n");
        config.append("offline_mode=true\n\n");
        
        config.append("[Owner_Info]\n");
        config.append("creator=Vincent Ganiza LilGagaTraxx09\n");
        config.append("contact1=263780078177\n");
        config.append("contact2=263716857999\n");
        config.append("app_name=Traxxion09 tunnel pro\n");
        
        return config.toString();
    }
    
    private void performImport(Uri fileUri) {
        try {
            FileInputStream fis = (FileInputStream) getContentResolver().openInputStream(fileUri);
            byte[] buffer = new byte[1024];
            StringBuilder content = new StringBuilder();
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }
            fis.close();
            
            // Parse and validate configuration
            String configData = content.toString();
            if (validateConfiguration(configData)) {
                // Save imported configuration
                saveImportedConfiguration(configData);
                
                statusText.setText("Configuration imported successfully!");
                statusText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                Toast.makeText(this, "Configuration imported successfully!", 
                    Toast.LENGTH_SHORT).show();
            } else {
                statusText.setText("Invalid configuration file!");
                statusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                Toast.makeText(this, "Invalid configuration file!", Toast.LENGTH_SHORT).show();
            }
            
            updateInfo();
            
        } catch (IOException e) {
            e.printStackTrace();
            statusText.setText("Import failed: " + e.getMessage());
            statusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            Toast.makeText(this, "Import failed!", Toast.LENGTH_SHORT).show();
        }
    }
    
    private boolean validateConfiguration(String configData) {
        // Basic validation - check if it contains required sections
        return configData.contains("[Server_List]") && 
               configData.contains("[Settings]") && 
               configData.contains("Traxxion09");
    }
    
    private void saveImportedConfiguration(String configData) {
        // Save to internal storage
        try {
            FileOutputStream fos = openFileOutput("imported_config.txt", MODE_PRIVATE);
            fos.write(configData.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void updateInfo() {
        String info = "Import/Export Information:\n\n";
        info += "• Export: Saves current VPN configuration\n";
        info += "• Import: Loads VPN configuration from file\n";
        info += "• Supported format: .txt configuration files\n";
        info += "• Export location: /storage/emulated/0/Traxxion09TunnelPro/\n\n";
        info += "Configuration includes:\n";
        info += "- Server settings\n";
        info += "- Connection preferences\n";
        info += "- Security settings\n";
        info += "- Owner information\n";
        
        infoText.setText(info);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == REQUEST_IMPORT_FILE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri fileUri = data.getData();
                performImport(fileUri);
            }
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, 
                                         int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_EXPORT_PERMISSION) {
                performExport();
            } else if (requestCode == REQUEST_IMPORT_PERMISSION) {
                openFileChooser();
            }
        } else {
            Toast.makeText(this, "Storage permission required!", Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}