package dx.queen.a_app.code;

import android.app.Application;
import android.content.Context;


public class AppInstance extends Application {
    public static AppInstance instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static AppInstance getInstance() {
        return instance;
    }

    public static  Context getContext() {
        return instance.getApplicationContext();
    }
}
