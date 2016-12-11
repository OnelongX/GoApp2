package com.ways2u.android.net.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

public class OKHttpUtil {
    private Object tag;

    public OKHttpUtil(Object activity) {
        if (activity != null)
            tag = activity.getClass().getName();
    }

    public void post(String urlString, final StringCallback res) // 用一个完整url获取一个string对象
    {
        if (res != null) {
            res.onStart();
        }
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (res != null) {
                            res.onSuccess(response);
                            res.onFinish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        });

        // 把这个请求加入请求队列
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        NetContext.getInstance().addRequest(stringRequest);
        //ApplicationContext.getInstance().getRequestQueue().post(urlString, res);
    }

    public void post(String urlString, final Map<String, String> params,
                     final StringCallback res) // url里面带参数
    {
        if (res != null) {
            res.onStart();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (res != null) {
                            res.onSuccess(response);
                            res.onFinish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        NetContext.getInstance().addRequest(stringRequest);
        //ApplicationContext.getInstance().getRequestQueue().post(urlString, params, res);
    }

    public void post(String urlString, final JsonCallback res) // 不带参数，获取json对象或者数组
    {
        if (res != null) {
            res.onStart();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (res != null) {
                            try {
                                res.onSuccess(new JSONObject(response));
                            } catch (Exception e) {
                                res.onSuccess(new JSONObject());
                                e.printStackTrace();
                            }
                            res.onFinish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        });

        // 把这个请求加入请求队列
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        NetContext.getInstance().addRequest(stringRequest);
        //ApplicationContext.getInstance().getRequestQueue().post(urlString, res);
    }

    public void post(String urlString, final Map<String, String> params,
                     final JsonCallback res) // 带参数，获取json对象或者数组
    {
        if (res != null) {
            res.onStart();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (res != null) {
                            try {
                                res.onSuccess(new JSONObject(response));
                            } catch (Exception e) {
                                res.onSuccess(new JSONObject());
                                e.printStackTrace();
                            }
                            res.onFinish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        NetContext.getInstance().addRequest(stringRequest);
    }

    public void post(String uString, final ByteArrayCallback res) // 下载数据使用，会返回byte数据
    {
        if (res != null) {
            res.onStart();
        }
        Response.ErrorListener rl = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        };

        Request request = new Request<byte[]>(Request.Method.POST, uString, rl) {

            @Override
            protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
                return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
            }

            @Override
            protected void deliverResponse(byte[] response) {
                if (res != null && !isCanceled()) {
                    res.onSuccess(response);
                    res.onFinish();
                }
            }
        };

        // 把这个请求加入请求队列
        request.setTag(tag);
        request.setShouldCache(false);
        NetContext.getInstance().addRequest(request);
    }

    public void get(String urlString, final StringCallback res) // 用一个完整url获取一个string对象
    {
        if (res != null) {
            res.onStart();
        }
        //System.out.println(urlString);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //在这里操作UI组件是安全的，因为响应返回时这个函数会被post到UI线程来执行
                        // 在这里尽情蹂躏响应的String。
                        if (res != null) {
                            res.onSuccess(response);
                            res.onFinish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        });

        // 把这个请求加入请求队列
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        NetContext.getInstance().addRequest(stringRequest);
    }

    public void get(String urlString, final Map<String, String> params,
                    final StringCallback res) // url里面带参数
    {
        if (res != null) {
            res.onStart();
        }
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                encodedParams.append('&');
            }
            if (encodedParams.length() > 0) {
                encodedParams.deleteCharAt(encodedParams.length() - 1);
            }
        } catch (UnsupportedEncodingException uee) {
        }

        if (urlString.indexOf("?") != -1) {
            urlString = urlString + encodedParams.toString();
        } else {
            urlString = urlString + "?" + encodedParams.toString();
        }


        //System.out.println(urlString);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //在这里操作UI组件是安全的，因为响应返回时这个函数会被post到UI线程来执行
                        // 在这里尽情蹂躏响应的String。
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        });

        // 把这个请求加入请求队列
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        NetContext.getInstance().addRequest(stringRequest);
    }

    public void get(String urlString, final JsonCallback res) // 不带参数，获取json对象或者数组
    {
        if (res != null) {
            res.onStart();
        }
        //System.out.println(urlString);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (res != null) {
                            //KLog.i(response);
                            try {
                                res.onSuccess(new JSONObject(response));
                            } catch (Exception e) {
                                res.onSuccess(new JSONObject());
                                e.printStackTrace();
                            }
                            res.onFinish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        });

        // 把这个请求加入请求队列
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        NetContext.getInstance().addRequest(stringRequest);

        //ApplicationContext.getInstance().getRequestQueue().get(urlString, res);
    }

    public void get(String urlString, final Map<String, String> params,
                    final JsonCallback res) // 带参数，获取json对象或者数组
    {
        if (res != null) {
            res.onStart();
        }
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                encodedParams.append('&');
            }
            if (encodedParams.length() > 0) {
                encodedParams.deleteCharAt(encodedParams.length() - 1);
            }
        } catch (UnsupportedEncodingException uee) {
        }

        if (urlString.indexOf("?") != -1) {
            urlString = urlString + encodedParams.toString();
        } else {
            urlString = urlString + "?" + encodedParams.toString();
        }


        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (res != null) {
                            try {
                                res.onSuccess(new JSONObject(response));
                            } catch (Exception e) {
                                res.onSuccess(new JSONObject());
                                e.printStackTrace();
                            }
                            res.onFinish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (res != null) {
                    res.onFailure(error.getCause(), error.getLocalizedMessage());
                    res.onFinish();
                }
            }
        });

        // 把这个请求加入请求队列
        stringRequest.setTag(tag);
        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        NetContext.getInstance().addRequest(stringRequest);
    }

    public void get(String urlString, final ByteArrayCallback bHandler) // 下载数据使用，会返回byte数据
    {
        if (bHandler != null) {
            bHandler.onStart();
        }
        //System.out.println(urlString);

        Response.ErrorListener rl = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (bHandler != null) {
                    bHandler.onFailure(error.getCause(), error.getLocalizedMessage());
                    bHandler.onFinish();
                }
            }
        };

        Request request = new Request<byte[]>(Request.Method.GET, urlString, rl) {

            @Override
            protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
                return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
            }

            @Override
            protected void deliverResponse(byte[] response) {
                if (bHandler != null && !isCanceled()) {
                    bHandler.onSuccess(response);
                    bHandler.onFinish();
                }
            }
        };

        // 把这个请求加入请求队列
        request.setTag(tag);
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));

        NetContext.getInstance().addRequest(request);
    }

    public void cancel() {
        NetContext.getInstance().cancelAll(tag);
    }

}
