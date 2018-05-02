package com.uestc.lcy.androidbook.modules.sort.knowledge_system.adapter;

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
 * Created by lcy on 2018\4\30 0030.
 */

public class KnowledgeSystemAdapter extends RecyclerView.Adapter<KnowledgeSystemAdapter.ViewHolder>{

    //获取从fragment中传递过来的每个item的数据集合
    private List<KnowledgeSystemBean.DataBean> mData;
    //保存用户设置的监听器及set方法
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public KnowledgeSystemAdapter(List<KnowledgeSystemBean.DataBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge_system, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            KnowledgeSystemBean.DataBean data = mData.get(position);
            holder.mFirstNameTv.setText(data.getName());
            //获得所有的二级标题
            String childName = getChildName(data.getChildren());
            holder.mChildNameTv.setText(childName);

            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(((ViewHolder) holder).itemView, position, mData);
                    }
                }
            });
            return;
        }
    }

    private String getChildName(List<KnowledgeSystemBean.DataBean.ChildrenBean> children) {
        StringBuilder sb = new StringBuilder();
        for (KnowledgeSystemBean.DataBean.ChildrenBean child : children) {
            String childName = child.getName();
            sb.append(childName);
            sb.append("  ");
        }
        return sb.toString();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mFirstNameTv;
        TextView mChildNameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mFirstNameTv = itemView.findViewById(R.id.tv_type_first);
            mChildNameTv = itemView.findViewById(R.id.tv_type_children);
        }
    }

    /**
     * 点击事件的内部接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position, List<KnowledgeSystemBean.DataBean> data);
    }
}
