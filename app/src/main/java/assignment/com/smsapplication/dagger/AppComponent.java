package assignment.com.smsapplication.dagger;

import assignment.com.smsapplication.SmsActivity;
import assignment.com.smsapplication.SmsApp;
import assignment.com.smsapplication.dagger.scope.AppScope;
import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, MvpPresenterModule.class})
public interface AppComponent {
    void inject(SmsApp smsApp);

    void inject(SmsActivity smsActivity);
}
