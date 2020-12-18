package fr.clic1prof.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.logging.Handler;

import fr.clic1prof.R;

public class LoadingDialog {
    private Activity activity;
    private AlertDialog dialog;

    LoadingDialog(Activity activity){
        this.activity = activity;
    }

    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_log,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }

    public void ErrorDialog(){
        TextView text = dialog.findViewById(R.id.loadingText);
        text.setText("Erreur de connection");
        dialog.setCancelable(true);
    }
}
