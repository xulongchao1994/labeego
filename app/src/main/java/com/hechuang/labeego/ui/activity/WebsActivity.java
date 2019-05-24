package com.hechuang.labeego.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.hechuang.labeego.R;
import com.hechuang.labeego.api.ApiFactify;
import com.hechuang.labeego.api.PathConstant;
import com.hechuang.labeego.base.BaseActivity;
import com.hechuang.labeego.data.UserData;
import com.hechuang.labeego.persenter.activity.WebPersenter;
import com.hechuang.labeego.tools.Util.AppManager;
import com.hechuang.labeego.tools.Util.MyToast;
import com.hechuang.labeego.tools.Util.Utils;
import com.hechuang.labeego.tools.ui.BasePopupWindow;
import com.hechuang.labeego.view.activity.IWebView;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 加载web页面
 * Created by Android_xu on 2018/2/3.
 */

public class WebsActivity extends BaseActivity<WebPersenter> implements IWebView, View.OnClickListener {
    @BindView(R.id.web_tencent)
    WebView mWebView;
    @BindView(R.id.web_layout)
    LinearLayout mLinearLayout;
    @BindView(R.id.web_refresh)
    Button mRefresh;
    @BindView(R.id.web_popup_xinashi)
    View mview;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private CookieManager cookieManager;
    private String url;
    private List<String> hostor_url;
    private SharedPreferences sp;
    public String browser = "";
    public String where = "";
    private String int_version = "";
    private String auto = "";
    private String picUrl;
    public static final String WEBSACTION = "activity.websactivity";

    @BindView(R.id.web_title_layout)
    RelativeLayout title;
    @BindView(R.id.web_title_text)
    TextView title_text;
    @BindView(R.id.web_back)
    ImageView back;

    @Override
    public void showloading() {
        mLoading.show();
    }

    private static final String TAG = "WebActivity";

    @Override
    protected int initlayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initPersenter() {
        setMPersenter(new WebPersenter(this, this));
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        ButterKnife.bind(WebsActivity.this);
        hostor_url = new ArrayList<>();
        url = getIntent().getStringExtra("web");
        where = getIntent().getStringExtra("url");
        browser = getIntent().getStringExtra("browser");
        auto = getIntent().getStringExtra("auto");
        int_version = getIntent().getStringExtra("version");
        if (url == null || url.equals("")) {
            url = ApiFactify.WEB_HOST;
        }
        if (url.contains("labeego")) {
            synCookies(WebsActivity.this, ApiFactify.HOST, "PHPSESSID=" + UserData.USERSESSIONID);
        } else {
            synCookies(WebsActivity.this, ApiFactify.WEB_HOST, "PHPSESSID=" + UserData.USERSESSIONID);
        }
        if (where == null || where.equals("")) {
        } else {
            int intversion = Integer.valueOf(int_version);
            if (intversion > Utils.getVersionCode(this)) {
                switch ((browser)) {
                    case "1":
                        boolean isgenxin;
                        isgenxin = auto.equals("1");
                        updateapp("", where, isgenxin);
                        break;
                    case "2":
                        new AlertDialog.Builder(this).setMessage("有新版本，是否更新").setPositiveButton("确定", (dialog, which) -> {
                            Uri uri = Uri.parse(where);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }).show();
                        break;
                }
            }
        }


        WebSettings webSettings = mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAllowFileAccess(true);//设置允许访问文件数据
//        webSettings.setSavePassword(false);
//        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBlockNetworkImage(false);
        webSettings.setLoadWithOverviewMode(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + "123";
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        // mWebView.getSettings().setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }

        mWebView.setWebViewClient(new MyWebviewClient());
        mWebView.setWebChromeClient(new MYWebChromeClient());
        mWebView.setDownloadListener(new MyWebViewDownLoadListener());
        mWebView.loadUrl(url);
        mWebView.addJavascriptInterface(new javascriptinterface(this), "android");
        mWebView.setOnLongClickListener(v -> {
            final WebView.HitTestResult hitTestResult = mWebView.getHitTestResult();
            if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                    hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                // 弹出保存图片的对话框
                picUrl = hitTestResult.getExtra();//获取图片链接
                if (picUrl.contains(ApiFactify.WEB_HOST)) {
                    showpopup(picUrl);
                    return true;
                }
            }
            return false;
        });
        back.setOnClickListener(this);
    }

