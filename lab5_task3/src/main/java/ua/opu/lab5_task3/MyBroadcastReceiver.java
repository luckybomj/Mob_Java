package ua.opu.lab5_task3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.FragmentManager;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private final FragmentManager fragmentManager;

    public MyBroadcastReceiver(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch(intent.getAction()) {
            case Intent.ACTION_BATTERY_LOW:
                new BatteryDialog().show(fragmentManager, "dlg");
                break;
            case Intent.ACTION_CAMERA_BUTTON:
                new CameraDialog().show(fragmentManager, "dlg");
                break;
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                boolean state = intent.getBooleanExtra("state", false);
                AirplaneModeDialog.newInstance(state).show(fragmentManager, "dlg");
                break;
        }

    }
}
