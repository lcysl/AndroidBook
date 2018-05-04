package com.uestc.lcy.androidbook.modules.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.ProjectSortBean;

import java.util.List;

/**
 * Created by lcy on 2018\5\4 0004.
 */

public class ProjectSortAdapter extends RecyclerView.Adapter<ProjectSortAdapter.ViewHolder> {

    private List<ProjectSortBean.DataBean> mData;
    private Context mContext;
    private OnItemClickListener mListener;

    public ProjectSortAdapter(List<ProjectSortBean.DataBean> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_project_sort, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ProjectSortBean.DataBean data = mData.get(position);
            holder.mProjectSortTv.setText(data.getName());

            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(((ViewHolder) holder).itemView, position, mData);
                    }
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

        TextView mProjectSortTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mProjectSortTv = itemView.findViewById(R.id.tv_project_sort);
        }
    }

    /**
     * 点击事件的内部接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position, List<ProjectSortBean.DataBean> data);
    }
}
