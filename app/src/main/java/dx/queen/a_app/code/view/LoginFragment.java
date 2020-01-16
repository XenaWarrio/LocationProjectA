package dx.queen.a_app.code.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dx.queen.a_app.R;
import dx.queen.a_app.code.presenter.PresenterRegistration;

public class LoginFragment extends Fragment implements FragmentRegistrationContract.View {
    @NonNull
    @BindView(R.id.et_emailR)
    EditText email;
    @NonNull
    @BindView(R.id.et_passwordR)
    EditText password;
    @NonNull
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private PresenterRegistration presenter;
    private Unbinder unbinder;
    private FragmentCallback callback;

    @OnClick(R.id.bt_done)
    public void done() {
        addNewUser();
        clearFields();
    }

    @OnClick(R.id.bt_back)
    public void back() {
        switchToBaseFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PresenterRegistration();
        presenter.subscribe(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registration, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;

    }


    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void progressBarVisible(boolean visibility) {
        if (visibility) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void clearFields() {
        password.setText("");
        email.setText("");
    }

    @Override
    public void switchToBaseFragment() {
        callback.nextFragment(1);
    }

    @Override
    public void addNewUser() {
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();

        if (emailS.isEmpty() && passwordS.isEmpty()) {
            Toast.makeText(getContext(),"Fields must be filled", Toast.LENGTH_LONG).show();
        } else if (passwordS.equals("test")) {
            setPasswordError("Lёsha etot parol slishkom prost");
        } else if (emailS.isEmpty()) {
            setEmailError(getString(R.string.empty_email));
        } else if (emailS.length() < 8) {
            setEmailError(getString(R.string.little_email));
        } else if (!emailS.contains("@")) {
            setEmailError(getString(R.string.dog_email));
        }else if (passwordS.isEmpty()) {
            setPasswordError(getString(R.string.empty_password));
        } else if (passwordS.length() < 7) {
            setPasswordError(getString(R.string.little_password));
        } else if (passwordS.length() > 15) {
            setPasswordError(getString(R.string.big_password));
        }else{
            presenter.createNewUser(emailS, passwordS);
            progressBarVisible(true);
        }


    }

    @Override
    public void setEmailError(String error) {
        email.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        password.setError(error);
    }

    @Override
    public String getString(Integer resId) {
        return Objects.requireNonNull(getContext()).getString(resId);
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
        presenter.unsubscribe();
        super.onDestroy();
    }

}
