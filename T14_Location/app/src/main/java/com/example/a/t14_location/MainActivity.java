package com.example.a.t14_location;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permission, 0);
        }

        final TextView textView = (TextView) findViewById(R.id.textView);
        String res = "";
        final Geocoder geocoder = new Geocoder(this);
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();
        for (int i = 0; i < providers.size(); i++) {
            res += "provider:" + providers.get(i) + "state:" + manager.isProviderEnabled(providers.get(i)) + "\n";

        }
        textView.setText(res);

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String str = "lat:"+location.getLatitude()+"lon:"+location.getLongitude();
                textView.append(str);
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude() , location.getLongitude() , 10);
                    Address address = addresses.get(0);
                    Log.d("adress ::",address.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }
}
