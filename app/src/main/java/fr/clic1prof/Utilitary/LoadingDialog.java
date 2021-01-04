package fr.clic1prof.Utilitary;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TextView;


import fr.clic1prof.R;

public class LoadingDialog {
    private final Activity activity;
    private AlertDialog dialog;

    public LoadingDialog(Activity activity){
        this.activity = activity;
    }

    public void launchLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_log,null));
        builder.setCancelable(false);
        dialog = builder.create();
    }

    public void startLoadingDialog(){
        dialog.show();
    }
    public void dismissDialog(){
        if(this.dialog != null)dialog.dismiss();
    }

    public void errorDialog(int string){
        if(dialog == null){
            launchLoadingDialog();
        }
        TextView text = dialog.findViewById(R.id.loadingText);
        text.setText(string);
        dialog.setCancelable(true);

    }

    public void inscriptionText(){
        TextView text = dialog.findViewById(R.id.loadingText);
        text.setText(R.string.Dialog_inscriptionLoading);
    }

    public void connectionText(){
        TextView text = dialog.findViewById(R.id.loadingText);
        text.setText(R.string.Dialog_connectionLoading);
    }
}
