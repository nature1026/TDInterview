package com.td.td.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.td.td.MainActivity;
import com.td.td.R;
import com.td.td.fragments.SearchResults;
import com.td.td.models.RelatedTopic;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kay on 2016-05-05.
 */
public class RelatedTopicAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<RelatedTopic> relatedTopics;

    public RelatedTopicAdapter(Context context, List<RelatedTopic> relatedTopics) {
        mContext = context;
        this.relatedTopics = relatedTopics;
    }

    public class RelatedTopicPlaceHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ImageView ivRelatedTopic;
        public TextView  tvRelatedTopic;

        public RelatedTopicPlaceHolder(View v) {
            super(v);
            mView = v;
            ivRelatedTopic = (ImageView)  v.findViewById(R.id.ivRelatedTopic);
            tvRelatedTopic = (TextView)  v.findViewById(R.id.tvRelatedTopic);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RelatedTopicPlaceHolder(
                LayoutInflater.from(mContext).inflate(R.layout.list_related_topic_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final RelatedTopicPlaceHolder placeHolder = (RelatedTopicPlaceHolder) holder;
        final RelatedTopic relatedTopic = relatedTopics.get(position);
        Glide.with(mContext)
                .load(relatedTopic.getIcon().getURL())
                .crossFade()
                .into(placeHolder.ivRelatedTopic);

        placeHolder.tvRelatedTopic.setText(relatedTopic.getText());
        placeHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = relatedTopic.getFirstURL();
                ((MainActivity) mContext).onItemClick(url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return relatedTopics.size();
    }
}
