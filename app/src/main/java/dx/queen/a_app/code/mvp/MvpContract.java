package dx.queen.a_app.code.mvp;

public abstract class MvpContract {

    public interface Presenter {

        void subscribe(MvpContract.View view);

        void unsubscribe();
    }

    public interface View {

        void showToast(String toast);

    }

}
