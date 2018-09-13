package com.knight.xiaomimall.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.knight.xiaomimall.weight.CustomGridView;
import com.knight.xm_mall.R;

import java.util.List;

/**
 * description: ${TODO}
 * autour: Knight
 * new date: 2018/9/13 on 15:13
 * e-mail: 37442216knight@gmail.com
 * update: 2018/9/13 on 15:13
 * version: v 1.0
 */
public class RighterAdapter extends RecyclerView.Adapter<RighterAdapter.ViewHolder> {

    private Context context;
    private List<String> bigSortList;
    private List<String> smallSortList;
    private RecyclerView recyclerView;
    private RightListener listener;

    public RighterAdapter(Context context, List<String> bigSortList, List<String> smallSortList, RecyclerView recyclerView) {
        this.context = context;
        this.bigSortList = bigSortList;
        this.smallSortList = smallSortList;
        this.recyclerView = recyclerView;
    }

    /**
     * 获取被选中的位置，将选中项移动到顶部，并刷新
     *
     * @param selectedPosition
     */
    public void getSelectedPosition(int selectedPosition) {
        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(selectedPosition, 0);
        notifyDataSetChanged();
    }

    /**
     * RecyclerView的点击方法
     *
     * @param listener
     */
    public void setItemClickListener(RightListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_right, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(bigSortList.get(position));
        GridViewAdapter adapter = new GridViewAdapter(context, smallSortList);
        holder.gridView.setAdapter(adapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, smallSortList.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bigSortList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvTitleHole;
        /**
         * 上面大标签的容器，要监听他的点击事件
         */
        RelativeLayout rlWhole;
        CustomGridView gridView;

        public ViewHolder(View itemView, final RightListener listener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTitleHole = itemView.findViewById(R.id.tv_title_whole);
            rlWhole = itemView.findViewById(R.id.rl_whole);
            gridView = itemView.findViewById(R.id.gridView);
            rlWhole.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    /**
     * RecyclerView没有内置监听器，自定义item点击事件
     */
    public interface RightListener {

        void onItemClick(int position);
    }

}
