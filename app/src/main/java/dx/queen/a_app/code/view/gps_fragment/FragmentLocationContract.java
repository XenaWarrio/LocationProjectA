package dx.queen.a_app.code.view.gps_fragment;

import android.content.Context;

import java.util.Date;

import dx.queen.a_app.code.mvp.MvpContract;

public interface FragmentLocationContract {

    interface Presenter extends MvpContract.Presenter{
        void startLocationTracking();
        void makeToast(String error);
         void checkEnebleGps(boolean enable);
         void checkEnebleNet(boolean enable);
        void showLocationGPS(double lat, double longt, Date time);
        void showLocationNET(double lat, double longt, Date time);
        Context getContext();



    }


    interface View extends MvpContract.View{

        void showToast(String toast);

        void checkEnablegps(boolean enable);

        void checkEnablenet(boolean enable);

        void showLocationGPS(double lat, double longt, Date time);

        void showLocationNET(double lat, double longt, Date time);

        Context getContext();


    }
    interface Model {
        void startTracking();
         void checkEnabled();
         void removeUpdates();
    }
}
