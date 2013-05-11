package com.example.huntersandzombiesdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener {
    private static final String TAG = "GPSTracker";
    
    private final Context mContext;
    
    // flag for GPS status
    boolean isGPSEnabled = false;
    
    // flag for network status
    boolean isNetworkEnabled = false;
    
    boolean canGetLocation = false;
    
    Location location; //  location
    double latitude; // latitude
    double longitude; //longitude
    
    // Minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    
    // Minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    
    //Declaring a Location Manager
    protected LocationManager locationManager;
    
    public GPSTracker(Context context){
        this.mContext = context;
        getLocation();
    }
    
    public Location getLocation(){
        try{
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);            
            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            
            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            
            Log.d(TAG, "Network Enabled: " + Boolean.toString(isNetworkEnabled));
            Log.d(TAG, "GPS Enabled: "+ Boolean.toString(isGPSEnabled));
            
            if (!isGPSEnabled && !isNetworkEnabled){
                Log.d(TAG, "no network enabled");
                // no network provider is enabled
            }
            else{
                this.canGetLocation = true;
                
                // First get location from Network Provider
                if (isNetworkEnabled){
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if(locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location != null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                
                // If GPS Enabled, get position using GPS Services
                if(isGPSEnabled){
                    
                    if(location == null){
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if(locationManager != null){
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if(location != null){
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
            return location;
    }
    /**
     * Function to check if GPS/wifi/network is enabled
     * @return boolean
     */
    
    public boolean canGetLocation(){
        return this.canGetLocation;
    }
    
    @Override
    public void onLocationChanged(Location location) {
    }
 
    @Override
    public void onProviderDisabled(String provider) {
    }
 
    @Override
    public void onProviderEnabled(String provider) {
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
 
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    
    //Get Latitude
    public double getLatitude(){
        if(location!=null){
            latitude = location.getLatitude();
        }
        return latitude;
     }
    
    //Get Longitude 
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
        return longitude;        
    }
}
