package com.td.td.view;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class SearchResultWebView extends WebViewClient {
	private WebView mWebView;
    private ProgressBar mPd;
    private String mUrl;
    private Context mContext;

    public SearchResultWebView(WebView webView, final ProgressBar pd, String url, Context context){
		mWebView = webView;
		mPd = pd;
		mUrl = url;
		mContext = context;
	}

	public void showUrl(){
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && mPd.getVisibility() == ProgressBar.GONE){
                    mPd.setVisibility(ProgressBar.VISIBLE);
                }
                mPd.setProgress(progress);
                if(progress == 100) {
                   mPd.setVisibility(ProgressBar.GONE);
                }
             }
	    });
	    
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.loadUrl(mUrl);
	}
}
