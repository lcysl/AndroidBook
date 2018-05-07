package com.uestc.lcy.androidbook.modules.navigate.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.NavigateBean;
import com.uestc.lcy.androidbook.modules.home.article_content.ArticleContentActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018\5\3 0003.
 */

public class NavigateAdapter extends RecyclerView.Adapter<NavigateAdapter.ViewHolder> {

    private List<NavigateBean.DataBean> mData;
    private Context mContext;

    public NavigateAdapter(List<NavigateBean.DataBean> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_navigate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final NavigateBean.DataBean data = mData.get(position);
            holder.mNavigateTitleTv.setText(data.getName());

            //加载flowlayout布局
            List<String> title = new ArrayList<>();
            for (NavigateBean.DataBean.ArticlesBean article : data.getArticles()) {
                title.add(article.getTitle());
            }
            holder.mFlowLayout.setAdapter(new TagAdapter<String>(title) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_tag, holder.mFlowLayout, false);
                    tv.setText(s);
                    return tv;
                }
            });
            holder.mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    NavigateBean.DataBean.ArticlesBean articleBean = data.getArticles().get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", articleBean.getLink());
                    bundle.putString("title", articleBean.getTitle());
                    Intent intent = new Intent(mContext, ArticleContentActivity.class);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                    return true;
                }
            });

            return;
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNavigateTitleTv;
        TagFlowLayout mFlowLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            mNavigateTitleTv = itemView.findViewById(R.id.tv_navigate_title);
            mFlowLayout = itemView.findViewById(R.id.tfl_navigate_content);
        }
    }
}
