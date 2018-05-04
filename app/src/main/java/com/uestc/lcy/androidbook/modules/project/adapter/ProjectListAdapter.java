package com.uestc.lcy.androidbook.modules.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.ProjectListBean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\4 0004.
 */

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

    private List<ProjectListBean.DataBean.DatasBean> mDatas;
    private Context mContext;

    public ProjectListAdapter(List<ProjectListBean.DataBean.DatasBean> datas, Context context) {
        this.mDatas = datas;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_project_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ProjectListBean.DataBean.DatasBean data = mDatas.get(position);
            holder.mTitleTv.setText(data.getTitle());
            holder.mDescTv.setText(data.getDesc());
            holder.mAuthorTv.setText(data.getAuthor());
            holder.mDateTv.setText(data.getNiceDate());

            Glide.with(mContext).load(data.getEnvelopePic()).into(holder.mPicIv);

            return;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthorTv, mDescTv, mTitleTv, mDateTv;
        ImageView mPicIv;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTv = itemView.findViewById(R.id.tv_project_title);
            mDescTv = itemView.findViewById(R.id.tv_project_desc);
            mDateTv = itemView.findViewById(R.id.tv_nice_date);
            mAuthorTv = itemView.findViewById(R.id.tv_project_author);
            mPicIv = itemView.findViewById(R.id.iv_project_pic);
        }
    }
}
