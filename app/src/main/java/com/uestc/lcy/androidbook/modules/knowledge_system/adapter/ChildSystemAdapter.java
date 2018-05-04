package com.uestc.lcy.androidbook.modules.knowledge_system.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.lcy.androidbook.R;
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;

import java.util.List;

/**
 * Created by lcy on 2018\5\1 0001.
 */

public class ChildSystemAdapter extends RecyclerView.Adapter<ChildSystemAdapter.ViewHolder> {

    private List<KnowledgeSystemBean.DataBean.ChildrenBean> mChildren;
    private OnItemClickListener mOnItemClickListener;

    public ChildSystemAdapter(List<KnowledgeSystemBean.DataBean.ChildrenBean> children) {
        this.mChildren = children;
    }

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_system, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            String childName = mChildren.get(position).getName();
            holder.mChildNameTv.setText(childName);

            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(((ViewHolder) holder).itemView, position, mChildren);
                    }
                }
            });
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mChildren == null ? 0 : mChildren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mChildNameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mChildNameTv = itemView.findViewById(R.id.tv_child_name);
        }
    }

    /**
     * 点击事件的内部接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position, List<KnowledgeSystemBean.DataBean.ChildrenBean> children);
    }
}
