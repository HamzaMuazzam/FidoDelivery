package com.example.fidodelivery.maps;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.fidodelivery.R;
import com.example.fidodelivery.directionhelpers.FetchURL;
import com.example.fidodelivery.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, TaskLoadedCallback {
    private int GPS_CODE = 1999;
    double latitude = 0;
    double longitude = 0;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;


    private static final int API_CHECK_CODE = 1996;
    public static final int PERMISSION_CODE = 1995;
    boolean permission_check;
    private boolean mGranted;

    FusedLocationProviderClient fusedLocationProviderClient;
    Button button_seeNearByRestaurant;
    private GoogleMap mMap;
    LocationRequest mLocationRequest;
    PendingResult<LocationSettingsResult> result;
    final static int REQUEST_LOCATION = 199;
    GoogleApiClient mGoogleApiClient;
    private Location mLastLocation = null;
    //    String address;
    private Polyline currentPolyline;

    double targetlongitude;
    double targetlatitude;
    private MarkerOptions currentLocation, targetLocation;


    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
// finally change the color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.textColorBlackLight));
            }
        }
        setContentView(R.layout.activity_maps);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");

        targetlatitude = getIntent().getDoubleExtra("latitude", 0);
        targetlongitude = getIntent().getDoubleExtra("longitude", 0);
//        Toast.makeText(this, targetlatitude + "\n get  from intent" + targetlongitude, Toast.LENGTH_SHORT).show();
        targetLocation = new MarkerOptions().position(new LatLng(targetlatitude, targetlongitude)).title("Destination 2");

    }

    @Override
    protected void onStart() {
        super.onStart();
        checklocationpermission();
        if (isGPS_Enabled()) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            fusedLocationProviderClient = new FusedLocationProviderClient(MapsActivity.this);
            requestNewLocationData();
        }
    }

    public boolean isGPS_Enabled() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        assert locationManager != null;
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsEnabled) {
            return true;
        } else {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            mGoogleApiClient.connect();


        }

        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);


    }


    public void checklocationpermission() {
        if (isServiceOk() == true) {
            if (permission_check) {
                Toast.makeText(this, "Ready to Map", Toast.LENGTH_SHORT).show();
            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_CODE);
                    }
                }

            }
        }

    }

    public boolean isServiceOk() {
        GoogleApiAvailability checkApi = GoogleApiAvailability.getInstance();
        int result = checkApi.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (checkApi.isUserResolvableError(result)) {
            Dialog dialog = checkApi.getErrorDialog(this, result, API_CHECK_CODE, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface task) {
                    Toast.makeText(MapsActivity.this, "Dialoge cencelled by user", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        } else {
            Toast.makeText(this, "No Google Play Service ", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

//    private void geocoder() {
//        Geocoder geocoder;
//        List<Address> addresses = null;
//        geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            address = addresses.get(0).getAddressLine(0);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14.0f);
            mMap.animateCamera(cameraUpdate);
//            mMap.addMarker(new MarkerOptions().position(latLng));
//            geocoder();
            requestLastLocationUpdate();
            currentLocation = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Location 1");

            mMap.addMarker(targetLocation);
            new FetchURL(MapsActivity.this).execute(getUrl(currentLocation.getPosition(), targetLocation.getPosition(), "driving"), "driving");

            float[] array = new float[5];
//            array ka size define krdo method khuud hi array men vallues insertkrdy ga ... max index 3 use krta h method
            Location.distanceBetween(latitude, longitude, targetlatitude, targetlongitude, array);
//            Toast.makeText(MapsActivity.this, ""+array.length, Toast.LENGTH_SHORT).show();
            float distance = 0;
            try {
                for (int x = 0; x < array.length; x++) {
                distance=distance+array[x];
//                    Toast.makeText(MapsActivity.this, "" + array[x], Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

                Toast.makeText(MapsActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(MapsActivity.this, "Distance in KM :"+distance/1000f, Toast.LENGTH_LONG).show();
//

// ye method bh same hi distance dy rha hai

//            Location startPoint=new Location("locationA");
//            startPoint.setLatitude(latitude);
//            startPoint.setLongitude(longitude);
//
//            Location endPoint=new Location("locationB");
//            endPoint.setLatitude(targetlatitude);
//            endPoint.setLongitude(targetlongitude);
//
//            double distance2=startPoint.distanceTo(endPoint);
//            Toast.makeText(MapsActivity.this, "KM: "+distance2/1000f, Toast.LENGTH_SHORT).show();
        }
    };
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                boolean gps_enabled = isGPS_Enabled();
                if (gps_enabled) {
                    requestNewLocationData();
                } else {
                    isGPS_Enabled();
                }
                mGranted = true;
            } else {
                Toast.makeText(this, "Please allow the permission to fetch location", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        //...
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    MapsActivity.this,
                                    REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        //...
                        break;
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult()", Integer.toString(resultCode));

        //final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        // All required changes were successfully made
                        Toast.makeText(MapsActivity.this, "Location enabled by user!", Toast.LENGTH_LONG).show();
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.map);
                        mapFragment.getMapAsync(this);
                        fusedLocationProviderClient = new FusedLocationProviderClient(MapsActivity.this);
                        requestNewLocationData();
                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(MapsActivity.this, "Location not enabled, user cancelled.", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void requestLastLocationUpdate() {

        LocationListener locationListener = new LocationListener() {
            SharedPreferences sharedPref = MapsActivity.this.getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
            String uuid = sharedPref.getString("UUID", "");

            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Log.d("MYTAG", "onLocationChanged: ");

                Map<String, Object> insertValue = new HashMap<>();
                insertValue.put("lat", latitude);
                insertValue.put("lgn", longitude);
                mRef.child(uuid).setValue(insertValue);
                Toast.makeText(MapsActivity.this, "Inserted", Toast.LENGTH_SHORT).show();


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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener);
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }

    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }



}
