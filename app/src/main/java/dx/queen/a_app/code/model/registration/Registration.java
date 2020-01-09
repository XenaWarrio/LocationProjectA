package dx.queen.a_app.code.model.registration;

import com.google.firebase.auth.FirebaseAuth;

import dx.queen.a_app.code.presenter.PresenterRegistration;

public class Registration {
    private PresenterRegistration presenter;

    public Registration(PresenterRegistration presenter){
        this.presenter = presenter;
    }

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
