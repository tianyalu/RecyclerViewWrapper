package com.sty.recyclerview.wrapper.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Steven.T on 2017/10/12/0012.
 * 代码参考：http://blog.csdn.net/qibin0506/article/details/49716795
 */

public abstract class BaseRcvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    protected Context mContext;
    protected List<T> mData;
    protected View mHeaderView;
    protected OnItemClickListener mOnItemClickListener;

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);
    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, T data);

    public interface OnItemClickListener<T>{
        void onItemClick(View view, int position, T item);
    }

    public BaseRcvAdapter(Context context, List<T> data){
        this.mContext = context;
        this.mData = data;
    }

    public void setHeaderView(View headerView){
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView(){
        return mHeaderView;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        if(null == mData || mData.isEmpty()) {
            return 0;
        }
        return mHeaderView == null ? mData.size() : mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null){
            return TYPE_NORMAL;
        }
        if(position == 0){
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder){
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new Holder(mHeaderView);
        }
        return onCreate(parent, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER){
            return;
        }
        final int pos = getRealPosition(holder);
        final T data = mData.get(pos);

        onBind(holder, pos, data);

        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, pos, data);
                }
            });
        }
    }

    public class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView){
            super(itemView);
        }
    }
}
