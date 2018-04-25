package com.uestc.lcy.androidbook.modules.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.ArticleListBean;

import java.util.List;

/**
 * Created by lcy on 2018\4\24 0024.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {
    private List<ArticleListBean.DataBean.DatasBean> mDatas;

    public ArticleListAdapter(List<ArticleListBean.DataBean.DatasBean> datas) {
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //绑定数据
        ArticleListBean.DataBean.DatasBean data = mDatas.get(position);
        holder.mAuthorTv.setText(data.getAuthor());
        holder.mNiceDateTv.setText(data.getNiceDate());
        holder.mTitleTv.setText(data.getTitle());
        holder.mChapterNameTv.setText(data.getChapterName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthorTv;
        TextView mNiceDateTv;
        TextView mTitleTv;
        TextView mChapterNameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mAuthorTv = itemView.findViewById(R.id.tv_author);
            mNiceDateTv = itemView.findViewById(R.id.tv_niceDate);
            mTitleTv = itemView.findViewById(R.id.tv_title);
            mChapterNameTv = itemView.findViewById(R.id.tv_chapterName);
        }
    }
}
