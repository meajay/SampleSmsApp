package assignment.com.smsapplication.sms.view;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import assignment.com.smsapplication.R;
import assignment.com.smsapplication.SmsApp;
import assignment.com.smsapplication.constants.AppConstants;
import assignment.com.smsapplication.sms.model.Sms;
import assignment.com.smsapplication.sms.presenter.SmsPresenter;
import assignment.com.smsapplication.utils.AppPermissions;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SmsActivity extends AppCompatActivity implements SmsMvpView,
        EasyPermissions.PermissionCallbacks {

    @Inject
    SmsPresenter smsPresenter;

    private AppPermissions appPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        injectDependencies();
        smsPresenter.onAttach(this);
        appPermissions = new AppPermissions(this);
        checkAndRequestSMSPermission();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == AppConstants.READ_SMS_PERMISIONS) {
            fetchInBoxMessages();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults,
                this);

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (requestCode == AppConstants.READ_SMS_PERMISIONS) {
            Toast.makeText(this, R.string.must_read_permissions,
                    Toast.LENGTH_LONG)
                    .show();
            requestSmsPermission();

            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                new AppSettingsDialog.Builder(this).build().show();
            }
        }
    }

    @Override
    public void onGetInboxMessagesResponse(int result, List<Sms> smsList) {
        Toast.makeText(this, "" + smsList.size(), Toast.LENGTH_SHORT).show();
    }

    private void checkAndRequestSMSPermission() {
        if (appPermissions.hasSmsPermission()) {
            fetchInBoxMessages();
        } else {
            requestSmsPermission();
        }
    }

    private void fetchInBoxMessages() {
        smsPresenter.getAllInBoxMessages();
    }

    private void injectDependencies() {
        ((SmsApp) getApplication()).getAppComponent().inject(this);
    }

    private void requestSmsPermission() {
        EasyPermissions.requestPermissions(this, getString(R.string.sms_required),
                AppConstants.READ_SMS_PERMISIONS,
                Manifest.permission.READ_SMS);
    }
}
