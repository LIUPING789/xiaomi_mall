package com.knight.xiaomimall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.knight.xiaomimall.adapter.LefterAdapter;
import com.knight.xiaomimall.adapter.RighterAdapter;
import com.knight.xm_mall.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    //@formatter:off
    @BindView(R.id.rv_left) RecyclerView leftRecyclerView;
    @BindView(R.id.rv_right) RecyclerView rightRecyclerView;
   // @formatter:on
   private RecyclerView.LayoutManager leftLayoutManager;
    private RecyclerView.LayoutManager rightLayoutManager;
    private LefterAdapter leftAdapter;
    private RighterAdapter rightAdapter;
    private List<String> bigSortList = new ArrayList<>();
    private List<String> smallSortList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Unbinder bind = ButterKnife.bind(this);
        bind.unbind();
    }

    private void initView() {
        leftLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        leftRecyclerView.setLayoutManager(leftLayoutManager);
        leftAdapter = new LefterAdapter(this, bigSortList, leftRecyclerView);
        //左侧列表的点击事件
        leftAdapter.setItemClickListener(new LefterAdapter.LeftListener() {
            @Override
            public void onItemClick(int position) {
                //向适配器中返回点击的位置，在适配器中进行操作
                leftAdapter.getSelectedPosition(position);
                rightAdapter.getSelectedPosition(position);
            }
        });
        leftRecyclerView.setAdapter(leftAdapter);


        rightLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rightRecyclerView.setLayoutManager(rightLayoutManager);
        rightAdapter = new RighterAdapter(getApplicationContext(), bigSortList, smallSortList, rightRecyclerView);
        //右侧列表的点击事件
        rightAdapter.setItemClickListener(new RighterAdapter.RightListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, bigSortList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        //右侧列表的滚动事件
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取右侧列表的第一个可见Item的position
                int TopPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                //左侧得到这个position
                leftAdapter.getSelectedPosition(TopPosition);
            }
        });
        rightRecyclerView.setAdapter(rightAdapter);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 20; i++) {
            bigSortList.add("商品" + i);
        }
        for (int i = 0; i < 10; i++) {
            smallSortList.add("标签" + i);
        }
    }
}
