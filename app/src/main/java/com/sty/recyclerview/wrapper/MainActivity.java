package com.sty.recyclerview.wrapper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sty.recyclerview.wrapper.adapters.BaseRcvAdapter;
import com.sty.recyclerview.wrapper.adapters.DividerLinearItemDecoration;
import com.sty.recyclerview.wrapper.adapters.RcvListAdapter;
import com.sty.recyclerview.wrapper.adapters.RcvListItem;
import com.sty.recyclerview.wrapper.view.EmptyRecyclerView;

public class MainActivity extends AppCompatActivity {
    private EmptyRecyclerView recyclerView;
    private RcvListAdapter rcvListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews() {
        recyclerView = (EmptyRecyclerView) findViewById(R.id.recycler_view);

        //设置内容为空时的布局
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        View emptyView = View.inflate(this, R.layout.empty_view, null);
        emptyView.setLayoutParams(params);
        recyclerView.setEmptyView(emptyView);

        //设置分割线样式
        DividerLinearItemDecoration divider = new DividerLinearItemDecoration(this,
                DividerLinearItemDecoration.VERTICAL_LIST);
        divider.setDividerLeftOffset((int)getResources()
                .getDimension(R.dimen.rcv_item_divider_left_offset), false);
        divider.setDrawable(getResources().getDrawable(R.drawable.rcv_divider));
        recyclerView.addItemDecoration(divider);

        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //适配器
        rcvListAdapter = new RcvListAdapter(this, RcvListAdapter.buildPrintMenu());
        //rcvListAdapter = new RcvListAdapter(this, null);

        //设置头布局(处理填充子布局后子布局宽高失效问题)
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        View headView = View.inflate(this, R.layout.header_rcv, null);
        headView.setLayoutParams(params2);
        rcvListAdapter.setHeaderView( headView);

        recyclerView.setAdapter(rcvListAdapter);
    }

    private void setListeners() {

        rcvListAdapter.setOnItemClickListener(new BaseRcvAdapter.OnItemClickListener<RcvListItem>() {
            @Override
            public void onItemClick(View view, int position, RcvListItem item) {
                switch (item.getName()){
                    case R.string.print_last:
                        Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.print_any:
                        Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.print_detail:
                        Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.print_summary:
                        Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.print_last_batch:
                        Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
