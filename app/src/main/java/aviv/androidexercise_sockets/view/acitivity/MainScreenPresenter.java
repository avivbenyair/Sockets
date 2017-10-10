package aviv.androidexercise_sockets.view.acitivity;

import android.os.Handler;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import aviv.androidexercise_sockets.model.gson.GsonManager;
import aviv.androidexercise_sockets.model.gson.Quote;
import aviv.androidexercise_sockets.model.realm.QuoteLDB;
import aviv.androidexercise_sockets.model.realm.RealmManager;
import aviv.androidexercise_sockets.presenter.Presenter;
import aviv.androidexercise_sockets.presenter.interfaces.BaseView;

import static aviv.androidexercise_sockets.network.SocketApi.SOCKET_URL;


public class MainScreenPresenter extends Presenter<MainScreenPresenter.MainScreenListener> {

    private WebSocketClient mWebSocketClient;
    private Handler mHandler;

    public MainScreenPresenter(Handler handler) {
        this.mHandler = handler;
    }

    public void connectWebSocket() {
        URI uri;
        try {
            uri = new URI(SOCKET_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.onSocketConnected();
                    }
                });
            }

            @Override
            public void onMessage(String message) {
                final List<Quote> quotes = GsonManager.convertMessageJsonToStocksArray(mGson, message);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<QuoteLDB> quoteLDBs = RealmManager.saveToQuoteToRealm(quotes, mRealm);
                        mView.onStockFeedArrived(quoteLDBs);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.onSocketClosed();
                    }
                });
            }

            @Override
            public void onError(final Exception e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.onSocketError(e);
                    }
                });
            }
        };
        mWebSocketClient.connect();
    }

    public void sendMessage(String message) {
        mWebSocketClient.send(message);
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    public interface MainScreenListener extends BaseView {
        void onStockFeedArrived(ArrayList<QuoteLDB> filteredContacts);

        void onSocketConnected();

        void onSocketClosed();

        void onSocketError(Exception e);
    }

}
