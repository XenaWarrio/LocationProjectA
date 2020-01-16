package dx.queen.a_app.code.view;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dx.queen.a_app.R;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SignInFragment extends Fragment  {

    private final int REQUEST_LOCATION_PERMISSION = 1;


    private Unbinder unbinder;
    private FragmentCallback callback;
    private FirebaseAuth auth;

    @NonNull
    @BindView(R.id.et_email)
    EditText email;
    @NonNull
    @BindView(R.id.et_password)
    EditText password;

    @OnClick(R.id.btn_signin)
    public void signIn() {

        emailAndPasswordValidation();

        callback.nextFragment(3);

    }

    @OnClick(R.id.btn_login)
    public void login() {
        callback.nextFragment(2);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signin, container, false);
        unbinder = ButterKnife.bind(this, v);
        requestLocationPermission();
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void emailAndPasswordValidation() {

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //Toast.makeText(getActivity(), "You have successfully sign in!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("TAG", "signInWithEmail:failure", task.getException());
                Toast.makeText(getActivity(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION };
        if(EasyPermissions.hasPermissions(getActivity(), perms)) {
            Toast.makeText(getContext(), "Permission already granted", Toast.LENGTH_SHORT).show();
        }
        else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (FragmentCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
