package com.ways2u.android.goapp.util;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TokenInterceptord implements Interceptor {

     private final String TAG = "respond";

     @Override
     public Response intercept(Chain chain) throws IOException {
         Request oldRequest = chain.request();
         String url = oldRequest.url().toString();
         Response response = null;

         // 新的请求,添加参数
         Request newRequest = addParam(oldRequest);
         response = chain.proceed(newRequest);

         ResponseBody value = response.body();
         byte[] resp = value.bytes();
         String json = new String(resp, "UTF-8");

         // 判断stateCode值
         try {
             JSONObject jsonObject = new JSONObject(json);
             int stateCode = jsonObject.optInt("stateCode");
             if (stateCode == 3) {
                 String data = jsonObject.optString("data");
                 Log.d(TAG, "token失效，新的token：" + data);
                 //DataStorageUtils.saveToken(data);
                 // token失效，重新执行请求
                 Request newTokenRequest = addParam(oldRequest);
                 response = chain.proceed(newTokenRequest);
             } else {
                 // 这里值得注意。由于前面value.bytes()把响应流读完并关闭了，所以这里需要重新生成一个response，否则数据就无法正常解析了
                 response = response.newBuilder()
                         .body(ResponseBody.create(null, resp))
                         .build();
             }
         } catch (Exception e) {

         }

         return response;
     }

     /**
      * 添加公共参数
      *
      * @param oldRequest
      * @return
      */
     private Request addParam(Request oldRequest) {

         HttpUrl.Builder builder = oldRequest.url()
                 .newBuilder()
                 //.setEncodedQueryParameter("lversion", PackagesUtils.getAppVersionName())
                 //.setEncodedQueryParameter("token", DataStorageUtils.getToken())
         ;

         Request newRequest = oldRequest.newBuilder()
                 .method(oldRequest.method(), oldRequest.body())
                 .url(builder.build())
                 //.addHeader("Content-Encoding", "gzip")
                 .build();


         return newRequest;
     }
 }