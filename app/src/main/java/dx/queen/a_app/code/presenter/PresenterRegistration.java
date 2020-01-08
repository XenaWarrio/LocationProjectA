package dx.queen.a_app.code.presenter;

import com.google.firebase.auth.FirebaseAuth;

import dx.queen.a_app.R;
import dx.queen.a_app.code.mvp.AbstractPresenter;
import dx.queen.a_app.code.util.TextUtils;
import dx.queen.a_app.code.view.FragmentRegistrationContract;

public class PresenterRegistration extends AbstractPresenter<FragmentRegistrationContract.View> implements FragmentRegistrationContract.Presenter  {

    public PresenterRegistration(FragmentRegistrationContract.View view){
        this.view = view;

    }

    @Override
    public void createNewUser(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            view.setEmailError(view.getString(R.string.empty_email));
        } else if (email.length() < 8) {
            view.setEmailError(view.getString(R.string.little_email));
        } else if(! email.contains("@")){
            view.setEmailError(view.getString(R.string.dog_email));
        }

        if (TextUtils.isEmpty(password)) {
            view.setPasswordError(view.getString(R.string.empty_password));
        } else if (password.length() < 7) {
            view.setPasswordError(view.getString(R.string.little_password));
        } else if(password.length() > 15){
            view.setPasswordError(view.getString(R.string.big_password));
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                if (view!= null){
                    view.progressBarVisible(false);
                    view.showToast("You`re successfully registered! Try to sign in");
                    view.switchToBaseFragment();
                }

            } else {
                if (view != null){
                    view.progressBarVisible(false);
                    view.showToast("ERROR!");
                }

            }
        });
    }
}

