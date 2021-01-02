package fr.clic1prof.activities;

import android.content.res.Resources;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.TextView;

import fr.clic1prof.R;

public class ErrorEntrie {


    private final TextView error;

    public ErrorEntrie(TextView view){
        this.error = view;
    }

    public TextView getError() {
        return error;
    }

    public void showError(){
        this.error.setText(R.string.error_Entries);
        this.error.setVisibility(View.VISIBLE);
    }

    public void cleanse(){
        if(this.error.getVisibility() == View.VISIBLE) {
            this.error.setVisibility(View.GONE);
        }
    }

    public void setText(String string){
        error.setText(string);
    }

    public void setText(int value){error.setText(value);}

}
