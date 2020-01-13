package dx.queen.a_app.code.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import dx.queen.a_app.R;
import dx.queen.a_app.code.view.gps_fragment.LocationFragment;

public class MainActivity extends AppCompatActivity implements FragmentCallback {

    private SignInFragment signInFragment;
    private LoginFragment loginFragment;
    private LocationFragment locationFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInFragment = new SignInFragment();
        loginFragment = new LoginFragment();
        locationFragment = new LocationFragment();

        nextFragment(1);
    }


    @Override
    public void nextFragment(int item) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (item == 1) {
            transaction.replace(R.id.container, signInFragment);

        } else if (item == 2) {
            transaction.replace(R.id.container, loginFragment);

        } else if (item == 3) {
            transaction.replace(R.id.container, locationFragment);

        }
        transaction.commit();
    }
}
