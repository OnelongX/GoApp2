package com.ways2u.android.goapp.dagger;

import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetrofitService {
            
        @GET("{url}")
        Call<JsonObject> getData(
                @Path("url") String url,
                @QueryMap Map<String, String> map
        );
    
        @FormUrlEncoded
        @POST( "{url}")
        Call<JsonObject> postData(
                @Path("url") String url,
                @FieldMap Map<String, String> map
        );
    
        @Multipart
        @POST( "{url}")
        Call<JsonObject> uploadFile(
                @Path("url") String url,
                @PartMap Map<String, RequestBody> map
        );
    }