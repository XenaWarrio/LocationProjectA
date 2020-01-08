package dx.queen.a_app.code.mvp;

public abstract class MvpContract {

    public interface Presenter<T extends MvpContract.View> {

        void subscribe(T view);

        void unsubscribe();
    }

    public interface View {

    }
}
