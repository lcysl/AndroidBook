package com.uestc.lcy.androidbook.modules.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            SearchBean.DataBean.DatasBean data = mDatas.get(position);
            holder.mAuthorTv.setText(data.getAuthor());
            holder.mNiceDateTv.setText(data.getNiceDate());
            holder.mChapterNameTv.setText(data.getChapterName());

            holder.mTitleWv.loadDataWithBaseURL(null, data.getTitle(), "text/html", "utf-8", null);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthorTv;
        TextView mNiceDateTv;
        WebView mTitleWv;
        TextView mChapterNameTv;

        public ViewHolder(View itemView) {
            super(itemView);

            mAuthorTv = itemView.findViewById(R.id.tv_author);
            mNiceDateTv = itemView.findViewById(R.id.tv_niceDate);
            mTitleWv = itemView.findViewById(R.id.wv_title);
            mChapterNameTv = itemView.findViewById(R.id.tv_chapterName);
        }
    }
}
