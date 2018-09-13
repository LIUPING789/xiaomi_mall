package com.knight.xiaomimall.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knight.xm_mall.R;

import java.util.List;

/**
 * description: ${TODO}
 * autour: Knight
 * new date: 2018/9/13 on 15:12
 * e-mail: 37442216knight@gmail.com
 * update: 2018/9/13 on 15:12
 * version: v 1.0
 */
public class LefterAdapter extends RecyclerView.Adapter<LefterAdapter.ViewHolder> {
    private Context context;
    private List<String> bigSortList;
    private RecyclerView recyclerView;
    private LeftListener listener;
    private int selectedPosition;

    public LefterAdapter(Context context, List<String> bigSortList, RecyclerView recyclerView) {
        this.context = context;
        this.bigSortList = bigSortList;
        this.recyclerView = recyclerView;
    }

    /**
     * 获取被选中的位置，将选中项移动到中间，并刷新
     *
     * @param selectedPosition
     */
    public void getSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        moveToMiddle(selectedPosition);
        notifyDataSetChanged();
    }

    /**
     * 获取listener,将listener传入ViewHolder中
     *
     * @param listener
     */
    public void setItemClickListener(LeftListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_left, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(bigSortList.get(position));
        if (position == selectedPosition) {
            holder.tvName.setBackgroundResource(R.color.color_107);
            holder.view.setVisibility(View.VISIBLE);
            holder.tvName.setTextColor(context.getResources().getColor(R.color.color_002));
        } else {
            holder.view.setVisibility(View.GONE);
            holder.tvName.setBackgroundResource(R.color.color_109);
            holder.tvName.setTextColor(context.getResources().getColor(R.color.color_100));
        }
    }

    /**
     * 将选中项移动到中间位置的方法
     *
     * @param position
     */
    public void moveToMiddle(int position) {
        int firstItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int lastItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        int middle = (firstItem + lastItem) / 2;
        int index = (position - middle) >= 0 ? position - middle : -(position - middle);
        if (index >= recyclerView.getChildCount()) {
            recyclerView.scrollToPosition(position);
        } else {
            if (position < middle) {
                recyclerView.scrollBy(0, -recyclerView.getChildAt(index).getTop());
            } else {
                recyclerView.scrollBy(0, recyclerView.getChildAt(index).getTop());
            }
        }
    }

    @Override
    public int getItemCount() {
        return bigSortList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * tvName显示大类名称，view是显示被选中的黄色标记
         */
        private TextView tvName;
        private View view;

        public ViewHolder(View itemView, final LeftListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_left);
            view = itemView.findViewById(R.id.view);
            itemView.setOnClickListener(new View.OnClickListener() {
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
    public interface LeftListener {

        void onItemClick(int position);
    }


}
