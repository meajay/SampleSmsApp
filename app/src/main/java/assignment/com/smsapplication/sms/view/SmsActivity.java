package assignment.com.smsapplication.sms.view;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import assignment.com.smsapplication.R;
import assignment.com.smsapplication.SmsApp;
import assignment.com.smsapplication.constants.AppConstants;
import assignment.com.smsapplication.receiver.SmsReceiver;
import assignment.com.smsapplication.sms.model.Sms;
import assignment.com.smsapplication.sms.presenter.SmsPresenter;
import assignment.com.smsapplication.utils.AppPermissions;
import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SmsActivity extends AppCompatActivity implements SmsMvpView,
        EasyPermissions.PermissionCallbacks {

    @BindView(R.id.sms_recycler)
    ShimmerRecyclerView smsRecycler;

    @Inject
    SmsPresenter smsPresenter;

    private AppPermissions appPermissions;
    private SmsAdapter smsAdapter;
    private List<Sms> smsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
        injectDependencies();
        smsPresenter.onAttach(this);
        appPermissions = new AppPermissions(this);
        checkAndRequestSMSPermission();
        setUpRecyclerView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        fetchInBoxMessages();
        smsRecycler.showShimmerAdapter();
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
    public void onGetInboxMessagesResponse(int result, List<Sms> smsList, String message) {
        if (result == AppConstants.SUCCESS) {
            this.smsList.clear();
            updateAdapter(smsList);
        } else {
            Toast.makeText(this, "Error : " + message, Toast.LENGTH_SHORT).show();
        }
    }

    public void updateAdapter(List<Sms> smsList) {
        Handler handler = new Handler();
        handler.postDelayed(() -> smsRecycler.hideShimmerAdapter(), 2000);
        this.smsList.addAll(smsList);
        smsAdapter.notifyDataSetChanged();
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

    private void setUpRecyclerView() {
        smsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL
                , false));
        smsAdapter = new SmsAdapter(this, smsList);
        smsRecycler.setAdapter(smsAdapter);
        smsRecycler.showShimmerAdapter();
    }


}
