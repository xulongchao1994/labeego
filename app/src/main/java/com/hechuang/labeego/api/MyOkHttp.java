package com.hechuang.labeego.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.hechuang.labeego.data.UserData;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求工具类
 */

public class MyOkHttp {
    private static MyOkHttp mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler = null;//切换线程
    private static Context mContext;

    private MyOkHttp() {
        mOkHttpClient = ApiFactify.getOkHttpClient(mContext);
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static void initContext(Context context) {
        mContext = context;
    }

    /**
     * 获取网络请求实例
     *
     * @return MyOkHttp
     */
    public static MyOkHttp getInstance() {
        if (mInstance == null) {
            synchronized (MyOkHttp.class) {
                if (mInstance == null) {
                    mInstance = new MyOkHttp();
                }
            }
        }
        return mInstance;
    }

    /**
     * 请求回调[okhttp请求回来的方法是在工作线程中，需要将线程切换到UI线程，这里理由handler+callback]
     */
    public interface RequestCallBack {
        /**
         * 请求成功
         *
         * @param data 返回的结果
         */
        void success(String data);

        /**
         * 请求失败
         *
         * @param request 请求的对列
         * @param e       错误信息
         */
        void fail(Request request, Exception e);
    }

    /**
     * 使用Get方式进行请求
     *
     * @param url      请求地址
     * @param callBack 请求的回调
     */
    public void get(String url, final RequestCallBack callBack) {
        final Request request = new Request.Builder().url(url).get().build();
        call(request, callBack);
    }

    /**
     * 使用Get方式进行请求
     *
     * @param url      请求地址
     * @param callBack 请求的回调
     */
    public void get(String url, final RequestCallBack callBack, boolean isAddCookie) {
        Request request = null;
        if (UserData.USERSESSIONID != null || !UserData.USERSESSIONID.equals("")) {
            if (isAddCookie) {
//                if (UserRelated.islogin) {
                request = new Request.Builder().url(url)
                        .addHeader("cookie", "PHPSESSID=" + UserData.USERSESSIONID).build();//"PHPSESSID=" +
//                } else {
//                    request = new Request.Builder().url(url).addHeader("cookie", UserRelated.sessionid).build();
//                }
            } else {
                request = new Request.Builder().url(url).get().build();
            }
//            Log.e("UserOrderFargment", "get: " + UserRelated.sessionid);
//            Log.e("123", "setdata: " + UserRelated.sessionid);
        } else {
            request = new Request.Builder().url(url).get().build();
        }
        call(request, callBack);
    }

    /**
     * 使用Post方式进行请求
     *
     * @param url      请求地址
     * @param params   请求的参数
     * @param callBack 请求的回调
     */
    public void post(String url, Map<String, String> params, final RequestCallBack callBack, boolean isAddCookie) {
        MediaType requestType
                = MediaType.parse("application/json; charset=utf-8");
        Request request = null;
        FormBody.Builder requestBodyPost = new FormBody.Builder();
        Iterator<String> iter = params.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = params.get(key);
            requestBodyPost.add(key, value);
        }
        request = new Request.Builder().url(url).post(requestBodyPost.build()).build();
        call(request, callBack);
    }

    /**
     * 使用Post方式进行请求
     *
     * @param url             请求地址
     * @param requestBodyPost 请求的参数
     * @param callBack        请求的回调
     */
    public void post(String url, RequestBody requestBodyPost, final RequestCallBack callBack, boolean isAddCookie) {
        MediaType requestType
                = MediaType.parse("application/json; charset=utf-8");
        Request request = null;

        if (isAddCookie) {
            request = new Request.Builder().url(url)
                    .addHeader("cookie", "PHPSESSID=" + UserData.USERSESSIONID).post(requestBodyPost).build();
        } else {
            request = new Request.Builder().url(url).post(requestBodyPost).build();
        }
        call(request, callBack);
    }

    public void post(String url, RequestBody requestBodyPost, final RequestCallBack callBack) {
        MediaType requestType
                = MediaType.parse("application/json; charset=utf-8");
        Request request = null;

        request = new Request.Builder().url(url).post(requestBodyPost).build();
//        }
        call(request, callBack);
    }

    public void post2(String url, Map<String, String> map, final RequestCallBack callBack) {
        MediaType requestType
                = MediaType.parse("application/json; charset=utf-8");
        Request request = null;
        FormBody.Builder requestBodyPost = new FormBody.Builder();
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = map.get(key);
            requestBodyPost.add(key, value);
        }
        request = new Request.Builder().url(url).post(requestBodyPost.build()).build();
//        }
        call(request, callBack);
    }

    public void post_json(String url, String jsonStr, final RequestCallBack callBack) {
        MediaType requestType
                = MediaType.parse("application/json; charset=utf-8");
        Request request = null;
        RequestBody body = RequestBody.create(requestType, jsonStr);
        request = new Request.Builder().url(url).post(body).build();
        call(request, callBack);
    }

    private void call(final Request request, final RequestCallBack callBack) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailMessage(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String result = response.body().string().trim();
                    sendSuccessMessage(result, callBack);
                } catch (Exception e) {
                    sendFailMessage(response.request(), e, callBack);
                }
            }
        });
    }

    private void sendFailMessage(final Request request, final Exception e, final RequestCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.fail(request, e);//回调
                }
            }
        });
    }

    private void sendSuccessMessage(final String result, final RequestCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.success(result);
                }
            }
        });
    }

    class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Response originResponse = chain.proceed(chain.request());
            //设置缓存时间为60秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
            return originResponse.newBuilder().removeHeader("pragma")
                    .header("Cache-Control", "max-age=60").removeHeader("Pragma").build();
        }
    }
}
