package assignment.com.smsapplication.dagger;

import android.support.transition.Transition;

import assignment.com.smsapplication.dagger.scope.AppScope;
import assignment.com.smsapplication.sms.presenter.SmsPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @AppScope
    SmsPresenter provideWeatherPresenter() {
        return new SmsPresenter();
    }
}
