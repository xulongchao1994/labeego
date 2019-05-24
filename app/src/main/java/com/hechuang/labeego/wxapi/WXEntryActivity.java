package com.hechuang.labeego.wxapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hechuang.labeego.R;
import com.hechuang.labeego.api.ApiFactify;
import com.hechuang.labeego.api.MyOkHttp;
import com.hechuang.labeego.bean.LoginBean;
import com.hechuang.labeego.data.UserData;
import com.hechuang.labeego.tools.Util.MyToast;
import com.hechuang.labeego.ui.activity.BindingPhonenumberActivity;
import com.hechuang.labeego.ui.activity.LabeegoActivity;
import com.hechuang.labeego.ui.activity.LoginActivity;
import com.hechuang.labeego.ui.activity.WebsActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI wxAPI;
    private Button newuser;
    private Button olduser;
    private String unionid;
    private ImageView weixin_icon;
    private LinearLayout weixin_tiaozhuan_layout;
    protected ProgressDialog mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wxentry);
        Log.d(TAG, "onCreate: ");
        mLoading = new ProgressDialog(this);
        mLoading.setMessage("正在加载...");
        mLoading.setCanceledOnTouchOutside(false);
        mLoading.show();
        if (UserData.isfenxiang) {
            mLoading.dismiss();
            UserData.isfenxiang = false;
            finish();
        }
        wxAPI = WXAPIFactory.createWXAPI(this, UserData.wxappid, true);
        wxAPI.registerApp(UserData.wxappid);
        wxAPI.handleIntent(getIntent(), this);
        newuser = findViewById(R.id.wxapi_newuser);
        olduser = findViewById(R.id.wxapi_olduser);
        weixin_icon = findViewById(R.id.weixin_logo);
        weixin_tiaozhuan_layout = findViewById(R.id.weixin_tiaozhuan_layout);
        weixin_icon.setVisibility(View.GONE);
        weixin_tiaozhuan_layout.setVisibility(View.GONE);
        newuser.setClickable(false);
        olduser.setClickable(false);
        olduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WXEntryActivity.this, BindingPhonenumberActivity.class);
                intent.putExtra("usertype", 0);
                startActivity(intent);
                finish();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkuser();
            }
        });
    }

    private static final String TAG = "WXEntryActivity";

    /**
     * 检查用户时候有
     */
    private void checkuser() {
        RequestBody body = new FormBody.Builder()
                .add("unionid", UserData.UNIONID)
                .build();
//        Log.d(TAG, "checkuser: " + UserData.UNIONID);
        String url = ApiFactify.HTF_HOST + "Api/login/new_validate.php";
        MyOkHttp.getInstance().post(url, body, new MyOkHttp.RequestCallBack() {
            @Override
            public void success(String data) {
                for (int i = 0; i < data.length(); i++) {
//                    Log.d(TAG, "success_checkuser: " + data);
                    if (data.substring(i, i + 1).equals("{")) {
                        data = data.substring(i);
//                        Log.d(TAG, "success_checkuser" + i + ":" + data);
                        break;
                    }
                }
//                Log.d(TAG, "success_checkuser:" + data + "  " + data.length());
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String status = String.valueOf(jsonObject.get("status"));
                    String msg = String.valueOf(jsonObject.get("msg"));
                    if (status.equals("1")) {
                        JSONObject listjson = jsonObject.getJSONObject("list");
                        String userid = String.valueOf(listjson.get("userid"));
                        String username = String.valueOf(listjson.get("username"));
                        String usertype = String.valueOf(listjson.get("usertype"));
                        String sessionid = String.valueOf(listjson.get("sessionid"));
                        String servicefee = String.valueOf(listjson.get("servicefee"));
                        String token = String.valueOf(listjson.get("token"));
                        UserData.USER_ID = userid;
                        UserData.USERNAME = username;
                        UserData.USERTYPE = usertype;
                        UserData.USERTOKENID = token;
//                        UserData.serviceffe = servicefee;
                        UserData.USERSESSIONID = sessionid;

                        if (mLoading != null && mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                        weixin_icon.setVisibility(View.VISIBLE);
                        weixin_tiaozhuan_layout.setVisibility(View.VISIBLE);
                        if (token.equals("")) {
                            if (mLoading != null && mLoading.isShowing())
                                mLoading.dismiss();
                            Intent intent = new Intent(WXEntryActivity.this, BindingPhonenumberActivity.class);
                            intent.putExtra("usertype", 1);
                            startActivity(intent);
                            finish();
                        } else {
                            sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean("islogin", true);
                            editor.putString("username", UserData.USERNAME);
                            editor.putString("token_id", UserData.USERTOKENID);
                            editor.commit();
                            Log.d("wxentryactivity", "token:----" + UserData.USERTOKENID);
                            UserData.USERISLOGIN = true;
                            if (mLoading != null && mLoading.isShowing())
                                mLoading.dismiss();
                            startActivity(new Intent(WXEntryActivity.this, LabeegoActivity.class));
//                            Intent intent = new Intent(WXEntryActivity.this, WebsActivity.class);
//                            intent.putExtra("web_url", Web_Url.ME_URL);
//                            startActivity(intent);
                            LoginActivity.Companion.getMLoginActivity().finish();
                            finish();
                        }
                    } else {
                        MyToast.showMsg(msg);
                    }
                } catch (JSONException e) {
//                    Log.d(TAG, "checkuser_josngexception: " + e.getMessage());
                    MyToast.showMsg(e.getMessage());
                }
            }

            @Override
            public void fail(Request request, Exception e) {
//                Log.d(TAG, "checkuser_fail: " + e.getMessage());
                MyToast.showMsg(e.getMessage());
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        wxAPI.handleIntent(getIntent(), this);
//        Log.d(TAG, "WXEntryActivity onNewIntent");
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.d(TAG, "baseReq" + baseReq + "");
    }

    @Override
    public void onResp(BaseResp baseResp) {

        SendAuth.Resp authresp = (SendAuth.Resp) baseResp;
        Log.d(TAG, "onResp:----\n" + baseResp.openId + "  " + baseResp.getType() + "   " + authresp.errCode);
//        if (baseResp.getType() == 2) {
//            mLoading.dismiss();
//            finish();
//        } else {
//            SendAuth.Resp authresp = (SendAuth.Resp) baseResp;
        if (authresp.errCode == 0) {
            Log.d(TAG, "onResp:----\n" + authresp.code);
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                    "appid=" + UserData.wxappid + "&secret=" + UserData.wxsecret +
                    "&code=" + authresp.code + "&grant_type=authorization_code";
            MyOkHttp.getInstance().get(url, new MyOkHttp.RequestCallBack() {
                @Override
                public void success(String data) {
//                    Log.d(TAG, "onResp:----\n" + data);
                    try {
                        JSONObject jsong = new JSONObject(data);
                        String access_token = String.valueOf(jsong.get("access_token"));
                        String expires_in = String.valueOf(jsong.get("expires_in"));
                        String refresh_token = String.valueOf(jsong.get("refresh_token"));
                        String openid = String.valueOf(jsong.get("openid"));
                        String scope = String.valueOf(jsong.get("scope"));
                        String unionid = String.valueOf(jsong.get("unionid"));
                        UserData.UNIONID = unionid;
                        getweixinuserinfo(access_token, openid);
                    } catch (JSONException e) {

                    }
                }

                @Override
                public void fail(Request request, Exception e) {

                }
            });
        } else {
            finish();
        }
//        }
    }

    private void getweixinuserinfo(String access_token, String openid) {
        String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=" +
                access_token + "&openid=" + openid;
        MyOkHttp.getInstance().get(url2, new MyOkHttp.RequestCallBack() {
            @Override
            public void success(String data) {
//                Log.d(TAG, data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String openid = String.valueOf(jsonObject.get("openid"));
                    String nickname = String.valueOf(jsonObject.get("nickname"));
                    String sex = String.valueOf(jsonObject.get("sex"));
                    String language = String.valueOf(jsonObject.get("language"));
                    String city = String.valueOf(jsonObject.get("city"));
                    String province = String.valueOf(jsonObject.get("province"));
                    String country = String.valueOf(jsonObject.get("country"));
                    String headimgurl = String.valueOf(jsonObject.get("headimgurl"));
                    String unionid = String.valueOf(jsonObject.get("unionid"));
                    wexinlogin(unionid, nickname, String.valueOf(sex), headimgurl);
                } catch (JSONException e) {

                }
            }

            @Override
            public void fail(Request request, Exception e) {

            }
        });
    }

    private SharedPreferences sp;

    private void wexinlogin(String unionid, String nickname, String sex, String headimgurl) {
        String url = ApiFactify.HTF_HOST + "Api/login/wechat_login.php";
//        Log.d(TAG, url);
        RequestBody body = new FormBody.Builder().add("unionid", unionid).add("nickname", nickname).add("sex", sex).add("headimgurl", headimgurl).build();
        MyOkHttp.getInstance().post(url, body, new MyOkHttp.RequestCallBack() {
            @Override
            public void success(String data) {
//                Log.d(TAG, data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String status = String.valueOf(jsonObject.get("status"));
                    String msg = String.valueOf(jsonObject.get("msg"));
                    JSONObject jsonlist = jsonObject.getJSONObject("list");
                    String unionid = String.valueOf(jsonlist.get("unionid"));
                    String url = String.valueOf(jsonlist.get("url"));
                    String userid = String.valueOf(jsonlist.get("userid"));
                    String username = String.valueOf(jsonlist.get("username"));
                    String usertype = String.valueOf(jsonlist.get("usertype"));
                    String sessionid = String.valueOf(jsonlist.get("sessionid"));
                    String token = String.valueOf(jsonlist.get("token"));
                    String servicefee = String.valueOf(jsonlist.get("servicefee"));
                    if (mLoading != null && mLoading.isShowing()) {
                        mLoading.dismiss();
                    }
                    if (userid.equals("") || username.equals("")) {
                        //跳转到绑定用户
                        weixin_icon.setVisibility(View.VISIBLE);
                        weixin_tiaozhuan_layout.setVisibility(View.VISIBLE);
                        newuser.setClickable(true);
                        olduser.setClickable(true);
                    } else {
                        weixin_icon.setVisibility(View.GONE);
                        weixin_tiaozhuan_layout.setVisibility(View.GONE);
                    }
                    userlogin(username, token);
                } catch (JSONException e) {
//                    Log.d(TAG, "success: " + e.getMessage());
                }
            }

            @Override
            public void fail(Request request, Exception e) {
//                Log.d(TAG, "success: " + e.getMessage());

            }
        });
    }

    public void userlogin(String username, String usertoken) {
        String url = ApiFactify.HOST + "Api/login/automatic_login.php";
        RequestBody body = new FormBody.Builder().add("userid", username)
                .add("token", usertoken).build();
        MyOkHttp.getInstance().post(url, body, new MyOkHttp.RequestCallBack() {
            @Override
            public void success(String data) {

                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject datas = jsonObject.getJSONObject("data");
                    String status = String.valueOf(datas.get("status"));
                    String msg = String.valueOf(datas.get("msg"));
                    if (status.equals("1")) {
                        JSONObject listbean = datas.getJSONObject("list");
                        String username = String.valueOf(listbean.get("username"));
                        String usertype = String.valueOf(listbean.get("usertype"));
                        String userid = String.valueOf(listbean.get("userid"));
                        String token = String.valueOf(listbean.get("token"));
                        String servicefee = String.valueOf(listbean.get("servicefee"));
                        String url = String.valueOf(listbean.get("url"));
                        String sessionid = String.valueOf(listbean.get("sessionid"));
//                        LoginBean.DataBean.ListBean listBean = new LoginBean.DataBean.ListBean
//                                (userid, username, usertype, servicefee, sessionid, "", url, token);
//                        LoginBean.DataBean dataBean = new LoginBean.DataBean(listBean, status,
//                                msg, "", "");
//                        LoginBean loginBean = new LoginBean(dataBean);
                        UserData.USER_ID = userid;
                        UserData.USERNAME = username;
                        UserData.USERTYPE = usertype;
                        UserData.USERTOKENID = token;
//                    UserData.serviceffe = servicefee;
                        UserData.USERSESSIONID = sessionid;
                        if (mLoading != null && mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                        if (userid.equals("") || username.equals("")) {
                            //跳转到绑定用户
                            weixin_icon.setVisibility(View.VISIBLE);
                            weixin_tiaozhuan_layout.setVisibility(View.VISIBLE);
                            UserData.USERISLOGIN = false;
                            newuser.setClickable(true);
                            olduser.setClickable(true);
                        } else {
                            weixin_icon.setVisibility(View.GONE);
                            weixin_tiaozhuan_layout.setVisibility(View.GONE);
                            if (mLoading != null && mLoading.isShowing())
                                mLoading.dismiss();
                            sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean("islogin", true);
                            editor.putString("username", UserData.USERNAME);
                            editor.putString("token_id", UserData.USERTOKENID);
                            editor.commit();
                            UserData.USERISLOGIN = true;

                            Intent intent = new Intent(WXEntryActivity.this, WebsActivity.class);
                            startActivity(intent);
//                        startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
                            LoginActivity.Companion.getMLoginActivity().finish();
                            finish();
                        }
                    } else {
                        UserData.USERISLOGIN = false;
                        MyToast.showMsg(msg);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(Request request, Exception e) {
//                Log.d(TAG, "success: " + e.getMessage());
            }
        });
    }
}
