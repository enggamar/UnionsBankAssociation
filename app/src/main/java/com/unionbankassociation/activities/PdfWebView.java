package com.unionbankassociation.activities;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.unionbankassociation.R;
import com.unionbankassociation.utils.AppUtils;
import com.unionbankassociation.utils.PermissionUtility;


/**
 * Created by appinventiv on 10/10/17.
 */

public class PdfWebView extends BaseActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private ImageView ivMenu;
    private DownloadManager downloadManager;
    private String name, pdf;
    private ImageView ivDownload;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initializeUI();
        webView.getSettings().setJavaScriptEnabled(true);
        ivMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
        pdf = getIntent().getExtras().getString("pdf");
        name = getIntent().getExtras().getString("TITLE");
        tvTitle.setText(name);

        setWebView(pdf);

    }

    private void initializeUI() {
        webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tvTitle = (TextView) findViewById(R.id.title);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        ivDownload = (ImageView) findViewById(R.id.iv_download);
        ivDownload.setVisibility(View.VISIBLE);
        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionUtility.isPermissionGranted(PdfWebView.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 101)) {
                    startDownload(pdf);
                }

            }
        });
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 startDownload(pdf);
                }
                break;
        }
    }


    private void setWebView(String url) {
        try {
            webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
            webView.setWebViewClient(new RealWebViewClient());
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (progress == 100)
                        progressBar.setVisibility(View.GONE);
                }
            });

        } catch (Exception e) {
            //Log.e("Web Error", e.getMessage());
            e.printStackTrace();
        }
    }

    private class RealWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                view.loadUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public void startDownload(String url) {
        DownloadManager mgr = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Uri uri = Uri.parse(url);

        Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .mkdirs();


        mgr.enqueue(new DownloadManager.Request(uri)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                        DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(true)
                .setTitle(name)
                .setDescription("Downloading")
                .setNotificationVisibility(View.VISIBLE)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                        name + ".pdf"));
        AppUtils.showToast(this, "Downloading Start");


    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            AppUtils.showToast(ctxt, "Downloading completed");
        }
    };
}
