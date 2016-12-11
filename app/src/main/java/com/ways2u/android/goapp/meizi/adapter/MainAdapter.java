package com.ways2u.android.goapp.meizi.adapter;

import android.content.Intent;
import android.view.View;


import com.ways2u.android.goapp.R;
import com.ways2u.android.goapp.base.BaseRecyclerAdapter;
import com.ways2u.android.goapp.meizi.activity.SubActivity;
import com.ways2u.android.goapp.meizi.model.Meizi;
import com.ways2u.android.goapp.meizi.view.ScaleImageView;
import com.ways2u.android.goapp.util.ImageLoader;

import java.util.List;

/**
 * Created by huanglong on 2016/12/6.
 */

public class MainAdapter extends BaseRecyclerAdapter<Meizi.ResultsBean,MainAdapter.MeiziViewHolder> {
    @Override
    protected int getLayoutId() {
        return R.layout.meizi_rv_item;
    }

    @Override
    protected MeiziViewHolder createViewHolder(View view) {
        return new MeiziViewHolder(view,getData());
    }

    public static class MeiziViewHolder extends BaseRecyclerAdapter.BaseViewHolder<Meizi.ResultsBean> implements View.OnClickListener {

        private static final String TAG = "MeiziViewHolder";

        ScaleImageView itemImageView;
        private List<Meizi.ResultsBean> data;

        public MeiziViewHolder(View itemView, List<Meizi.ResultsBean> data) {
            super(itemView);
            this.data = data;

            itemImageView = (ScaleImageView)itemView.findViewById(R.id.iv_item);
            itemImageView.setOnClickListener(this);
        }

        @Override
        protected void bindData(Meizi.ResultsBean data) {
            itemImageView.setOriginalSize(data.getWidth(),data.getHeight());
            ImageLoader.loadImage(data.getUrl(),itemImageView,itemImageView.getContext());
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), SubActivity.class));
        }
    }
}
