package com.uestc.lcy.androidbook.modules.home.article_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final int TYPE_NORMAL = 0;
//    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_HEATER = 1;

    //获取从fragment中传递过来的每个item的数据集合
    private List<ArticleListBean.DataBean.DatasBean> mDatas;

    private View mHeaderView;


    //事件回调监听
    private OnItemClickListener mOnItemClickListener;


    public ArticleListAdapter(List<ArticleListBean.DataBean.DatasBean> datas) {
        this.mDatas = datas;
    }


    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    /**
     * 设置回调监听
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEATER;
        }
        return TYPE_NORMAL;
    }

    /**
     * 根据不同的类型返回不同的布局
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEATER) {
            return new ViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_list, parent, false);
        return new ViewHolder(view);
    }

    private ArticleListBean.DataBean.DatasBean data;

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //绑定数据

        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof ViewHolder) {
                final int pos = getRealPosition(holder);
                data = mDatas.get(pos);

                holder.mAuthorTv.setText(data.getAuthor());
                holder.mNiceDateTv.setText(data.getNiceDate());
                holder.mTitleTv.setText(data.getTitle());
                holder.mChapterNameTv.setText(data.getChapterName());

                ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(((ViewHolder) holder).itemView, pos, mDatas);
                        }
                    }
                });
                return;
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEATER){
            return;
        } else {
            return;
        }
    }

    private int getRealPosition(ViewHolder holder) {
        int position = holder.getLayoutPosition();
        Log.d("--position--", position +"");
        return mHeaderView == null ? position : position - 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthorTv;
        TextView mNiceDateTv;
        TextView mTitleTv;
        TextView mChapterNameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }

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
        void onItemClick(View view, int position, List<ArticleListBean.DataBean.DatasBean> datas);
    }
}
