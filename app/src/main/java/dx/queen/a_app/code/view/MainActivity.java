package dx.queen.a_app.code.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import dx.queen.a_app.R;

public class MainActivity extends AppCompatActivity {

    private SignInFragment signInFragment;
    private LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInFragment = new SignInFragment();
        loginFragment = new LoginFragment();


        if (savedInstanceState == null) {
            onNavigationItemSelected(1);
        }
    }

    public void onNavigationItemSelected(int item) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (item == 1) {
            transaction.replace(R.id.container, signInFragment);

        } else if (item == 2) {
            transaction.replace(R.id.container, loginFragment);

        } else if (item == 3) {
            //transaction.replace(R.id.container, fvistavki);

        }
        transaction.commit();
    }

}
