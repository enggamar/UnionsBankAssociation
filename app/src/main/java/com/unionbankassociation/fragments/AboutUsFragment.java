package com.unionbankassociation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;

public class AboutUsFragment extends Fragment {

    private TextView tvTitle;
    private AppCompatImageView ivBack;
    private WebView webView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_webview, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.title);
        ivBack = view.findViewById(R.id.iv_menu);
        ivBack.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        tvTitle.setText(getString(R.string.about_us));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });
        webView = (WebView) view.findViewById(R.id.webview);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        setWebView("http://52.2.121.230/pages/about-us");
    }

    private void setWebView(String url) {
        try {
            webView.loadUrl(url);
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