    private BasePopupWindow mPopupWindow;

    private void showpopup(String picUrl) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_webs_popup, null);
        Button baocun = view.findViewById(R.id.webs_popup_baocun);
        Button fenxiang = view.findViewById(R.id.webs_popup_fenxiang);
        Button quxiao = view.findViewById(R.id.webs_popup_quxiao);
        RelativeLayout pop = view.findViewById(R.id.webs_popup_layout);
        pop.setOnClickListener(v -> {
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
        });

        baocun.setOnClickListener(v -> {
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
            if (Build.VERSION.SDK_INT >= 23) {
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(
                        WebsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                } else {
                    new Thread(() -> url2bitmap(picUrl)).start();
                }
            } else {
                new Thread(() -> url2bitmap(picUrl)).start();
            }
        });
        fenxiang.setOnClickListener(v -> {
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
            showShare(picUrl);
        });
        quxiao.setOnClickListener(v -> {
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new BasePopupWindow(this);
        mPopupWindow.setContentView(view);
        mPopupWindow.showAsDropDown(mview, Gravity.BOTTOM, 0, 0);
    }


    private void showShare(String picUrl) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle(getString(R.string.app_name));
        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
//        oks.setText("扫一扫，立即注册");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath(picUrl);//确保SDcard下面存在此张图片
        oks.setImageUrl(picUrl);
        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl(picUrl);
        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }

    public void url2bitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            if (bm != null) {
                save2Album(bm);
            }
        } catch (Exception e) {
            runOnUiThread(() -> Toast.makeText(WebsActivity.this, "保存失败", Toast.LENGTH_SHORT).show());
            e.printStackTrace();
        }
    }

    private void save2Album(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "相册名称");
        if (!appDir.exists()) appDir.mkdir();
        String[] str = picUrl.split("/");
        String fileName = str[str.length - 1];
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            onSaveSuccess(file);
        } catch (IOException e) {
            runOnUiThread(() -> Toast.makeText(WebsActivity.this, "保存失败", Toast.LENGTH_SHORT).show());
            e.printStackTrace();
        }
    }

    private void onSaveSuccess(final File file) {
        runOnUiThread(() -> {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            Toast.makeText(WebsActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateapp(String msg, String url, Boolean isqiangzhi) {
        if (msg.equals("")) {
            msg = "有新版本，是否更新？";
        }
//        Log.d(TAG, "updateapp: " + msg + "   " + url + "  " + isqiangzhi);
        UIData uiData = UIData.create().setTitle(getString(R.string.app_name)).setContent(msg).setDownloadUrl(url);
        DownloadBuilder bundle = AllenVersionChecker.getInstance().downloadOnly(uiData);
//        if (isqiangzhi) {
//            bundle.setForceUpdateListener(() -> MyToast.showMsg("此版本为强制更新"));
//        }
        bundle.setForceRedownload(true);
        bundle.setShowNotification(false);
//        bundle.setNotificationBuilder(NotificationBuilder.create().setRingtone(true)
//                .setIcon(R.mipmap.ic_launcher)
////                .setTicker("custom_ticker")
//                .setContentTitle(getString(R.string.app_name))
//                .setContentText(msg));
//        String finalMsg = msg;
//        bundle.setCustomVersionDialogListener((context, versionBundle) -> {
//            BaseDialog dialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_one_layout);
//            TextView textView = dialog.findViewById(R.id.tv_msg);
//            textView.setText(finalMsg);
//            //给窗体设置大小
//            Window win = dialog.getWindow();
//            win.getDecorView().setPadding(50, 0, 50, 0);
////            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
////            win.setAttributes(layoutParams);
//            WindowManager.LayoutParams lp = win.getAttributes();
//            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//            return dialog;
//        });
        //自定义进度条页面
//        bundle.customDownloadingDialogListener = object : CustomDownloadingDialogListener {
//            override fun updateUI(dialog: Dialog?, progress: Int, versionBundle: UIData?) {
//
//
//            }
//
//            override fun getCustomDownloadingDialog(context: Context?, progress: Int, versionBundle: UIData?): Dialog {
//            }
//        }
        bundle.excuteMission(this);
    }


    public static void getCookie(Context context, String url) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(context);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        String cookie = cookieManager.getCookie(url);
        Log.e(TAG, "getCookie: " + cookie);
    }

    /**
     * 将cookie同步到WebView
     * shangchuanceshi
     *
     * @param url WebView要加载的url
     * @return true 同步cookie成功，false同步cookie失败
     * @Author JPH
     */

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

    @Override
    public void dismissloading() {
        mLoading.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_back:
                keydown();
                break;
        }
    }


    public class javascriptinterface {
        private Context mContext;

        public javascriptinterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void outlogin() {
            sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("token_id", "");
            editor.putString("urserid", "");
            editor.putString("usertype", "");
            editor.putBoolean("islogin", false);
            UserData.USERISLOGIN = false;
            editor.commit();
            UserData.USERSESSIONID = "";
            UserData.USERTOKENID = "";
            UserData.USERTYPE = "";
            UserData.USER_ID = "";
            startActivity(new Intent(WebsActivity.this, LoginActivity.class));
            finish();
        }

        @JavascriptInterface
        public void back() {
            finish();
        }
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
//            Log.i("tag", "url=" + url);
//            Log.i("tag", "userAgent=" + userAgent);
//            Log.i("tag", "contentDisposition=" + contentDisposition);
//            Log.i("tag", "mimetype=" + mimetype);
//            Log.i("tag", "contentLength=" + contentLength);
            if (url.contains(".apk")) {
                updateapp("是否下载apk文件？", url, false);
            }
//            String payt = contentDisposition.substring(21);
//            imgurl = ApiFactify.WEB_HOST + "Upload/unshop/PayEwm/" + payt;
//            if (Build.VERSION.SDK_INT >= 23) {
//                int neicunquanxian = ContextCompat.checkSelfPermission(WebsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
//
//                if (neicunquanxian != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 321);
//                } else {
//                    ImageUtils.downloadimg(WebsActivity.this, imgurl);
//                }
//            } else {
//                ImageUtils.downloadimg(WebsActivity.this, imgurl);
//            }
//
        }
    }

    private class MyWebviewClient extends WebViewClient {
        String loadurl = "";

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
//            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView webView, String s) {
            Log.d("webs_shouidintercept_1", s);
//            if (s.contains("lafeng.hetianpay.com")
//                    || s.contains("lafeng.99xyg")
//                    || s.contains("at.alicdn.com")
//                    || s.contains("laf.out.999000")
//                    || s.contains("okcex")
//                    || s.contains("labeego")
//                    || s.contains("at.alicdn.com")
//                    || s.contains("openapi.alipay.com")
//                    || s.contains("wx.gtimg.com")
//                    || s.contains("wx.tenpay.com")
//                    || s.contains("hshc618")
//                    || s.contains("192.168.10.219")) {
//                return super.shouldInterceptRequest(webView, s);
//            } else {
//                return new WebResourceResponse(null, null, null);
//            }
            if (Utils.ishasurl(s)) {
                return super.shouldInterceptRequest(webView, s);
            } else {
                return new WebResourceResponse(null, null, null);
            }
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            Log.d("webs_shouidintercept_2", webResourceRequest.getUrl().getHost());
//            if (webResourceRequest.getUrl().getHost().contains("lafeng.hetianpay.com")
//                    || webResourceRequest.getUrl().getHost().contains("lafeng.99xyg")
//                    || webResourceRequest.getUrl().getHost().contains("laf.out.999000.cn")
//                    || webResourceRequest.getUrl().getHost().contains("at.alicdn.com")
//                    || webResourceRequest.getUrl().getHost().contains("okcex")
//                    || webResourceRequest.getUrl().getHost().contains("labeego")
//                    || webResourceRequest.getUrl().getHost().contains("at.alicdn.com")
//                    || webResourceRequest.getUrl().getHost().contains("openapi.alipay.com")
//                    || webResourceRequest.getUrl().getHost().contains("wx.gtimg.com")
//                    || webResourceRequest.getUrl().getHost().contains("wx.tenpay.com")
//                    || webResourceRequest.getUrl().getHost().contains("hshc618")
//                    || webResourceRequest.getUrl().getHost().contains("192.168.10.219")) {
//                return super.shouldInterceptRequest(webView, webResourceRequest);
//            } else {
//                return new WebResourceResponse(null, null, null);
//            }
            if (Utils.ishasurl(webResourceRequest.getUrl().getHost())) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            } else {
                return new WebResourceResponse(null, null, null);
            }
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest, Bundle bundle) {
            Log.d("webs_shouidintercept_3", webResourceRequest.getUrl().getHost());
//            if (webResourceRequest.getUrl().getHost().contains("lafeng.hetianpay.com")
//                    || webResourceRequest.getUrl().getHost().contains("lafeng.99xyg")
//                    || webResourceRequest.getUrl().getHost().contains("at.alicdn.com")
//                    || webResourceRequest.getUrl().getHost().contains("laf.out.999000.cn")
//                    || webResourceRequest.getUrl().getHost().contains("okcex")
//                    || webResourceRequest.getUrl().getHost().contains("labeego")
//                    || webResourceRequest.getUrl().getHost().contains("at.alicdn.com")
//                    || webResourceRequest.getUrl().getHost().contains("openapi.alipay.com")
//                    || webResourceRequest.getUrl().getHost().contains("wx.gtimg.com")
//                    || webResourceRequest.getUrl().getHost().contains("wx.tenpay.com")
//                    || webResourceRequest.getUrl().getHost().contains("hshc618")
//                    || webResourceRequest.getUrl().getHost().contains("192.168.10.219")
//                    ) {
//                return super.shouldInterceptRequest(webView, webResourceRequest, bundle);
//            } else {
//                return new WebResourceResponse(null, null, null);
//            }

            if (Utils.ishasurl(webResourceRequest.getUrl().getHost())) {
                return super.shouldInterceptRequest(webView, webResourceRequest, bundle);
            } else {
                return new WebResourceResponse(null, null, null);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            Log.d(TAG, "shouldOverrideUrlLoading: " + s + "   " + loadurl);
            if (s.contains(".apk")) {
                return false;
            }
            if (s.startsWith("http:") || s.startsWith("https:")) {
                return false;
            }
            if (s.startsWith("weixin:") || s.startsWith("tel:") || s.contains("alipay")
                    ) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                startActivity(intent);
                return true;
            }
//            else {
//                if (s.contains("hshc618") && s.contains("alipay")) {
//                    Log.d(TAG, "shouldOverrideUrlLoading: " +"hshc618:-----" + s);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
//                    startActivity(intent);
//                    return true;
//                } else {
//                    Log.d(TAG, "shouldOverrideUrlLoading: " +"alipay:++++++++" + s);
//                    final PayTask task = new PayTask(WebsActivity.this);
//                    boolean isIntercepted = task.payInterceptorWithUrl(s, true, result -> {
//                        // 支付结果返回
//                        final String url = result.getReturnUrl();
//                        Log.d(TAG, "shouldOverrideUrlLoading: " +"alipay:-----" + url);
//                        if (!TextUtils.isEmpty(url)) {
//                            WebsActivity.this.runOnUiThread(() ->
//                                    webView.loadUrl(s));
//                        }
//                    });
//                    /**
//                     * 判断是否成功拦截
//                     * 若成功拦截，则无需继续加载该URL；否则继续加载
//                     */
//                    if (!isIntercepted) {
//                        return false;
//                    }
//                }
//            }


            webView.loadUrl(s);
            return true;
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            Log.d(TAG, "开始加载：" + s);
            showloading();
            mLinearLayout.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
            if (!s.contains("alipays") && !s.contains("openapi.alipay") && !s.contains("mclient.alipay") && !s.contains("wx.tenpay") && !s.contains("wxpay_out")
                    && !s.contains("http://laf.out.999000.cn/web/home.php/Test/order?")
                    && !s.contains("http://laf.out.999000.cn/web/home.php/Classify/Generate_order?"
            ) && !s.contains("http://laf.out.999000.cn/web/home.php/Pay/goods_pay?")
                    ) {
                hostor_url.add(s);
            }
            if (hostor_url.size() > 1 && hostor_url.get(hostor_url.size() - 1).equals(hostor_url.get(hostor_url.size() - 2))) {//这个判断是用来判断是否连续加载两个不同的链接
                hostor_url.remove(hostor_url.size() - 1);
            }
            if (s.equals(ApiFactify.WEB_HOST + "index.php/Home/FreeLogin/login") || s.equals(ApiFactify.HOST + "index.php/Home/FreeLogin/login")) {
                if (UserData.USERISLOGIN) {
                    sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token_id", "");
                    editor.putString("urserid", "");
                    editor.putString("usertype", "");
                    editor.putBoolean("islogin", false);
                    UserData.USERISLOGIN = false;
                    editor.commit();
                    UserData.USERSESSIONID = "";
                    UserData.USERTOKENID = "";
                    UserData.USERNAME = "";
                    UserData.USERTYPE = "";
                    UserData.USER_ID = "";
                }
                startActivity(new Intent(WebsActivity.this, LoginActivity.class));
                finish();
            }
            if (s.equals(ApiFactify.WEB_HOST + "index.php/Home/FreeLogin/login.html") || s.equals(ApiFactify.HOST + "index.php/Home/FreeLogin/login.html")) {
                if (UserData.USERISLOGIN) {
                    sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token_id", "");
                    editor.putString("urserid", "");
                    editor.putString("usertype", "");
                    editor.putBoolean("islogin", false);
                    UserData.USERISLOGIN = false;
                    editor.commit();
                    UserData.USERSESSIONID = "";
                    UserData.USERTOKENID = "";
                    UserData.USERNAME = "";
                    UserData.USERTYPE = "";
                    UserData.USER_ID = "";
                }
                startActivity(new Intent(WebsActivity.this, LoginActivity.class));
                finish();
            }

            if (s.equals(ApiFactify.WEB_HOST) || s.equals(ApiFactify.HOST)) {//首页
                hostor_url.clear();
                hostor_url.add(s);
            }
            if (s.equals(PathConstant.MY_HOENY) || s.equals(ApiFactify.HOST + "index.php/Home/User/labeeamount")) {
                hostor_url.clear();
                finish();
            }
            if (s.equals(PathConstant.MY_HOENY_2) || s.equals(ApiFactify.HOST + "index.php/Home/User/apiserum")) {
                hostor_url.clear();
                finish();
            }
            if (s.equals(ApiFactify.WEB_HOST + "index.php/Home/Index") || s.equals(ApiFactify.HOST + "index.php/Home/Index")) {//首页
                hostor_url.clear();
                hostor_url.add(s);
            }
//            if (s.contains("http://laflinux.test.999000.cn/web/home.php/Login/rlblogin/user_name/")) {
//                hostor_url.remove(hostor_url.size() - 1);
//            }
            if (s.contains("http://laf.out.999000.cn//web/home.php/Login/rlblogin/user_name")) {
                hostor_url.remove(hostor_url.size() - 1);
            }
//            if (s.equals(ApiFactify.HOST + "/")) {
//                hostor_url.clear();
//                hostor_url.add(s);
//            }
            if (s.equals((ApiFactify.WEB_HOST + "index.php/Home/Product/index")) || s.equals((ApiFactify.HOST + "index.php/Home/Product/index"))) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(ApiFactify.HOST);
                Intent intent = new Intent(WEBSACTION);
                intent.putExtra("weizhi", 1);
                sendBroadcast(intent);
                Intent intent1 = new Intent(WebsActivity.this, LabeegoActivity.class);
                intent1.putExtra("weizhi", 1);
                startActivity(intent1);
                finish();
            }
            if (s.equals((ApiFactify.WEB_HOST + "index.php/Home/User/index")) || s.equals((ApiFactify.HOST + "index.php/Home/User/index"))) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
                if (UserData.USERISLOGIN) {
                    Intent intent = new Intent(WEBSACTION);
                    intent.putExtra("weizhi", 2);
                    sendBroadcast(intent);
                    Intent intent1 = new Intent(WebsActivity.this, LabeegoActivity.class);
                    intent1.putExtra("weizhi", 2);
                    startActivity(intent1);
                } else {
                    startActivity(new Intent(WebsActivity.this, LoginActivity.class));
                }
                finish();
            }
            if (s.equals((ApiFactify.WEB_HOST + "index.php/Home/Product")) || s.equals((ApiFactify.HOST + "index.php/Home/Product"))) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
                Intent intent = new Intent(WEBSACTION);
                intent.putExtra("weizhi", 1);
                sendBroadcast(intent);
                Intent intent1 = new Intent(WebsActivity.this, LabeegoActivity.class);
                intent1.putExtra("weizhi", 1);
                startActivity(intent1);
                finish();
            }
            //应用商家
            if (s.equals((ApiFactify.WEB_HOST + "index.php/Home/Unproduct")) || s.equals((ApiFactify.HOST + "index.php/Home/Unproduct"))) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
                Intent intent = new Intent(WEBSACTION);
                intent.putExtra("weizhi", 3);
                sendBroadcast(intent);
                Intent intent1 = new Intent(WebsActivity.this, LabeegoActivity.class);
                intent1.putExtra("weizhi", 3);
                startActivity(intent1);
                finish();
            }

            if (s.equals((ApiFactify.WEB_HOST + "index.php/Home/Product/Index")) || s.equals((ApiFactify.HOST + "index.php/Home/Product/Index"))) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
                Intent intent = new Intent(WEBSACTION);
                intent.putExtra("weizhi", 1);
                sendBroadcast(intent);
                Intent intent1 = new Intent(WebsActivity.this, LabeegoActivity.class);
                intent1.putExtra("weizhi", 1);
                startActivity(intent1);
                finish();
            }
            if (s.equals((ApiFactify.WEB_HOST + "index.php/Home/Product/product_list.html")) || s.equals((ApiFactify.HOST + "index.php/Home/Product/product_list.html"))) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
                Intent intent = new Intent(WEBSACTION);
                intent.putExtra("weizhi", 1);
                sendBroadcast(intent);
                Intent intent1 = new Intent(WebsActivity.this, LabeegoActivity.class);
                intent1.putExtra("weizhi", 1);
                startActivity(intent1);
                finish();
            }
            if (s.equals((ApiFactify.WEB_HOST + "index.php/Home/Order/index")) || s.equals((ApiFactify.HOST + "index.php/Home/Order/index"))) {//订单
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                Intent intent = new Intent(WebsActivity.this, OrderActivity.class);
                intent.putExtra("ordertype", "全部");
                startActivity(intent);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
            }
            if (s.equals((PathConstant.ORDER_DAIFUKUAN)) || s.equals(ApiFactify.HOST + "index.php/Home/Order/index/Tradingclass/1")) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                Intent intent = new Intent(WebsActivity.this, OrderActivity.class);
                intent.putExtra("ordertype", "待付款");
                startActivity(intent);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
            }
            if (s.equals((PathConstant.ORDER_DAISHOUHUO)) || s.equals(ApiFactify.HOST + "index.php/Home/Order/index/Tradingclass/3")) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                Intent intent = new Intent(WebsActivity.this, OrderActivity.class);
                intent.putExtra("ordertype", "待收货");
                startActivity(intent);

                mWebView.loadUrl(ApiFactify.WEB_HOST);
            }
            if (s.equals((PathConstant.ORDER_YIWANCHENG)) || s.equals(ApiFactify.HOST + "index.php/Home/Order/index/Tradingclass/4")) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                Intent intent = new Intent(WebsActivity.this, OrderActivity.class);
                intent.putExtra("ordertype", "已完成");
                startActivity(intent);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
