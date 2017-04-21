package com.td.td;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.td.td.adapter.RelatedTopicAdapter;
import com.td.td.fragments.SearchResults;
import com.td.td.fragments.WebViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchResults.onItemClickListener {

    private final float FRAGMENT_PAGE_WIDTH = 0.98f;
    private ArrayList<Fragment> mFragments;
    private ViewPager vpSearchResult;
    private WebViewPagerAdapter mWebViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpSearchResult = (ViewPager) findViewById(R.id.vpSearchResult);
        mFragments = new ArrayList<Fragment>();
        mWebViewPagerAdapter = new WebViewPagerAdapter(getSupportFragmentManager());
        vpSearchResult.setAdapter(mWebViewPagerAdapter);
        vpSearchResult.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {}

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if(mFragments.size() > 1){
                            mFragments.remove(1);
                            mWebViewPagerAdapter.notifyDataSetChanged();
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        mFragments.add(SearchResults.getInstance());
        mWebViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String url) {
        mFragments.add(WebViewFragment.newInstance(url));
        mWebViewPagerAdapter.notifyDataSetChanged();
        vpSearchResult.setCurrentItem(1);
    }

    public class WebViewPagerAdapter extends FragmentStatePagerAdapter {
        public WebViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public float getPageWidth(int position){
            if(position == 1) return FRAGMENT_PAGE_WIDTH;
            return 1.0f;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getItemPosition (Object object) {
            if(object.equals(mFragments.get(0))){
                return 0;
            } else{
                return POSITION_NONE;
            }
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }
    }

    @Override
    public void onBackPressed() {
        if(mFragments.size() > 1) {
            WebViewFragment webViewFragment = (WebViewFragment) mFragments.get(1);
            if(webViewFragment.canGoBack()) {
                webViewFragment.goBack();
            } else vpSearchResult.setCurrentItem(0);
            return;
        }
        super.onBackPressed();
    }
}
