package dx.queen.a_app.code.presenter;

import android.content.Context;

import dx.queen.a_app.code.model.LocationTracking;
import dx.queen.a_app.code.mvp.AbstractPresenter;
import dx.queen.a_app.code.view.gps_fragment.FragmentLocationContract;

public class PresenterLocation extends AbstractPresenter implements FragmentLocationContract.Presenter {

    FragmentLocationContract.Model model;

    public PresenterLocation(){
        model = new LocationTracking();
    }

    @Override
    public void startLocationTracking(Context context) {
        model.startTracking(context);
    }

    @Override
    public void makeToast(String error) {
        view.showToast(error);
    }
}