//                finish();
            }
//
            if (s.indexOf("index.php/Home/Unproduct/merchant/supid") > 0) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                String mob = "supid/";
                int mob_l = mob.length();
                String goodsId_sor = s.substring(s.indexOf(mob) + mob_l);
                Log.d(TAG, goodsId_sor);
                Intent intent = new Intent(WebsActivity.this, ShopInfoActivity.class);
                intent.putExtra("id", goodsId_sor);
                startActivity(intent);
            }

            if (s.indexOf("index.php/Home/Unproduct/product_details/proid") > 0) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                String mob = "proid/";
                int mob_l = mob.length();
                String goodsId_sor = s.substring(s.indexOf(mob) + mob_l);
                Log.d(TAG, goodsId_sor);
                Intent intent = new Intent(WebsActivity.this, ShopGoodsInfoActivity.class);
                intent.putExtra("pid", goodsId_sor);
                intent.putExtra("proimg", "");
                startActivity(intent);
                finish();
            }
            if (s.equals((PathConstant.ORDER_ZHONGCHOU)) || s.equals(ApiFactify.HOST + "index.php/Home/Order/index/Tradingclass/5")) {
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                Intent intent = new Intent(WebsActivity.this, OrderActivity.class);
                intent.putExtra("ordertype", "众筹");
                startActivity(intent);
                mWebView.loadUrl(ApiFactify.WEB_HOST);
//                finish();
            }
            if (s.equals((ApiFactify.WEB_HOST + "alipay/wappay/pay.php")) || s.equals((ApiFactify.HOST + "alipay/wappay/pay.php"))) {
                hostor_url.remove(hostor_url.size() - 1);
//                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
            }
            if (s.contains((ApiFactify.WEB_HOST + "index.php/Home/UnCart/confirm_order/action/buyNow/data/")) || s.contains((ApiFactify.HOST + "index.php/Home/UnCart/confirm_order/action/buyNow/data/"))) {
                hostor_url.remove(hostor_url.size() - 1);
//                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
            }
            if (s.contains("https://mclient.alipay.com/cashier/mobilepay.htm?alipay_exterface_invoke_assign_target")) {
                hostor_url.remove(hostor_url.size() - 1);
//                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
            }
            if (s.contains("/index.php/Home/Wxinpay")) {
                hostor_url.remove(hostor_url.size() - 1);
            }
            if (s.contains("index.php/Home/Wxinpay/user_cptwpay?action=ok")) {
                hostor_url.remove(hostor_url.size() - 1);
            }
            if (s.contains(".apk")) {
                updateapp("是否下载apk文件？", s, false);
                hostor_url.remove(hostor_url.size() - 1);
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
            }
            Log.d(TAG, hostor_url.toString());
        }

