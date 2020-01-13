package dx.queen.a_app.code.model.registration;

import com.google.firebase.auth.FirebaseAuth;

import dx.queen.a_app.code.presenter.PresenterRegistration;
import dx.queen.a_app.code.view.FragmentRegistrationContract;

public class Registration implements FragmentRegistrationContract.Model {
    //FragmentRegistrationContract.Presenter presenter;

    PresenterRegistration presenter;

    public void newUser(String email , String password){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                presenter.taskSuccessful();

            } else {
                presenter.taskFailed();
            }
        });
    }
}
