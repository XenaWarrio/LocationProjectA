package dx.queen.a_app.code.view.gps_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dx.queen.a_app.R;
import dx.queen.a_app.code.Mode;
import dx.queen.a_app.code.presenter.PresenterLocation;

public class LocationFragment extends Fragment implements FragmentLocationContract.View {

    @NonNull
    @BindView(R.id.tvenableGPS)
    TextView enableGps;
    @NonNull
    @BindView(R.id.tvEnableNET)
    TextView enableNet;
    @NonNull
    @BindView(R.id.tvlocationGPS)
    TextView locationGps;
    @NonNull
    @BindView(R.id.tvlocationNET)
    TextView locationNet;
    @NonNull
    @BindView(R.id.pb_main)
    ProgressBar progressBar;
    private PresenterLocation presenter;
    private Unbinder unbinder;

    @OnClick(R.id.bt_settings)
    public void changeSettings() {
        startActivity(new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_screen, container, false);
        unbinder = ButterKnife.bind(this, v);
        presenter = new PresenterLocation(this);
        presenter.subscribe(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //presenter.startLocationTracking(getActivity());
        progressBarVisible(true);
        presenter.startLocationTracking();
        presenter.checkEnable();


    }


    @Override
    public void showToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    public void checkEnablegps(boolean enable) {
        if (enable) {
            enableGps.setText(R.string.gpsIsOn);
        } else {
            enableGps.setText(R.string.gpsIsOff);

        }

        progressBarVisible(false);
    }

    public void checkEnablenet(boolean enable) {
        if (enable) {
            enableNet.setText(R.string.netIsOn);
        } else {
            enableNet.setText(R.string.netIsOff);

        }
        progressBarVisible(false);
    }


    public void showLocationGPS(double lat, double longt, Date time) {
        locationGps.setText(String.format(Locale.US, "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
                lat, longt, time));
        progressBarVisible(false);

    }

    public void showLocationNET(double lat, double longt, Date time) {
        locationNet.setText(String.format(Locale.US, "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
                lat, longt, time));

        progressBarVisible(false);

    }

    public Context getContext() {
        return getActivity();
    }



    public void progressBarVisible(boolean visibility) {
        if (visibility) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Mode.setMODE(true);
//        progressBarVisible(true);
//        presenter.startLocationTracking();
//        presenter.checkEnable();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.removeUpdates();
    }

    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        unbinder.unbind();

        super.onDestroy();
    }
}

