package dx.queen.a_app.code.view.gps_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import dx.queen.a_app.R;
import dx.queen.a_app.code.mvp.MvpContract;
import dx.queen.a_app.code.presenter.PresenterLocation;

public class LocationFragment extends Fragment implements MvpContract.View {

    PresenterLocation presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_screen, container, false);
        presenter = new PresenterLocation();
        presenter.subscribe(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.startLocationTracking(getActivity());

    }

    @Override
    public void showToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }



    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        super.onDestroy();
    }
}

