package fi.hiit.android.gpsdongle;

import android.util.Log;
import android.os.Bundle;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.GpsStatus.NmeaListener;

/**
  */
public class GpsDongleApplication extends Application implements OnSharedPreferenceChangeListener, LocationListener, NmeaListener
{
    public static final String TAG = "GPS-DONGLE";
    public static final String ERROR_TAG = "GPS-DONGLE:ERROR";

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private boolean mActive;
    private LocationManager mLocationManager;


    @Override
    public void onCreate()
    {
        Log.d(GpsDongleApplication.TAG, "Application.onCreate");
        super.onCreate();

        mActive = false;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPrefs.edit();
        mPrefs.registerOnSharedPreferenceChangeListener(this);

        mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
    }

    /** From NmeaListener
      */
    @Override
    public void onNmeaReceived(long timestamp, String nmea)
    {
        Log.i(GpsDongleApplication.TAG, nmea);
    }

    /** From LocationListener
      */
    @Override
    public void onLocationChanged(Location location) { }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onTerminate()
    {
        Log.i(GpsDongleApplication.TAG, "App.onTerminate");
        super.onTerminate();
        stop();
    }

    public boolean isActive()
    {
        return mActive;
    }

    public void start()
    {
        Log.i(GpsDongleApplication.TAG, "Application.start");
        mLocationManager.addNmeaListener(this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        mActive = true;
    }
    public void stop()
    {
        Log.i(GpsDongleApplication.TAG, "Application.stop");
        mLocationManager.removeNmeaListener(this);
        mActive = false;
    }

    public SharedPreferences getPrefs()
    {
        return mPrefs;
    }
    public SharedPreferences.Editor getPrefsEditor()
    {
        return mEditor;
    }

    /* methods required by OnSharedPreferenceChangeListener */
    @Override
    public synchronized void onSharedPreferenceChanged(SharedPreferences prefs, String key)
    {
        //[TODO]
        Log.i(GpsDongleApplication.TAG, "App.onSharedPreferenceChanged");
        return;
    }
}



