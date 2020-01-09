package dx.queen.a_app.code.view.gps_fragment;

import android.app.Activity;

import dx.queen.a_app.code.mvp.MvpContract;

public interface FragmentLocationContract {

     interface View extends MvpContract.View{
        void showToast(String message);
    }

    interface Presenter extends MvpContract.Presenter{
        void startLocationTracking(Activity activity);
        void makeToast(String error);
    }
}
