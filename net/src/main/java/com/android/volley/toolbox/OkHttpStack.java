package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.ways2u.android.net.okhttp.CookiesManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpStack extends HurlStack{
    private final OkHttpClient mClient;//提供的可以克隆的OkHttpClient，例如HTTPS请求
    public OkHttpStack(OkHttpClient client) {
        this.mClient = client;
    }

    //@SuppressWarnings("deprecation")
    private  void setConnectionParametersForRequest (okhttp3.Request.Builder builder, Request<?> request)
            throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case Request.Method.DEPRECATED_GET_OR_POST:
                byte[] postBody = request.getBody();
                if (postBody != null) {
                    builder.post(RequestBody.create
                            (MediaType.parse(request.getBodyContentType()), postBody));
                }
                break;

            case Request.Method.GET:
                builder.get();
                break;

            case Request.Method.DELETE:
                builder.delete();
                break;

            case Request.Method.POST:
                builder.post(createRequestBody(request));
                break;

            case Request.Method.PUT:
                builder.put(createRequestBody(request));
                break;

            case Request.Method.HEAD:
                builder.head();
                break;

            case Request.Method.OPTIONS:
                builder.method("OPTIONS", null);
                break;

            case Request.Method.TRACE:
                builder.method("TRACE", null);
                break;

            case Request.Method.PATCH:
                builder.patch(createRequestBody(request));
                break;

            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    /**
     * 如果需要对OkHttp的请求体进行修改或者添加一些内容可以在这个地方修改
     * @param request
     * @return
     * @throws AuthFailureError
     */
    protected  RequestBody createRequestBody(Request request) throws AuthFailureError {
        final byte[] body = request.getBody();
        if (body == null) return null;

        return RequestBody.create(MediaType.parse(request.getBodyContentType()), body);
    }

    @Override
    public Response performRequest(Request<?> request, Map<String, String> additionalHeaders)
            throws IOException, AuthFailureError {

        int timeoutMs = request.getTimeoutMs();
        OkHttpClient client = mClient.newBuilder()
                .readTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .connectTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .writeTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .build();
        okhttp3.Request.Builder okHttpRequestBuilder = new okhttp3.Request.Builder();

        Map<String, String> headers = request.getHeaders();
        for (final String name : headers.keySet()) {
            okHttpRequestBuilder.addHeader(name, headers.get(name));
        }

        for (final String name : additionalHeaders.keySet()) {
            okHttpRequestBuilder.addHeader(name, additionalHeaders.get(name));
        }

        setConnectionParametersForRequest(okHttpRequestBuilder, request);

        okhttp3.Request okhttp3Request = okHttpRequestBuilder.url(request.getUrl()).build();
        if(request.isCanceled())
        {
            return null;
        }
        Response okHttpResponse = client.newCall(okhttp3Request).execute();

        return okHttpResponse;
    }
}
