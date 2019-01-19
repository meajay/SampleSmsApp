package assignment.com.smsapplication;

import android.app.Application;

import assignment.com.smsapplication.dagger.AppComponent;
import assignment.com.smsapplication.dagger.AppModule;

public class SmsApp extends Application {
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent().inject(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public AppComponent initAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                    .builder().appModule(new AppModule(this))
                    .mvpPresenterModule(new MvpPresenterModule())
                    .build();
        }
        return appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
