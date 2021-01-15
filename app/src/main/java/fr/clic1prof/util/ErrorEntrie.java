package fr.clic1prof.util;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import fr.clic1prof.R;

public class ErrorEntrie {


    private final TextView error;

    public ErrorEntrie(TextView view){
        this.error = view;
        this.error.setVisibility(View.GONE);
        this.cleanse();
    }

    public ErrorEntrie(ErrorEntrie errorEntrie){
        this.error = errorEntrie.error;
        this.error.setVisibility(View.INVISIBLE);
    }

    public TextView getError() {
        return error;
    }

    public void showError(){
        this.error.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cleanse();
            }
        }, 5000);
    }

    public void cleanse(){
        if(this.error.getVisibility() == View.VISIBLE) {
            this.error.setVisibility(View.GONE);
            this.error.setText(R.string.Dialog_error);
        }
    }

    public void setText(String string){
        error.setText(string);
    }

    public void setText(int value){error.setText(value);}

}