//        @Override
//        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//            super.onReceivedSslError(webView, sslErrorHandler, sslError);
//            sslErrorHandler.proceed();//接收证书
//            mWebView.getSettings().setJavaScriptEnabled(true);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                mWebView.getSettings()
//                        .setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
//            }

//        }
        //        @Override
//        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
//            super.onReceivedError(webView, webResourceRequest, webResourceError);
//            mLinearLayout.setVisibility(View.VISIBLE);
//            mWebView.setVisibility(View.GONE);
//            if (hostor_url != null && hostor_url.size() > 0) {
//                hostor_url.remove(hostor_url.size() - 1);
//            }
//        }

//        @Override
//        public void onReceivedError(WebView webView, int i, String s, String s1) {
//            super.onReceivedError(webView, i, s, s1);
//            mLinearLayout.setVisibility(View.VISIBLE);
//            mWebView.setVisibility(View.GONE);
//            if (hostor_url != null && hostor_url.size() > 0) {
//                hostor_url.remove(hostor_url.size() - 1);
//            }
//        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            if (mLoading != null && mLoading.isShowing())
                mLoading.dismiss();
            if (!mWebView.getSettings().getLoadsImagesAutomatically()) {
                mWebView.getSettings().setLoadsImagesAutomatically(true);
            }
