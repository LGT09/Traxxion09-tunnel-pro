package com.lilgagatraxx09.traxxion09tunnelpro;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicBoolean;

public class VpnService extends android.net.VpnService {
    
    private static final String CHANNEL_ID = "VPN_SERVICE_CHANNEL";
    private static final int NOTIFICATION_ID = 1;
    
    private Handler mHandler;
    private Thread mThread;
    private ParcelFileDescriptor mInterface;
    private AtomicBoolean mRunning = new AtomicBoolean(false);
    private String mServer;
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if ("DISCONNECT".equals(action)) {
                disconnect();
                return START_NOT_STICKY;
            }
            
            mServer = intent.getStringExtra("server");
            if (mServer == null) {
                mServer = "Econet Zimbabwe - Harare";
            }
        }
        
        if (mRunning.get()) {
            return START_STICKY;
        }
        
        startVpn();
        return START_STICKY;
    }
    
    private void startVpn() {
        mHandler = new Handler();
        
        if (mThread != null) {
            mThread.interrupt();
        }
        
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mInterface = establish();
                    if (mInterface != null) {
                        mRunning.set(true);
                        startForeground(NOTIFICATION_ID, createNotification());
                        runVpn();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "VpnThread");
        
        mThread.start();
    }
    
    private ParcelFileDescriptor establish() {
        Builder builder = new Builder();
        
        // Configure VPN interface
        builder.setSession("Traxxion09 Tunnel Pro")
               .setMtu(1500)
               .addAddress("10.0.0.2", 32)
               .addDnsServer("8.8.8.8")
               .addDnsServer("8.8.4.4")
               .addRoute("0.0.0.0", 0);
        
        // Set up the VPN interface
        try {
            return builder.establish();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void runVpn() {
        try {
            // Simulate VPN data processing
            FileInputStream in = new FileInputStream(mInterface.getFileDescriptor());
            FileOutputStream out = new FileOutputStream(mInterface.getFileDescriptor());
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();
            
            ByteBuffer packet = ByteBuffer.allocate(32767);
            
            while (mRunning.get()) {
                packet.clear();
                int length = inChannel.read(packet);
                if (length > 0) {
                    packet.flip();
                    // Process packet here (placeholder for actual VPN logic)
                    outChannel.write(packet);
                }
                
                // Simulate some processing delay
                Thread.sleep(10);
            }
            
            in.close();
            out.close();
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void disconnect() {
        mRunning.set(false);
        
        if (mThread != null) {
            mThread.interrupt();
        }
        
        if (mInterface != null) {
            try {
                mInterface.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        stopForeground(true);
        stopSelf();
    }
    
    private Notification createNotification() {
        createNotificationChannel();
        
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Traxxion09 Tunnel Pro")
                .setContentText("Connected to " + mServer)
                .setSmallIcon(R.drawable.ic_vpn_lock)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
    }
    
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "VPN Service",
                NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Shows VPN connection status");
            
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        disconnect();
    }
}