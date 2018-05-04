package com.uestc.lcy.androidbook.modules.knowledge_system.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.ChildArticleListBean;

import java.util.List;

/**
 * Created by lcy on 2018\5\2 0002.
 */

public class ChildArticleListAdapter extends RecyclerView.Adapter<ChildArticleListAdapter.ViewHolder> {

    private List<ChildArticleListBean.DataBean.DatasBean> mDatas;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ChildArticleListAdapter(List<ChildArticleListBean.DataBean.DatasBean> datas) {
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ChildArticleListBean.DataBean.DatasBean data = mDatas.get(position);
            holder.mAuthorTv.setText(data.getAuthor());
            holder.mNiceDateTv.setText(data.getNiceDate());
            holder.mTitleTv.setText(data.getTitle());
            holder.mChapterNameTv.setText(data.getChapterName());

            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(((ViewHolder) holder).itemView, position, mDatas);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
    /**
     * 点击事件的回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position, List<ChildArticleListBean.DataBean.DatasBean> datas);
    }
}
