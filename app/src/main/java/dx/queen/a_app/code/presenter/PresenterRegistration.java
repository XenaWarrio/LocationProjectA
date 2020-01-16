package dx.queen.a_app.code.presenter;

import dx.queen.a_app.code.model.registration.Registration;
import dx.queen.a_app.code.mvp.AbstractPresenter;
import dx.queen.a_app.code.view.FragmentRegistrationContract;

public class PresenterRegistration extends AbstractPresenter implements FragmentRegistrationContract.Presenter {

    FragmentRegistrationContract.View view;
    FragmentRegistrationContract.Model model;

    public PresenterRegistration(){
        model = new Registration();
    }


    @Override
    public void createNewUser(String email, String password) {
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


}

