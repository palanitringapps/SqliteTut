package com.sqlitetut;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by tringapps-admin on 17/9/17.
 */

public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
            Toast.makeText(context, "screen unlocked !!!!.",
                    Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(context, "Test broadcast!!!!.",
                    Toast.LENGTH_LONG).show();
        }
    }

}
