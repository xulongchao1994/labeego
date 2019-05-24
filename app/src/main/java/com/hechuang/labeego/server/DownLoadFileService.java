package com.hechuang.labeego.server;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;

import com.hechuang.labeego.api.PathConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 下载文件
 */
public class DownLoadFileService extends Service {

    private DownloadManager dm;
    private static long enqueue;
    private BroadcastReceiver receiver;
    private String url = "";
    private static final String TAG = "DownLoadFileService";
    private String type = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            url = bundle.getString("url");
            type = bundle.getString("type");
            if (type == null || type.equals("")) {
                type = "";
            }
        } else {
            url = PathConstant.INSTANCE.getAPP_DOWNLOAD_URL();
        }
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (url.endsWith(".apk")) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    File apkfile = queryDownloadedApk(context);
                    Uri data =null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        if (FileProvider.getUriForFile(context, getPackageName() + ".fileProvider", apkfile) == null) {
                            Toast.makeText(DownLoadFileService.this, "下载失败", Toast.LENGTH_SHORT).show();
                        } else {
                            data = FileProvider.getUriForFile(context, getPackageName() + ".fileProvider", apkfile);
                            // 给目标应用一个临时授权
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        }
                    } else {
                        if (Uri.fromFile(apkfile) == null) {
                            Toast.makeText(DownLoadFileService.this, "下载失败", Toast.LENGTH_SHORT).show();
                        } else {
                            data = Uri.fromFile(apkfile);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                    }
                    intent.setDataAndType(data,
                            "application/vnd.android.package-archive");
                    if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                        context.startActivity(intent);
                    }
                } else {
//                    com.hechuang.labeego.tools.Util.MyToast.showMsg("已保存");
                }
                stopSelf();
            }
        };

        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        startDownload();

        return Service.START_STICKY;
    }

    public static File queryDownloadedApk(Context context) {
        File targetApkFile = null;
        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (enqueue != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(enqueue);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downloader.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetApkFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetApkFile;
    }

    private void startDownload() {
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setMimeType("application/vnd.android.package-archive");
        if (type.equals("apk")) {
            String timeStamp = PathConstant.APP_NAME_ZH + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            request.setDestinationInExternalPublicDir(PathConstant.APK_DIR, timeStamp + ".apk");
//            com.hechuang.labeego.tools.Util.MyToast.showMsg("下载成功");
//            id = dm.enqueue(request);
        } else if (url.endsWith(".jpg")) {
            String timeStamp = PathConstant.INSTANCE.getAPP_NAME() + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            request.setDestinationInExternalPublicDir(PathConstant.INSTANCE.IMG_DIR, timeStamp + ".jpg");
//            com.hechuang.labeego.tools.Util.MyToast.showMsg("下载成功");
        } else if (url.endsWith(".png")) {
            String timeStamp = PathConstant.INSTANCE.getAPP_NAME() + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            request.setDestinationInExternalPublicDir(PathConstant.INSTANCE.IMG_DIR, timeStamp + ".png");
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(),
                        PathConstant.INSTANCE.IMG_DIR + timeStamp + ".png", timeStamp + ".png", null);
//                com.hechuang.labeego.tools.Util.MyToast.showMsg("下载成功");
            } catch (FileNotFoundException e) {
//                com.hechuang.labeego.tools.Util.MyToast.showMsg("下载失败");
                e.printStackTrace();
            }
            // 最后通知图库更新
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + PathConstant.INSTANCE.IMG_DIR + timeStamp + ".png")));
        }
        request.setTitle(PathConstant.INSTANCE.getAPP_NAME());
        request.setDescription(PathConstant.INSTANCE.APP_NAME_ZH);
        request.setVisibleInDownloadsUi(true);
        enqueue = dm.enqueue(request);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
