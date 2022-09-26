package com.example.azarias;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;


public class MainActivity extends Activity {

    private boolean status;
    Intent mServiceIntent;
    private Servico mYourService;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            startService(new Intent(getBaseContext(), Servico.class));
            mYourService = new Servico();
            mServiceIntent = new Intent(this, mYourService.getClass());
            if (!isMyServiceRunning(mYourService.getClass())) {
                startForegroundService(mServiceIntent);
        }
    }
    private boolean isMyServiceRunning( Class<?> Servico) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (Servico.getName().equals(service.service.getClassName())) {
                Log.i ("Service status", "Running");
                return true;
            }
        }
        Log.i ("Service status", "Not running");
        return false;
    }
    @Override
    protected void onDestroy() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, BootCompletedIntentReceiver.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, BootCompletedIntentReceiver.class);
        this.sendBroadcast(broadcastIntent);
        super.onStop();
    }
}











