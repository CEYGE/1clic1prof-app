package fr.clic1prof.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class AbstractAdapter<T, VH extends AbstractViewHolder<T>> extends RecyclerView.Adapter<VH>  {
    private List<T> items;
    private LayoutInflater layoutInflater;

    public AbstractAdapter(Context context, RecyclerViewClickListener clickListener) {
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Implemented for a specific adapter
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * Method called by the recyclerView to set data for the position.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        T currentItem = items.get(position);
        holder.onBind(currentItem);
    }

    @Override
    public int getItemCount() { return items.size(); }

    /**
     * Sets items to the adapter and notifies changements.
     * @param items
     */
    public void setItems(List<T> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent, final boolean attachToRoot) {
        return layoutInflater.inflate(layout, parent, attachToRoot);
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, final @Nullable ViewGroup parent) {
        return inflate(layout, parent, false);
    }

    public interface RecyclerViewClickListener { void onClick(View v, int document_id, String document_name); }

}
