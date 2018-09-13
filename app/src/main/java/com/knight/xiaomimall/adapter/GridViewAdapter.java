package com.knight.xiaomimall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knight.xm_mall.R;

import java.util.List;

/**
 * description: ${TODO}
 * autour: Knight
 * new date: 2018/9/13 on 15:11
 * e-mail: 37442216knight@gmail.com
 * update: 2018/9/13 on 15:11
 * version: v 1.0
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> smallSortList;

    public GridViewAdapter(Context context, List<String> smallSortList) {
        this.context = context;
        this.smallSortList = smallSortList;
    }

    @Override
    public int getCount() {
        return smallSortList.size();
    }

    @Override
    public Object getItem(int position) {
        return smallSortList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.tv_small);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(smallSortList.get(position));
        return convertView;
    }

    public static class ViewHolder {
        TextView textView;
    }
}
