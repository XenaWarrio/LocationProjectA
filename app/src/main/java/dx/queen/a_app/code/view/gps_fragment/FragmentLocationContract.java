package dx.queen.a_app.code.view.gps_fragment;

import android.content.Context;

import dx.queen.a_app.code.mvp.MvpContract;

public interface FragmentLocationContract {

    interface Presenter extends MvpContract.Presenter{
        void startLocationTracking(Context context);
        void makeToast(String error);
    }

    interface Model {
        void startTracking(Context context);
    }
}
