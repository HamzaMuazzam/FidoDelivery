package com.example.fidodelivery.services;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.fidodelivery.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MusicPlayerService extends Service {
    public static final String TAG = "MyTag";
    private final Binder binder = new MyServiceBiner();
    private MediaPlayer mPlayer;
    FirebaseDatabase mDatabase;
    private DatabaseReference mRef;


    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.tilawat);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent("MUSIC");
                intent.putExtra("KEY", "done");
                LocalBroadcastManager.getInstance(getApplicationContext())
                        .sendBroadcast(intent);

                //service stop and remove notification also
//                        stopForeground(true);
                //service auto stop hojae gi
//                        stopSelf();

            }
        });
        Log.d(TAG, "OnCreate");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startMyOwnForeground();
        }
        Log.d(TAG, "onStartCommand");
requestLastLocationUpdate(null);

        return START_NOT_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");

        return true;
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        mPlayer.release();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");

        return binder;
    }


    public class MyServiceBiner extends Binder {
        public MusicPlayerService getservice() {
            return MusicPlayerService.this;
        }

    }


    //public client methods for communication to activity
    public boolean isPlaying() {

        return mPlayer.isPlaying();
    }

    public void play() {
        mPlayer.start();
    }

    public void pause() {
        mPlayer.pause();
    }


    public void requestLastLocationUpdate(final String ID) {
        SharedPreferences sharedPref = MusicPlayerService.this.getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
        final String uuid = sharedPref.getString("UUID", "");

        mDatabase = FirebaseDatabase.getInstance();
        // u can give any node reference of database here
        mRef = mDatabase.getReference("users");
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Log.d(TAG, "onLocationChanged: ");
//                Toast.makeText(MapsActivity.this, "Getting bar bar\n"+latitude+"\n"+longitude, Toast.LENGTH_LONG).show();


                Map<String, Object> insertValue = new HashMap<>();
                insertValue.put("lat", latitude);
                insertValue.put("lgn", longitude);
                Intent intent = new Intent("MUSIC");
                intent.putExtra("KEY", "done");

                mRef.child(uuid).setValue(insertValue);

                LocalBroadcastManager.getInstance(getApplicationContext())
                        .sendBroadcast(intent);
//                mRef.child("ID_UNIQUE").setValue(insertValue);


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.2f, locationListener);
        } else {
//            Toast.makeText(this, "ye null kun hai ?", Toast.LENGTH_SHORT).show();
        }

    }
}
