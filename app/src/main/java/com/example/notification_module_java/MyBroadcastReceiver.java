package com.example.notification_module_java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Action to be perfomed placed here
        context.startActivity(intent);
    }
}
