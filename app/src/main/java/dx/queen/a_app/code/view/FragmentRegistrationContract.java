package dx.queen.a_app.code.view;

import dx.queen.a_app.code.mvp.MvpContract;

public interface FragmentRegistrationContract {

    interface View extends MvpContract.View {
        void showToast(String message);
        void progressBarVisible(boolean visibility);
        void clearFields();
        void switchToBaseFragment();
        void addNewUser();
        void setEmailError(String error);
        void setPasswordError(String error);
        String getString(Integer resId);
    }


    interface Presenter extends MvpContract.Presenter<View> {
        void createNewUser(String email, String password);
    }
}
