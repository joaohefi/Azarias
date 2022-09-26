package com.example.azarias;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class Servico extends android.app.Service{
    boolean status;
    public int counter=0;
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            startConex();
    }

    public void startConex(){
        Conexao conexao = new Conexao(getApplicationContext());
        status = conexao.networkConnectionStatus();
        if(status){
            if (conexao.mqqtConnection()){
                conexao.subscribe("topico/2",0);
                conexao.callback();
            }
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, BootCompletedIntentReceiver.class);
        this.sendBroadcast(broadcastIntent);
    }


}