//            if (s.equals(PathConstant.app_host + "index.php/Home/FreeLogin/login.html")) {
//                mWebView.loadUrl(PathConstant.app_host);
//            }
//            if (s.equals(PathConstant.app_host + "index.php/Home/FreeLogin/login")) {
//                mWebView.loadUrl(PathConstant.app_host);
//            }
//            getCookie(WebsActivity.this, webView.getUrl());
            super.onPageFinished(webView, s);
        }
    }

    private class MYWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
            Log.d(TAG, "onReceivedTitle: " + s);
//            if (s.equals("啦蜂 - 新零售生态系统")) {
//                title.setVisibility(View.GONE);
//            } else {
//                title.setVisibility(View.VISIBLE);
//            }
//            title_text.setText(s);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }


        @Override
        public boolean onJsConfirm(WebView view, String urls, String message, final JsResult result) {
            new AlertDialog.Builder(WebsActivity.this)
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
        public boolean onJsBeforeUnload(WebView view, String urls, String message, final JsResult result) {
            new AlertDialog.Builder(WebsActivity.this)
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

        @Override
        public boolean onJsAlert(final WebView view, final String urls, final String message, final JsResult result) {
            new AlertDialog.Builder(WebsActivity.this)
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
//            }
//            }
            return true;
        }

        // 3.0++
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
//            mUploadFile = uploadMsg;
            doPickPhotoFromGallery();
        }

        //3.0--
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//            mUploadFile = uploadMsg;
            doPickPhotoFromGallery();
        }

        //4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }


        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue(null);
            }
            mUploadCallbackAboveL = valueCallback;
            doPickPhotoFromGallery();
            return true;
        }

    }

    private final int REQUESTCODE_PICK = 2;

    protected void doPickPhotoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUESTCODE_PICK);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return keydown();
        }
        return super.onKeyDown(keyCode, event);
    }

    private Boolean keydown() {
        if (hostor_url.size() > 0) {
            hostor_url.remove(hostor_url.size() - 1);
            if (hostor_url.size() > 0) {
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
            } else {
                if (mWebView.getUrl().equals(ApiFactify.WEB_HOST + "index.php/Home/Product/product_list.html")) {
                    mWebView.loadUrl(ApiFactify.WEB_HOST);
                } else {
                    if (mWebView.getUrl().equals(ApiFactify.WEB_HOST) ||
                            mWebView.getUrl().equals(ApiFactify.WEB_HOST + "index.php/Home/Index") ||
                            mWebView.getUrl().equals(ApiFactify.WEB_HOST + "index.php/Home/FreeLogin/info")
                            ) {
                        outlogin();
                    } else {
                        finish();
                    }
                }
            }
        } else

        {
            if (mWebView.getUrl().equals(ApiFactify.WEB_HOST) ||
                    mWebView.getUrl().equals(ApiFactify.WEB_HOST + "index.php/Home/Index") ||
                    mWebView.getUrl().equals(ApiFactify.WEB_HOST + "index.php/Home/FreeLogin/info")) {
                outlogin();
            } else {
                finish();
            }
        }
        return true;
    }

    public static WebsActivity ewedfasf;
    private long exitTime = 0;
    AlertDialog adia;

    private void outlogin() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            //弹出提示，可以有多种方式
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.view_out, null);
            Button sure = view.findViewById(R.id.out_title_queding);
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppManager.getAppManager().AppExit(WebsActivity.this);
                    adia.dismiss();
                }
            });
            Button quxiao = view.findViewById(R.id.out_title_quxiao);
            quxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adia.dismiss();
                }
            });
            adia = new AlertDialog.Builder(this)
                    .setView(view).show();
            Utils.setdialotwidth(this, adia);
        }

    }

    @Override
    protected void onDestroy() {
//        if (mWebView != null)
//            mWebView.destroy();
        if (mLoading != null && mLoading.isShowing())
            mLoading.dismiss();


        super.onDestroy();
    }


    //    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            for (String per : permissions) {
                switch (per) {
                    case "android.permission.READ_EXTERNAL_STORAGE":
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            new Thread(() -> url2bitmap(picUrl)).start();
                        } else {
                            MyToast.showMsg("读写权限已禁止，部分功能暂时无法使用，建议在权限管理中打开");
                        }
                        break;
                    case "android.permission.WRITE_EXTERNAL_STORAGE":
                        if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                            new Thread(() -> url2bitmap(picUrl)).start();
                        } else {
                            MyToast.showMsg("读写权限已禁止，部分功能暂时无法使用，建议在权限管理中打开");
                        }
                        break;
                }
            }
        }
    }

    private Boolean isintent = false;

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onnewintent");
        super.onNewIntent(intent);
        if (hostor_url != null && hostor_url.size() > 0) {
            hostor_url.clear();
        }
        url = intent.getStringExtra("web");
        if (url == null) {
            url = "";
            isintent = false;
        } else {
            isintent = true;
            if (url.equals("")) url = ApiFactify.WEB_HOST;
            mWebView.loadUrl(url);
//            syncCookie(WebsActivity.this, ApiFactify.HOST, "PHPSESSID=" + UserData.USERSESSIONID);
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        if (isintent) {
            isintent = false;
        } else {
//            syncCookie(WebsActivity.this, ApiFactify.HOST, "PHPSESSID=" + UserData.USERSESSIONID);
            if (hostor_url != null && hostor_url.size() > 0) {
                mWebView.loadUrl(hostor_url.get(hostor_url.size() - 1));
                if (hostor_url.size() > 1)
                    hostor_url.remove(hostor_url.get(hostor_url.size() - 1));
//                syncCookie(WebsActivity.this, ApiFactify.HOST, "PHPSESSID=" + UserData.USERSESSIONID);
            }
        }
    }
}
