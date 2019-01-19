package assignment.com.smsapplication.dagger;

import android.content.Context;
import android.support.transition.Transition;

import assignment.com.smsapplication.dagger.qualifier.ApplicationContext;
import assignment.com.smsapplication.dagger.scope.AppScope;
import assignment.com.smsapplication.sms.presenter.SmsPresenter;
import assignment.com.smsapplication.utils.SmsAPI;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @AppScope
    SmsAPI provideSmsAPI(@ApplicationContext Context context){
        return new SmsAPI(context);
    }
    @Provides
    @AppScope
    SmsPresenter provideWeatherPresenter(SmsAPI smsAPI) {
        return new SmsPresenter(smsAPI);
    }


}
