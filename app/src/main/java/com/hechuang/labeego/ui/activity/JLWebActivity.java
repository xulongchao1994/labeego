package com.hechuang.labeego.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alipay.sdk.app.PayTask;
import com.hechuang.labeego.R;
import com.hechuang.labeego.api.ApiFactify;
import com.hechuang.labeego.api.PathConstant;
import com.hechuang.labeego.data.UserData;
import com.hechuang.labeego.tools.Util.Utils;
import com.hechuang.labeego.tools.ui.LoadingDialog;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载web页面
 * Created by Android_xu on 2018/2/3.
 */

public class JLWebActivity extends AppCompatActivity implements View.OnClickListener {
    WebView mWebView;
    private String url;
    private List<String> hostor_url;
    private SharedPreferences sp;
    private static final String TAG = "jlweb";
    private LoadingDialog mLoading;
    private RelativeLayout title_layout;
    private ImageView back;
    public static final String JLWEBSACTION = "activity.websactivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fandweb);
        mWebView = findViewById(R.id.web_findtencent);
        title_layout = findViewById(R.id.scanweb_title_layout);
        back = findViewById(R.id.scanweb_back);
        back.setOnClickListener(this);
        mLoading = new LoadingDialog(this);
        mLoading.setCanceledOnTouchOutside(false);
        initView();
    }

    public static void synCookies(Context context, String url, String cookieValue) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(context);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        cookieManager.removeSessionCookie();
        cookieManager.setCookie(url, cookieValue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
        String cookie = cookieManager.getCookie(url);
    }

    protected void initView() {
        url = getIntent().getStringExtra("web");
        hostor_url = new ArrayList<>();
        if (!url.contains("_if")) {
            synCookies(JLWebActivity.this, ApiFactify.HOST, "PHPSESSID=" + UserData.USERSESSIONID);
        }
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAllowFileAccess(true);//设置允许访问文件数据
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(JLWebActivity.this)
                        .setTitle(getResources().getString(R.string.app_name))
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                (dialog, which) -> {
                                    result.confirm();
                                }).setCancelable(false)
                        .setOnKeyListener((dialogInterface, i, keyEvent) -> {
                            if (i == KeyEvent.KEYCODE_SEARCH) {
                                return true;
                            } else {
                                return false; //默认返回 false
                            }
//                        }
                        })
                        .create()
                        .show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(JLWebActivity.this)
                        .setTitle(getResources().getString(R.string.app_name))
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                (dialog, which) -> {
                                    result.confirm();
                                }).setCancelable(false)
                        .setOnKeyListener((dialogInterface, i, keyevent) -> {
                            if (i == KeyEvent.KEYCODE_SEARCH) {
                                return true;
                            } else {
                                return false; //默认返回 false
                            }
                        })
                        .create()
                        .show();
                return true;
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(JLWebActivity.this)
                        .setTitle(getResources().getString(R.string.app_name))
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                (dialog, which) -> {
                                    result.confirm();
//                                }
                                }).setCancelable(false)
                        .setOnKeyListener((dialogInterface, i, kytEvent) -> {
                            if (i == KeyEvent.KEYCODE_SEARCH) {
                                return true;
                            } else {
                                return false; //默认返回 false
                            }
                        })
                        .create()
                        .show();
                return true;
            }


        });
        mWebView.setWebViewClient(new WebViewClient() {
            String loadurl = "";

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, String s) {
//                Log.d(TAG, "shouldInterceptRequest_1: " + s);
//                if (s.contains("lafeng.99xyg.com") || s.contains("at.alicdn.com")
//                        ||
//                        s.contains("weixin") ||
//                        s.contains("tenpay") ||
//                        s.contains("lafeng") ||
//                        s.contains("alipay") ||
//                        s.contains("999000")
//                        ) {
//                    return super.shouldInterceptRequest(webView, s);
//                } else {
//                    return new WebResourceResponse(null, null, null);
//                }
                if (Utils.ishasurl(s)) {
                    return super.shouldInterceptRequest(webView, s);
                } else {
                    return new WebResourceResponse(null, null, null);
                }
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {

//                Log.d(TAG, "shouldInterceptRequest_2: " + webResourceRequest.getUrl().getHost());
//                if (webResourceRequest.getUrl().getHost().contains("lafeng.99xyg") ||
//                        webResourceRequest.getUrl().getHost().contains("at.alicdn.com")
//                        ||
//                        webResourceRequest.getUrl().getHost().contains("weixin") ||
//                        webResourceRequest.getUrl().getHost().contains("tenpay") ||
//                        webResourceRequest.getUrl().getHost().contains("lafeng") ||
//                        webResourceRequest.getUrl().getHost().contains("alipay") ||
//                        webResourceRequest.getUrl().getHost().contains("999000")
//                        ) {
//                    return super.shouldInterceptRequest(webView, webResourceRequest);
//                } else {
//                    return new WebResourceResponse(null, null, null);
//                }
                if (Utils.ishasurl(webResourceRequest.getUrl().getHost())) {
                    return super.shouldInterceptRequest(webView, webResourceRequest);
                } else {
                    return new WebResourceResponse(null, null, null);
                }

            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest, Bundle bundle) {
//                Log.d(TAG, "shouldInterceptRequest_3: " + webResourceRequest.getUrl().getHost());
//                if (webResourceRequest.getUrl().getHost().contains("lafeng.99xyg") ||
//                        webResourceRequest.getUrl().getHost().contains("at.alicdn.com") ||
//                        webResourceRequest.getUrl().getHost().contains("weixin") ||
//                        webResourceRequest.getUrl().getHost().contains("tenpay") ||
//                        webResourceRequest.getUrl().getHost().contains("lafeng") ||
//                        webResourceRequest.getUrl().getHost().contains("alipay") ||
//                        webResourceRequest.getUrl().getHost().contains("999000")
//                        ) {
//                    return super.shouldInterceptRequest(webView, webResourceRequest, bundle);
//                } else {
//                    return new WebResourceResponse(null, null, null);
//                }


                if (Utils.ishasurl(webResourceRequest.getUrl().getHost())) {
                    return super.shouldInterceptRequest(webView, webResourceRequest, bundle);
                } else {
                    return new WebResourceResponse(null, null, null);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String s) {
                if (loadurl.equals(s)) {
                    return false;
                } else {
                    loadurl = s;
//                    view.loadUrl(s);
//                    return true;
                }

                if (s.contains("weixin://")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                    startActivity(intent);
                    return true;
                } else if (s.contains("https://wx.tenpay.com")) {
                    return false;
                }
//                else if (s.contains("https://mclient.alipay.com") || s.equals(" https://mclient.alipay.com")) {
//                    return false;
//                } else if (s.contains("alipays://")) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
//                    startActivity(intent);
//                    return true;
//                }
                else {
                    final PayTask task = new PayTask(JLWebActivity.this);
                    boolean isIntercepted = task.payInterceptorWithUrl(s, true, result -> {
                        // 支付结果返回
                        final String url = result.getReturnUrl();
                        if (!TextUtils.isEmpty(url)) {
                            JLWebActivity.this.runOnUiThread(() ->
                                    view.loadUrl(s));
                        }
                    });
                    /**
                     * 判断是否成功拦截
                     * 若成功拦截，则无需继续加载该URL；否则继续加载
                     */
                    if (!isIntercepted) {
                        return false;
                    }
                }

                if ((s.startsWith("http") || s.startsWith("https"))) {
                    return false;
                }
//                view.loadUrl(s);
                return true;


            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();

            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                Log.d(TAG, "onPageStarted:" + s);
                if (mLoading != null) {
                    mLoading.show();
                }
                if (!s.contains("alipays") && !s.contains("alipay") && !s.contains("wx.tenpay") && !s.contains("wxpay_out")
                        && !s.contains("http://laf.out.999000.cn/web/home.php/Test/order?")
                        && !s.contains("http://laf.out.999000.cn/web/home.php/Classify/Generate_order?"
                ) && !s.contains("http://laf.out.999000.cn/web/home.php/Pay/goods_pay?")
                        ) {
                    hostor_url.add(s);
                }
//                if (!s.contains("alipays") && !s.contains("alipay") && !s.contains("wx.tenpay") && !s.contains("wxpay_out")
//                        && !s.contains("http://laflinux.test.999000.cn/web/home.php/Test/order?")
//                        && !s.contains("http://laflinux.test.999000.cn/web/home.php/Classify/Generate_order?"
//                ) && !s.contains("http://laflinux.test.999000.cn/web/home.php/Pay/goods_pay?")
//                        ) {
//                    hostor_url.add(s);
//                }
                if (hostor_url.size() > 1 && hostor_url.get(hostor_url.size() - 1).equals(hostor_url.get(hostor_url.size() - 2))) {//这个判断是用来判断是否连续加载两个不同的链接
                    hostor_url.remove(hostor_url.size() - 1);
                }
                if (s.equals("http://laf.out.999000.cn/web/home.php/Test/selfhome")) {
                    hostor_url.clear();
                    hostor_url.add(s);
                    title_layout.setVisibility(View.VISIBLE);
                } else {
                    title_layout.setVisibility(View.GONE);
                }
//                if (s.equals("http://laflinux.test.999000.cn/web/home.php/Test/selfhome")) {
//                    hostor_url.clear();
//                    hostor_url.add(s);
//                    title_layout.setVisibility(View.VISIBLE);
//                } else {
//                    title_layout.setVisibility(View.GONE);
//                }
                if (s.equals((ApiFactify.HOST + "index.php/Home/Product/index"))) {
                    hostor_url.remove(hostor_url.size() - 1);
                    mWebView.loadUrl(ApiFactify.HOST);
                    Intent intent = new Intent(JLWEBSACTION);
                    intent.putExtra("weizhi", 1);
                    sendBroadcast(intent);
                    Intent intent1 = new Intent(JLWebActivity.this, LabeegoActivity.class);
                    intent1.putExtra("weizhi", 1);
                    startActivity(intent1);
                    finish();
                }
                if (s.equals("http://www.labeego.com/index.php/Home/FreeLogin/login")) {
                    startActivity(new Intent(JLWebActivity.this, LoginActivity.class));
                    finish();
                }
                if (s.equals("http://www.labeego.com/index.php/Home/FreeLogin/login.html")) {
                    startActivity(new Intent(JLWebActivity.this, LoginActivity.class));
                    finish();
                }
                if (s.equals(ApiFactify.HOST + "/index.php/Home/FreeLogin/login")) {
                    startActivity(new Intent(JLWebActivity.this, LoginActivity.class));
                    finish();
                }
                if (s.equals(ApiFactify.HOST + "/index.php/Home/FreeLogin/login.html")) {
                    startActivity(new Intent(JLWebActivity.this, LoginActivity.class));
                    finish();
                }
                if (s.contains(ApiFactify.HOST + "/index.php/Home/Cart/")) {
                    hostor_url.remove(hostor_url.size() - 1);
                }
                if (s.equals((ApiFactify.HOST + "index.php/Home/Order/index"))) {//订单
                    hostor_url.remove(hostor_url.size() - 1);
                    mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                    Intent intent = new Intent(JLWebActivity.this, OrderActivity.class);
                    intent.putExtra("ordertype", "全部");
                    startActivity(intent);
                    finish();
                }
                if (s.equals((PathConstant.ORDER_DAIFUKUAN))) {
                    hostor_url.remove(hostor_url.size() - 1);
                    mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                    Intent intent = new Intent(JLWebActivity.this, OrderActivity.class);
                    intent.putExtra("ordertype", "待付款");
                    startActivity(intent);
                    finish();
                }
                if (s.equals((PathConstant.ORDER_DAISHOUHUO))) {
                    hostor_url.remove(hostor_url.size() - 1);
                    mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                    Intent intent = new Intent(JLWebActivity.this, OrderActivity.class);
                    intent.putExtra("ordertype", "待收货");
                    startActivity(intent);
                    finish();
                }
                if (s.equals((PathConstant.ORDER_YIWANCHENG))) {
                    hostor_url.remove(hostor_url.size() - 1);
                    mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                    Intent intent = new Intent(JLWebActivity.this, OrderActivity.class);
                    intent.putExtra("ordertype", "已完成");
                    startActivity(intent);
                    finish();
//                finish();
                }
                if (s.equals("http://www.labeego.com/index.php/Home/Unproduct")) {
                    hostor_url.remove(hostor_url.size() - 1);
                    finish();
                }
                if (s.contains("/web/home.php/Login/rlblogin/user_name")) {
                    hostor_url.remove(hostor_url.size() - 1);
                }
//
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mLoading != null && mLoading.isShowing()) {
                    mLoading.dismiss();
                }

//                for (int i = 0; i < mWebView.copyBackForwardList().getSize(); i++) {
//                    Log.d(TAG, "onPageFinished: " + mWebView.copyBackForwardList().getItemAtIndex(i).getUrl());
//                }
                if (url.contains("http://laf.out.999000.cn/web/home.php/Order/index?")) {
                    view.clearHistory();
                }

            }

            @Override
            public void doUpdateVisitedHistory(WebView webView, String s, boolean b) {
                if (url.contains("http://laf.out.999000.cn/web/home.php/Order/index?")) {
                    webView.clearHistory();
                }
//                Log.d(TAG, "doUpdateVisitedHistory: " + s);
            }

        });
        mWebView.loadUrl(url);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (hostor_url.size() > 0) {
                if (hostor_url.get(hostor_url.size() - 1).equals("http://laf.out.999000.cn/web/home.php/Login/index")
                        || hostor_url.get(hostor_url.size() - 1).equals("http://laf.out.999000.cn/web/home.php/Cart/cart_empty")) {
                    mWebView.loadUrl("http://laf.out.999000.cn/web/home.php/Test/selfhome");
                } else {
                    hostor_url.remove(hostor_url.size() - 1);
                    if (hostor_url.size() > 0) {
                        mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                    } else {
                        finish();
                    }
                }
            } else {
                finish();
            }
//            Log.d(TAG, "返回键" + mWebView.getUrl());
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoading != null) {
            mLoading = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanweb_back:
                if (hostor_url.size() > 0) {
                    if (hostor_url.get(hostor_url.size() - 1).equals("http://laf.out.999000.cn/web/home.php/Login/index")
                            || hostor_url.get(hostor_url.size() - 1).equals("http://laf.out.999000.cn/web/home.php/Cart/cart_empty")) {
                        mWebView.loadUrl("http://laf.out.999000.cn/web/home.php/Test/selfhome");
                    } else {
                        hostor_url.remove(hostor_url.size() - 1);
                        if (hostor_url.size() > 0) {
                            mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                        } else {
                            finish();
                        }
                    }
                } else {
                    finish();
                }
                break;
        }
    }
}
