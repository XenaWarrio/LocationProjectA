package dx.queen.a_app.code.presenter;

import android.content.Context;

import java.util.Date;

import dx.queen.a_app.code.model.LocationTracking;
import dx.queen.a_app.code.mvp.AbstractPresenter;
import dx.queen.a_app.code.view.gps_fragment.FragmentLocationContract;

public class PresenterLocation extends AbstractPresenter implements FragmentLocationContract.Presenter {

    FragmentLocationContract.Model model;
    FragmentLocationContract.View viewFragment;

    public PresenterLocation(FragmentLocationContract.View viewFragment){
        this.viewFragment = viewFragment;
        model = new LocationTracking();
    }

    @Override
    public void startLocationTracking() {
        model.startTracking();
    }

    public void removeUpdates(){
        model.removeUpdates();
    }

    public void checkEnebleGps(boolean enable){
        viewFragment.checkEnablegps(enable);

    }

    public void checkEnebleNet(boolean enable){
        viewFragment.checkEnablenet(enable);
    }

    public void checkEnable(){
        model.checkEnabled();
    }

    public void showLocationGPS(double lat, double longt, Date time){
        viewFragment.showLocationGPS(lat,longt,time);
    }
    public void showLocationNET(double lat, double longt, Date time){
        viewFragment.showLocationNET(lat,longt,time);


    }

    @Override
    public Context getContext() {
        return viewFragment.getContext();
    }

    @Override
    public void makeToast(String error) {
        view.showToast(error);
    }
}
