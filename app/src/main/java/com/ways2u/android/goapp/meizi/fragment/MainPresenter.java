package com.ways2u.android.goapp.meizi.fragment;

import android.content.Context;
import android.graphics.Bitmap;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.ways2u.android.goapp.BasePresenter;
import com.ways2u.android.goapp.UICallCack;
import com.ways2u.android.goapp.meizi.api.MeiziApi;
import com.ways2u.android.goapp.meizi.model.Meizi;
import com.ways2u.android.goapp.util.ImageLoader;
import com.ways2u.android.net.util.JsonCallback;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huanglong on 2016/12/9.
 */

public class MainPresenter extends BasePresenter {
    private MainFragment context;

    public MainPresenter(MainFragment context) {
        this.context = context;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }

    public void getData(final int p, final UICallCack callCack) {


        MeiziApi.getMeiziList(context.getBaseActivity(), p, new JsonCallback() {
            @Override
            public void onFailure(Throwable te, String errorResponse) {
                if (callCack != null) {
                    callCack.onFailure(te, errorResponse);
                }

            }

            @Override
            public void onSuccess(JSONObject response) {
                if (response == null) {
                    return;
                }
                Meizi m = new Meizi(response);
                // 加载图片缓存，并保存尺寸数据到meizi
                Observable.just(m)
                        .compose(context.<Meizi>bindUntilEvent(FragmentEvent.DESTROY))
                        .map(new Function<Meizi, Meizi>() {
                            @Override
                            public Meizi apply(Meizi meizi) {
                                for (final Meizi.ResultsBean bean : meizi.getResults()) {
                                    Bitmap bitmap = ImageLoader.loadImageBitmap(bean.getUrl(),
                                            context.getBaseActivity());
                                    if (bitmap != null) {
                                        bean.setWidth(bitmap.getWidth());
                                        bean.setHeight(bitmap.getHeight());
                                    }
                                }
                                return meizi;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Meizi>() {
                            @Override
                            public void accept(Meizi meizi) throws Exception {
                                if (callCack != null) {
                                    callCack.onSuccess(meizi);
                                }
                            }
                        });


            }

            @Override
            public void onStart() {
                if (callCack != null) {
                    callCack.onStart();
                }
            }

            @Override
            public void onFinish() {
                if (callCack != null) {
                    callCack.onFinish();
                }
            }
        });
    }


}
