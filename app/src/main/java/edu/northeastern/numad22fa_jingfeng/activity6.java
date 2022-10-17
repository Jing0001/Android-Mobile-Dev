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