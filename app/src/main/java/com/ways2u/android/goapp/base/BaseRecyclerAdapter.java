package com.ways2u.android.goapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;


public abstract class BaseRecyclerAdapter<D, VH extends BaseRecyclerAdapter.BaseViewHolder<D>>
        extends RecyclerView.Adapter<VH> {

    private List<D> data;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent,
                false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    protected abstract int getLayoutId();

    protected abstract VH createViewHolder(View view);

    public List<D> getData() {
        return data;
    }

    public void setData(List<D> data) {
        this.data = data;
    }

    public abstract static class BaseViewHolder<D> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        protected abstract void bindData(D data);
    }
}
