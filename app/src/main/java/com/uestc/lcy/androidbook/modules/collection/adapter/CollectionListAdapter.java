package com.uestc.lcy.androidbook.modules.collection.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.FeedArticleData;
import com.uestc.lcy.androidbook.modules.home.article_content.ArticleContentActivity;

import java.util.List;

/**
 * Created by lcy on 2018\6\18 0018.
 */

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.ViewHolder>{

    private List<FeedArticleData> mDatas;
    private Context mContext;

    public CollectionListAdapter(List<FeedArticleData> datas, Context context){
        this.mDatas = datas;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FeedArticleData data = mDatas.get(position);
        holder.mAuthorTv.setText(data.getAuthor());
        holder.mNiceDateTv.setText(data.getNiceDate());
        holder.mTitleTv.setText(data.getTitle());
        holder.mChapterNameTv.setText(data.getChapterName());

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ArticleContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", data.getLink());
                bundle.putString("title", data.getTitle());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mAuthorTv;
        TextView mNiceDateTv;
        TextView mTitleTv;
        TextView mChapterNameTv;
        CardView mItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mAuthorTv = itemView.findViewById(R.id.tv_author);
            mNiceDateTv = itemView.findViewById(R.id.tv_niceDate);
            mTitleTv = itemView.findViewById(R.id.tv_title);
            mChapterNameTv = itemView.findViewById(R.id.tv_chapterName);
            mItem = itemView.findViewById(R.id.layout_item);
        }
    }

}
