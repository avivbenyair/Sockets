package aviv.androidexercise_sockets.view.acitivity;


import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aviv.androidexercise_sockets.R;
import aviv.androidexercise_sockets.model.realm.QuoteLDB;
import aviv.androidexercise_sockets.view.QuoteAdapter;


import static aviv.androidexercise_sockets.network.SocketApi.JSON_SAMPLE;


public class MainScreenActivity extends BaseActivity<MainScreenPresenter> implements MainScreenPresenter.MainScreenListener {


    private RecyclerView mQuoteRecycler;
    private QuoteAdapter mQuoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLoader(true);
        initRecycler();
        mPresenter.connectWebSocket();
    }

    private void initRecycler() {
        mQuoteRecycler = (RecyclerView) findViewById(R.id.quote_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mQuoteRecycler.setLayoutManager(linearLayoutManager);

        mQuoteAdapter = new QuoteAdapter(new ArrayList<QuoteLDB>());
        mQuoteRecycler.setAdapter(mQuoteAdapter);
    }

    @Override
    protected MainScreenPresenter getPresenter() {
        return new MainScreenPresenter(new Handler());
    }

    @Override
    protected void bind() {
        mPresenter.bind(this);
    }

    @Override
    protected void unbind() {
        mPresenter.unbind();

    }

    @Override
    public void onError(String message) {
        Log.d("onError", "message: " + message);
    }

    @Override
    public void onStockFeedArrived(ArrayList<QuoteLDB> filteredContacts) {
        showLoader(false);
        mQuoteAdapter.setData(filteredContacts);
    }


    public void onSocketConnected() {
        mPresenter.sendMessage(JSON_SAMPLE);
    }

    @Override
    public void onSocketClosed() {
        Log.d("Socket", "onSocketClosed");
        showLoader(false);

    }

    @Override
    public void onSocketError(Exception e) {
        showLoader(false);
        showErrorDialog(getString(R.string.socket_error_title), getString(R.string.socket_error_message));
    }
}
