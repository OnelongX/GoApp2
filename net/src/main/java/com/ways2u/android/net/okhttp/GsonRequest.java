package com.ways2u.android.net.okhttp;

import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class GsonRequest<T> extends JsonRequest<T> {
    private final Response.Listener<T> mListener;


    public GsonRequest(int method, String url, String requestBody,  Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener,
                errorListener);


        setRetryPolicy(new DefaultRetryPolicy(30000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        mListener = listener;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response<T>) Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}