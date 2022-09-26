package com.example.azarias;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, Servico.class);
            pushIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(pushIntent);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, Servico.class));
        } else {
            context.startService(new Intent(context, Servico.class));
        }
    }
}

