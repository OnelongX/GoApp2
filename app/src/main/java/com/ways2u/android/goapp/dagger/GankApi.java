package com.ways2u.android.goapp.dagger;


import com.ways2u.android.goapp.meizi.model.Meizi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {

    @GET("data/%E7%A6%8F%E5%88%A9/{count}/{page}")
    Observable<Meizi> latest(@Path("count") int count, @Path("page") int page);
    //多个Api
}
