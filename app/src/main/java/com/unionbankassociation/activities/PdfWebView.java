package com.unionbankassociation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.unionbankassociation.R;


/**
 * Created by appinventiv on 10/10/17.
 */

public class PdfWebView extends BaseActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private ImageView ivMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initializeUI();
        webView.getSettings().setJavaScriptEnabled(true);
        ivMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
        String pdf = getIntent().getExtras().getString("pdf");
        String name = getIntent().getExtras().getString("TITLE");
        tvTitle.setText(name);

        setWebView(pdf);

    }

    private void initializeUI() {
        webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tvTitle = (TextView) findViewById(R.id.title);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

}
