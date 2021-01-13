package fr.clic1prof.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    public AbstractViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);}

    public abstract void onBind(T item);

}
