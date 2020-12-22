package fr.clic1prof.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.logging.Handler;

import fr.clic1prof.R;

public class LoadingDialog {
    private final Activity activity;
    private AlertDialog dialog;

    LoadingDialog(Activity activity){
        this.activity = activity;
    }

    public void launchLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_log,null));
        builder.setCancelable(false);
        dialog = builder.create();
    }

    public void startLoadingDialog(){ dialog.show();}
    public void dismissDialog(){
        dialog.dismiss();
    }

    public void ErrorDialog(){
        TextView text = dialog.findViewById(R.id.loadingText);
        text.setText(R.string.Dialog_error);
        dialog.setCancelable(true);
    }

    public void errorEntries(int color){
        TextView view = dialog.findViewById(R.id.loadingText);
        view.setText("Mail ou Mot de passe ne répond pas aux critères !");
        view.setTextColor(color);
        dialog.setCancelable(true);
    }
}
