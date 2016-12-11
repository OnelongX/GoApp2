package com.ways2u.android.goapp.util;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseInterceptor implements Interceptor {
@Override    
public Response intercept(Chain chain) throws IOException {
     Request original = chain.request();
//添加请求参数，此处是以豆瓣api为例，下面会贴出Base_url
     HttpUrl url=original.url().newBuilder()
              .addQueryParameter("count", "5")    
              .addQueryParameter("start", "0")          
              .build();   
//添加请求头
    Request request = original.newBuilder()     
             .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")         
             .addHeader("Connection", "keep-alive")    
             .method(original.method(), original.body())   
             .url(url)   
            .build();     
    return chain.proceed(request); 
 }}