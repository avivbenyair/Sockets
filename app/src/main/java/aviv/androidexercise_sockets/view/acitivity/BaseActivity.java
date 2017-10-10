package aviv.androidexercise_sockets.view.acitivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import aviv.androidexercise_sockets.presenter.Presenter;
import aviv.androidexercise_sockets.view.dialog.DialogLoader;


public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity {
    public P mPresenter;
    private DialogLoader dialogLoader;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        bind();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void showErrorDialog(String title,String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public void showLoader(boolean enable) {
        if (enable) {
            dialogLoader = new DialogLoader();
            dialogLoader.show(getFragmentManager(), "loader");
        } else if (dialogLoader != null) {
            try {
                dialogLoader.dismiss();
            } catch (Exception e) {

            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected abstract P getPresenter();

    protected abstract void bind();

    protected abstract void unbind();

}
