package dx.queen.a_app.code.mvp;

public class AbstractPresenter implements MvpContract.Presenter {
    protected MvpContract.View view;

    @Override
    public void subscribe(MvpContract.View view) {
        this.view = view;

    }

    @Override
    public void unsubscribe() {
        view = null;
    }
}

