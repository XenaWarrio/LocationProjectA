package dx.queen.a_app.code.presenter;

import dx.queen.a_app.R;
import dx.queen.a_app.code.model.registration.Registration;
import dx.queen.a_app.code.mvp.AbstractPresenter;
import dx.queen.a_app.code.util.TextUtils;
import dx.queen.a_app.code.view.FragmentRegistrationContract;

public class PresenterRegistration extends AbstractPresenter implements FragmentRegistrationContract.Presenter {

    FragmentRegistrationContract.View view;
    FragmentRegistrationContract.Model model;

    public PresenterRegistration(){
        model = new Registration();
    }


    @Override
    public void createNewUser(String email, String password) {
        isCorrectEmailPassword(email, password);

        model.newUser(email, password);

    }

    public void taskSuccessful() {
        if (view != null) {
            view.progressBarVisible(false);
            view.showToast("You`re successfully registered! Try to sign in");
            view.switchToBaseFragment();
        }

    }

    public void taskFailed() {
        if (view != null) {
            view.progressBarVisible(false);
            view.showToast("ERROR!");
        }
    }

    private void isCorrectEmailPassword(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            view.setEmailError(view.getString(R.string.empty_email));
        } else if (email.length() < 8) {
            view.setEmailError(view.getString(R.string.little_email));
        } else if (!email.contains("@")) {
            view.setEmailError(view.getString(R.string.dog_email));
        }

        if (TextUtils.isEmpty(password)) {
            view.setPasswordError(view.getString(R.string.empty_password));
        } else if (password.length() < 7) {
            view.setPasswordError(view.getString(R.string.little_password));
        } else if (password.length() > 15) {
            view.setPasswordError(view.getString(R.string.big_password));
        }
    }
}

