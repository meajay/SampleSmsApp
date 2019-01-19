package assignment.com.smsapplication.sms.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import assignment.com.smsapplication.R;
import assignment.com.smsapplication.SmsApp;
import assignment.com.smsapplication.sms.presenter.SmsMvpPresenter;
import assignment.com.smsapplication.sms.presenter.SmsPresenter;

public class SmsActivity extends AppCompatActivity implements SmsMvpView {

    @Inject
    SmsPresenter smsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        injectDependencies();
        smsPresenter.onAttach(this);
    }

    private void injectDependencies() {
        ((SmsApp) getApplication()).getAppComponent().inject(this);
    }


}
