package com.td.td.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.td.td.R;
import com.td.td.view.SearchResultWebView;
import com.td.td.view.Shadow;

public class WebViewFragment extends Fragment {
    private Shadow mShadow;
    private View mView;
    private WebView mWebView;
    private ProgressBar pd = null;
    private SearchResultWebView mSearchResultWebView;
	public static String mUrl = "";

	public static Fragment newInstance(String url) {
		mUrl = url;
		WebViewFragment f = new WebViewFragment();
		Bundle args = new Bundle();
		args.putString("url", url);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		if(getArguments() != null){
			mUrl = getArguments().getString("url");
		}

		mView  = inflater.inflate(R.layout.webview_search_result, container, false);
		mWebView = (WebView) mView.findViewById(R.id.webView);
		pd = (ProgressBar) mView.findViewById(R.id.web_view_progress_bar);

        mSearchResultWebView = new SearchResultWebView(mWebView, pd, mUrl, getActivity());
        mSearchResultWebView.showUrl();
		return mView;
	}
	@Override
	public void onResume() {
		super.onResume();

		mShadow = new Shadow(getActivity());
		mShadow.mShadowWidth = (int)getResources().getDimension(R.dimen.shadow_width);
		mShadow.mSecondaryShadowDrawable = getActivity().getResources().getDrawable(R.drawable.shadowright);
		mShadow.mContent = mView;
		LayoutParams aboveParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		((ViewGroup) mView).addView(mShadow, aboveParams);
	}

	public void goBack(){
		mWebView.goBack();
	}

	public boolean canGoBack(){
		return mWebView.canGoBack();
	}

}
