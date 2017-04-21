package com.td.td.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.td.td.R;
import com.td.td.adapter.RelatedTopicAdapter;
import com.td.td.models.DuckDuckGo;
import com.td.td.retro.ApiService;
import com.td.td.retro.RetroClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResults extends Fragment {
    private RecyclerView rvDuckDuckGo;
    private ProgressBar pb;
    private static DuckDuckGo duckDuckGoList = new DuckDuckGo();
    private View mView;
    public static SearchResults getInstance() {
        return new SearchResults();
    }

    public interface onItemClickListener{
        public void onItemClick(String url);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_results, container, false);
        pb = (ProgressBar) mView.findViewById(R.id.pb);
        rvDuckDuckGo = (RecyclerView) mView.findViewById(R.id.rvDuckDuckGo);
        downloadJson();
        return mView;
    }

    private void downloadJson(){
        //TODO: check network connection
        ApiService api = RetroClient.getApiService();
        Call<DuckDuckGo> call = api.getMyJSON();
        call.enqueue(new Callback<DuckDuckGo>() {
            @Override
            public void onResponse(Call<DuckDuckGo> call, Response<DuckDuckGo> response) {
                if(response.isSuccessful()) {
                    pb.setVisibility(View.GONE);
                    duckDuckGoList = response.body();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    rvDuckDuckGo.setLayoutManager(linearLayoutManager);
                    RelatedTopicAdapter relatedTopicAdapter = new RelatedTopicAdapter(getActivity(), duckDuckGoList.getCombinedRelatedTopics());
                    rvDuckDuckGo.setAdapter(relatedTopicAdapter);
                } else {
                    //TODO: retry for a few more times and then notify user
                    Toast.makeText(getActivity(), "Download Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DuckDuckGo> call, Throwable t) {
            }
        });
    }
}
