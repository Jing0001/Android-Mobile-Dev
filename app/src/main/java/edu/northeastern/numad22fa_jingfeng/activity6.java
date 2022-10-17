package edu.northeastern.numad22fa_jingfeng;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class activity6<fusedLocationClient> extends AppCompatActivity implements LocationListener{
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    private double distance;
    private TextView la;
    private TextView lo;
    private TextView dis;
    private Location startLocation;
    private Button reset;
    private int count = 0;
    private LocationManager locationManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        la = (TextView) findViewById(R.id.txtLa);
        lo = (TextView) findViewById(R.id.txtLo);
        dis = (TextView) findViewById(R.id.dis);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocation = null;
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
    }

    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(activity6.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, this);

        }
        else{
            ActivityCompat.requestPermissions(activity6.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.e("aaaaaa", String.valueOf(location));
        if (startLocation == null) {
            startLocation = location;
        }
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        distance = distance(location);
        dis.setText(String.valueOf(distance));
        la.setText("Latitude: " + String.valueOf(latitude));
        lo.setText("Longitude: " + String.valueOf(longitude));
    }

    private double distance(Location location) {
        float[] results = new float[3];
        Location.distanceBetween(startLocation.getLatitude(), startLocation.getLongitude(), location.getLatitude(), location.getLongitude(), results);
        return results[0];
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count == 2){
            super.onBackPressed();
        }else{
            Toast.makeText(getBaseContext(),"Are you sure to go back?", Toast.LENGTH_SHORT).show();
        }
    }
}
//package edu.northeastern.numad22fa_jingfeng;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Looper;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Switch;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.location.LocationRequest;
//
//    public class activity6<fusedLocationClient> extends AppCompatActivity {
//
//        private static final int PERMISSION_FINE_LOCATION = 99;
//        public static final int FAST_UPDATE_INTERVAL = 2;
//        public static final int DEFAULT_UPDATE_INTERVAL = 2;
//        TextView tv_latitude, tv_longitude, tv_sensor, tv_updates, tv_distanceValue, tv_startingLa, tv_startingLo;
//        Switch sw_locationUpdates, sw_gps;
//        Button btn_resetDistance;
//        //Google API location service
//        FusedLocationProviderClient fusedLocationProviderClient;
//        //Location request
//        LocationRequest locationRequest;
//        private long pressedTime;
//        double latitude;
//        double longitude;
//        double distance;
//        double startingLa;
//        double startingLo;
//
//        //variable to check if tracking location or not
//        Boolean updateOn = false;
//        //    Location currentLocation;
//        LocationCallback locationCallback;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_6);
//            tv_latitude = (TextView) findViewById(R.id.tv_la);
//            tv_longitude = (TextView) findViewById(R.id.tv_lo);
//            tv_sensor = (TextView) findViewById(R.id.tv_sensor);
//            tv_updates = (TextView) findViewById(R.id.tv_updates);
//            tv_distanceValue = (TextView) findViewById(R.id.tv_distanceValue);
//            tv_startingLa = (TextView) findViewById(R.id.tv_starting_la_value);
//            tv_startingLo = (TextView) findViewById(R.id.tv_starting_lo_value);
//
//            sw_gps = (Switch) findViewById(R.id.sw_gps);
//            sw_locationUpdates = (Switch) findViewById(R.id.sw_locationsUpdates);
//            btn_resetDistance = (Button) findViewById(R.id.btn_resetDistance);
//
//            //set properties of LocationRequest
//            locationRequest = new LocationRequest();
//            locationRequest.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
//            locationRequest.setFastestInterval(1000 * FAST_UPDATE_INTERVAL);
//            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//            locationCallback = new LocationCallback() {
//                @Override
//                public void onLocationResult(LocationResult locationResult) {
//                    super.onLocationResult(locationResult);
//                    Location location = locationResult.getLastLocation();
//                    updateCurrentLocation(location);
//                }
//            };
//
//            sw_gps.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (sw_gps.isChecked()) {
//                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                        tv_sensor.setText("Using GPS Sensor");
//                    } else {
//                        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//                        tv_sensor.setText("Using Tower and WIFI");
//                    }
//                }
//            });
//
//            sw_locationUpdates.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (sw_locationUpdates.isChecked()) {
//                        // tracking location
//                        startTracking();
//                    } else {
//                        // stop tracking
//                        stopTracking();
//                    }
//                }
//            });
//
//            updateGPS();
//
//            btn_resetDistance.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    updateGPS();
//                    distance = 0.00;
//                }
//            });
//
//        } //end onCreate
//
//        private void startTracking() {
//            tv_updates.setText("Tracking location...");
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
//            updateGPS();
//        }
//
//        private void stopTracking() {
//            tv_latitude.setText("Not tracking");
//            tv_longitude.setText("Not tracking");
//            tv_updates.setText("Not tracking location");
//            tv_startingLo.setText("0.00");
//            tv_startingLa.setText("0.00");
//            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
//        }
//
//        private void updateGPS() {
//            //get permission from user to track GPS
//            //mark the starting point for calculation the distance
//            //get current location from fused client
//            //update the UI: set all properties
//            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity6.this);
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        showStartingPoint(location);
//                        // mark the starting point
//                        if (location != null) {
//                            //get permission, put the location values into UI
//                            latitude = location.getLatitude();
//                            longitude = location.getLongitude();
////                        updateCurrentLocation(location);
//                        }
//                    }
//                });
//            } else {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
//                }
//
//            }
//        }
//
//        private void showStartingPoint(Location location) {
//            // mark the starting point
//            startingLa = location.getLatitude();
//            startingLo = location.getLongitude();
//            tv_startingLa.setText((String.valueOf(startingLa)));
//            tv_startingLo.setText((String.valueOf(startingLo)));
//        }
//
//        private void updateCurrentLocation(Location location) {
//            //update new location
//            double newLa = location.getLatitude();
//            double newLo = location.getLongitude();
//            distance += calculateDistance(latitude, longitude, newLa, newLo);
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//            tv_latitude.setText(String.valueOf(latitude));
//            tv_longitude.setText(String.valueOf(longitude));
//            tv_distanceValue.setText(String.valueOf((distance)));
//        }
//
//        // calculate distance
//        private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//            float[] results = new float[3];
//            Location.distanceBetween(lat1, lon1, lat2, lon2, results);
//            return results[0];
//        }
//
//        @Override
//        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            switch (requestCode) {
//                case PERMISSION_FINE_LOCATION:
//                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                        updateGPS();
//                    } else {
//                        Toast.makeText(this, "The app requires permission to be granted.", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                    break;
//            }
//        }
//
//        @Override
//        public void onBackPressed() {
//            if (pressedTime + 3000 > System.currentTimeMillis()) {
//                super.onBackPressed();
//                finish();
//            } else {
//                Toast.makeText(getBaseContext(), "Exit will cause distance data lose, press back again to confirm exit.", Toast.LENGTH_SHORT).show();
//            }
//            pressedTime = System.currentTimeMillis();
//        }
//    }