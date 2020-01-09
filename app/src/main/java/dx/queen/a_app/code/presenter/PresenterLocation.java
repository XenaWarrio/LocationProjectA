package dx.queen.a_app.code.presenter;

import android.app.Activity;

import dx.queen.a_app.code.model.LocationTracking;
import dx.queen.a_app.code.mvp.AbstractPresenter;
import dx.queen.a_app.code.view.gps_fragment.FragmentLocationContract;

public class PresenterLocation extends AbstractPresenter implements FragmentLocationContract.Presenter {

    LocationTracking model;

    @Override
    public void startLocationTracking(Activity activity) {
        model.startTracking(activity);
    }

    @Override
    public void makeToast(String error) {
        view.showToast(error);
    }
}
