package com.sty.recyclerview.wrapper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sty.recyclerview.wrapper.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Steven.T on 2017/10/23/0023.
 */

public class RcvListAdapter extends BaseRcvAdapter<RcvListItem> {
    public RcvListAdapter(Context context, List<RcvListItem> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_rcv, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, RcvListItem data) {
        if(viewHolder instanceof MyViewHolder){
            ((MyViewHolder) viewHolder).menuIcon.setImageResource(data.getIcon());
            ((MyViewHolder) viewHolder).menuText.setText(mContext.getResources().getString(data.getName()));
        }
    }

    class MyViewHolder extends BaseRcvAdapter.Holder {

        private ImageView menuIcon;
        private TextView menuText;

        public MyViewHolder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView){
                return;
            }
            menuIcon = itemView.findViewById(R.id.iv_list_icon);
            menuText = itemView.findViewById(R.id.tv_list_text);
        }
    }

    public static List<RcvListItem> buildPrintMenu() {
        return Arrays.asList(
                new RcvListItem(R.string.print_last, R.mipmap.print_last_icon),
                new RcvListItem(R.string.print_any, R.mipmap.print_any_icon),
                new RcvListItem(R.string.print_detail, R.mipmap.print_details_icon),
                new RcvListItem(R.string.print_summary, R.mipmap.print_summary_icon),
                new RcvListItem(R.string.print_last_batch, R.mipmap.print_last_batch_icon));
    }
}
