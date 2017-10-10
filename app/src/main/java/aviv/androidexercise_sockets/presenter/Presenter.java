package aviv.androidexercise_sockets.presenter;


import com.google.gson.Gson;

import aviv.androidexercise_sockets.presenter.interfaces.BaseView;
import io.realm.Realm;


public abstract class Presenter<V extends BaseView> {
    public final String TAG = getClass().getSimpleName();
    protected V mView;
    protected Gson mGson;
    protected Realm mRealm;

    public void bind(V view) {
        this.mView = view;
        this.mGson = new Gson();
        this.mRealm = Realm.getDefaultInstance();
    }

    public void unbind() {
        mRealm.close();
        this.mView = null;
        this.mGson = null;
    }

    public abstract void onResume();

    public abstract void onPause();
}