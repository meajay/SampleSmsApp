package assignment.com.smsapplication.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Scope;

import assignment.com.smsapplication.dagger.qualifier.ApplicationContext;
import assignment.com.smsapplication.dagger.scope.AppScope;
import assignment.com.smsapplication.utils.AppPermissions;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @AppScope
    public Application providesApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    @AppScope
    Context provideContext() {
        return application.getBaseContext();
    }

}
