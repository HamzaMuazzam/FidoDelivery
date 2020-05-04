package com.example.fidodelivery.usermianscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fidodelivery.R;
import com.example.fidodelivery.fragments.AccountFragment;
import com.example.fidodelivery.fragments.DeliveriesFragment;
import com.example.fidodelivery.fragments.TodayHistoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainActivity extends AppCompatActivity {

    public static String uuid;
    private BottomNavigationView bottom_navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        SharedPreferences sharedPreferences=getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
         uuid = sharedPreferences.getString("UUID", "");

        initviews();
    }

    private void initviews() {

        openFragment(new DeliveriesFragment());

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.deliveries:
//                        Toast.makeText(MainActivity.this, "reselected do nothing", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.navigation_history:
//                        openFragment(new HomeFragment());
//                        Toast.makeText(MainActivity.this, "reselected do nothing", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_account:
//                        Toast.makeText(MainActivity.this, "reselected do nothing", Toast.LENGTH_SHORT).show();

//                        openFragment(new HomeFragment());

//                        Toast.makeText(MainActivity.this, "reselected do nothing", Toast.LENGTH_SHORT).show();

//                        openFragment(new HomeFragment());


                }
            }
        });

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.deliveries:
                        openFragment(new DeliveriesFragment());
                        break;
                    case R.id.navigation_history:
                        openFragment(new TodayHistoryFragment());
                        break;

                    case R.id.navigation_account:
                        openFragment(new AccountFragment());

                        break;


                }
                return true;
            }
        });


    }

    public void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentReplace, fragment).commit();

    }

/*

    public void requestLastLocationUpdate(final String ID) {
        mDatabase = FirebaseDatabase.getInstance();
        // u can give any node reference of database here
        mRef = mDatabase.getReference("users");
        Toast.makeText(this, ""+ID, Toast.LENGTH_SHORT).show();
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

                mRef.child("ID_UNIQUE").setValue(insertValue);

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
        }
        else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }

    }
*/


}
