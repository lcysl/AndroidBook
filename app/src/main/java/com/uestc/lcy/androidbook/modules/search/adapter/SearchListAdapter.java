package com.uestc.lcy.androidbook.modules.search.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.SearchBean;

import java.util.List;


/**
 * Created by Administrator on 2018\5\6 0006.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    private List<SearchBean.DataBean.DatasBean> mDatas;
    private Context mContext;

    public SearchListAdapter(List<SearchBean.DataBean.DatasBean> datas, Context context) {
        this.mDatas = datas;
        this.mContext = context;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            SearchBean.DataBean.DatasBean data = mDatas.get(position);
            holder.mAuthorTv.setText(data.getAuthor());
            holder.mNiceDateTv.setText(data.getNiceDate());
            holder.mChapterNameTv.setText(data.getChapterName());

            String title;
            if (Build.VERSION.SDK_INT >= 24) {
                title = String.valueOf(Html.fromHtml(data.getTitle(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                title = String.valueOf(Html.fromHtml(data.getTitle()));
            }
            if (Build.VERSION.SDK_INT >= 24) {
                holder.mTitleTv.setText(title);
            } else {
                holder.mTitleTv.setText(title);
            }

            ((ViewHolder) holder).mTitleTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(((ViewHolder) holder).itemView, position, mDatas);
                    }
                }
            });
            return;
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
        void onItemClick(View view, int position, List<SearchBean.DataBean.DatasBean> datas);
    }

}
