package com.ways2u.android.goapp.meizi.fragment;


import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;


import com.trello.rxlifecycle2.android.FragmentEvent;
import com.ways2u.android.common.LogUtil;
import com.ways2u.android.goapp.R;
import com.ways2u.android.goapp.UICallCack;
import com.ways2u.android.goapp.base.BaseFragment;
import com.ways2u.android.goapp.dagger.ApplicationScope;
import com.ways2u.android.goapp.dagger.DaggerAppComponent;
import com.ways2u.android.goapp.dagger.GankApi;
import com.ways2u.android.goapp.dagger.fragment.FragmentComponent;
import com.ways2u.android.goapp.dagger.fragment.FragmentModule;
import com.ways2u.android.goapp.dagger.qualifier.DefaultQualifier;
import com.ways2u.android.goapp.dagger.ui.ActivityComponent;
import com.ways2u.android.goapp.meizi.adapter.BaseRecyclerOnScrollListener;
import com.ways2u.android.goapp.meizi.adapter.MainAdapter;
import com.ways2u.android.goapp.meizi.api.MeiziApi;
import com.ways2u.android.goapp.meizi.model.Meizi;
import com.ways2u.android.goapp.meizi.view.BaseRecyclerView;
import com.ways2u.android.goapp.util.HUDUtil;
import com.ways2u.android.goapp.util.ImageLoader;
import com.ways2u.android.goapp.util.SharePreferencesUtil;
import com.ways2u.android.goapp.util.Validator;
import com.ways2u.android.net.util.JsonCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


//BaseFragment 是UI，应该实现UICallCack，异步回调数据
public class MainFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private BaseRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private BaseRecyclerOnScrollListener scrollListener;
    private MainAdapter mainAdapter;
    private List<Meizi.ResultsBean> datas = new ArrayList<>();

    private MainPresenter mainPresenter;

    private Activity tactivity;

    @Inject
    public void setActivity(Activity activity) {
        this.tactivity = activity;
    }

    @Inject
    public Application application;

    @Inject
    public HUDUtil hudUtil;

    @Inject
    public Validator validator;

    @Inject
    GankApi gankApi;

    @Inject
    @DefaultQualifier
    public SharePreferencesUtil preferencesUtil;


    private UICallCack<Meizi> meiziUICallCack = new UICallCack<Meizi>() {
        @Override
        public void onStart() {

        }

        @Override
        public void onFailure(Throwable e, String errorResponse) {
            scrollListener.loadingError();
        }

        @Override
        public void onSuccess(Meizi response) {
            datas.addAll(response.getResults());
            if (datas.size() >= 10) {
                mainAdapter.notifyItemRangeInserted(
                        datas.size() - 10,
                        response.getResults().size());
            } else {
                mainAdapter.notifyItemRangeInserted(
                        0,
                        response.getResults().size());

                //mainAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFinish() {
            swipeRefreshLayout.setRefreshing(false);
        }
    };


    @Override
    public void handleMessage(Message msg) {
        LogUtil.e(this, msg.obj.toString());
    }


        @Override
        protected void setupFragmentComponent(ActivityComponent activityComponent) {

            activityComponent.addSub(new FragmentModule(this)).inject(this);


        }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView() {

       // activity.getActivityComponent().addSub(new FragmentModule(this)).inject(this);
        LogUtil.i(this, tactivity + "");
        LogUtil.i(this, application + "");
        LogUtil.i(this, hudUtil + "");
        LogUtil.i(this, gankApi + "");
        LogUtil.i(this, preferencesUtil + "");

        preferencesUtil.setAndCommit("xxx", "one---long");
        LogUtil.i(this, preferencesUtil.getString("xxx"));

        LogUtil.i(this, validator.isEmpty("") + "");


        recyclerView = find(R.id.recyclerView);
        swipeRefreshLayout = find(R.id.swipeRefreshLayout);

        mainPresenter = new MainPresenter(this);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        mainAdapter = new MainAdapter();
        mainAdapter.setData(datas);
        recyclerView.setAdapter(mainAdapter);

        scrollListener = new BaseRecyclerOnScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mainPresenter.getData(currentPage, meiziUICallCack);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData(Bundle bundle) {
        datas.clear();
        mainAdapter.notifyDataSetChanged();
        scrollListener.clearPage();
        scrollListener.onLoadMore(1);

        Message message = (Message) activity.handler.obtainMessage(0, "Msg from MainFragment");
        activity.handler.postMessage(message);
    }

    @Override
    public void onRefresh() {
        datas.clear();
        mainAdapter.notifyDataSetChanged();
        scrollListener.clearPage();
        scrollListener.onLoadMore(1);
    }


}
