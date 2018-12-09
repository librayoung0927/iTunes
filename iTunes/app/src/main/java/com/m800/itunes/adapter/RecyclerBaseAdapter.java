package com.m800.itunes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.m800.itunes.dataModel.RecyclerBaseItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerBaseAdapter<T extends RecyclerBaseItem> extends RecyclerView.Adapter{
    static final String TAG = RecyclerBaseAdapter.class.getSimpleName();
    protected Context ctx;
    protected LayoutInflater layoutInflater;
    protected List<T> list;

    AdapterView.OnItemClickListener onItemClickListener;

    public RecyclerBaseAdapter(Context ctx, List<T> list) {
        this.ctx = ctx;
        this.layoutInflater = LayoutInflater.from(ctx);
        setList(list);
    }

    public void remove(int position) {
        if(list != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(T item, int position) {
        if(list == null)
            list = new ArrayList<>();
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void setList(List<T> list){
        Log.d(TAG, "setList");

        if(this.list == null)
            this.list = new ArrayList<>();
        else {
            this.list.clear();
            notifyDataSetChanged();
        }

        if(list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public List<T> getList(){
        return list;
    }

    public void addAll(List<T> list){
        if(list != null) {
            int orgItemCount = getItemCount();
            int extraItemCount = list != null ? list.size() : 0;
            if (this.list == null)
                this.list = new ArrayList<>();

            this.list.addAll(list);
            notifyItemRangeInserted(orgItemCount, extraItemCount);
        }
    }


    public void clear(){
        int orgItemCount = getItemCount();

        this.list.clear();
        if(orgItemCount > 0)
            notifyItemRangeRemoved(0, orgItemCount);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        onItemClickListener = listener;
    }



    public T getItem(int position){
        return (list != null && position > -1 && position < list.size())? list.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return list != null? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, String.format("getItemViewType :%d", position));
        return getItemCount() > 0 && position >= 0 && position < getItemCount()? getItem(position).getLayoutType() : -1;
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position);

}