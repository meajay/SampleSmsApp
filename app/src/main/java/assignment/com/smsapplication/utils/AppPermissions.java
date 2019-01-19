package assignment.com.smsapplication.utils;

import android.Manifest;
import android.content.Context;

import assignment.com.smsapplication.R;
import assignment.com.smsapplication.constants.AppConstants;
import pub.devrel.easypermissions.EasyPermissions;

public class AppPermissions {
    private Context context;

    public AppPermissions(Context context) {
        this.context = context;
    }

    public boolean hasSmsPermission() {
        return EasyPermissions.hasPermissions(context, Manifest.permission.READ_SMS);
    }

}
