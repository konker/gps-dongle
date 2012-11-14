package fi.hiit.android.gpsdongle.activity;

import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import fi.hiit.android.gpsdongle.R;
import fi.hiit.android.gpsdongle.GpsDongleApplication;


public class MainActivity extends Activity
{
    private GpsDongleApplication mApplication;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.d(GpsDongleApplication.TAG, "Main.onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        mApplication = (GpsDongleApplication)getApplication();

        setupUi();
    }

    private void setupUi()
    {
        Button buttonMasterToggle =
            (Button)findViewById(R.id.buttonMasterToggle);
        if (mApplication.isActive()) {
            buttonMasterToggle.setText(getString(R.string.stop));
        }
        else {
            buttonMasterToggle.setText(getString(R.string.start));
        }
        buttonMasterToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(GpsDongleApplication.TAG, "Main.buttonMasterToggle clicked");
                if (mApplication.isActive()) {
                    mApplication.stop();
                    ((Button)view).setText(getString(R.string.start));
                }
                else {
                    mApplication.start();
                    ((Button)view).setText(getString(R.string.stop));
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        /*
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main_activity, menu);
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        /*
        switch (item.getItemId()) {
            case R.id.menuPreferences:
                Intent intent = new Intent(this, PrefsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        */
        return super.onOptionsItemSelected(item);
    }

    /* Lifecycle methods [TODO: remove if uneeded?] */
    @Override
    protected void onPause()
    {
        Log.d(GpsDongleApplication.TAG, "Main.onPause");
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        Log.d(GpsDongleApplication.TAG, "Main.onResume");
        super.onResume();
    }

    @Override
    protected void onStart()
    {
        Log.d(GpsDongleApplication.TAG, "Main.onStart");
        super.onStart();
    }

    @Override
    protected void onRestart()
    {
        Log.d(GpsDongleApplication.TAG, "Main.onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop()
    {
        Log.d(GpsDongleApplication.TAG, "Main.onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        Log.d(GpsDongleApplication.TAG, "Main.onDestroy");
        super.onDestroy();
    }
}

