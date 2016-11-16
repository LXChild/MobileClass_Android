package com.lxchild.classTest.paperList.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxchild.bean.PaperBean;
import com.lxchild.mobileclass.R;

import java.util.ArrayList;

/**
 * Created by LXChild on 06/11/2016.
 */

public class PaperListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<PaperBean> itemList;

    public PaperListAdapter(Context cxt, ArrayList<PaperBean> itemList) {
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(cxt);
    }

    @Override
    public int getCount() {
        return itemList != null ? itemList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_paper_list_item, parent, false);
            holder = new ItemViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_paper_list);
            holder.title = (TextView) convertView.findViewById(R.id.tv_paper_list_title);
            holder.remark = (TextView) convertView.findViewById(R.id.tv_paper_list_remark);

            convertView.setTag(holder);
        } else {
            holder = (ItemViewHolder) convertView.getTag();
        }
        holder.icon.setImageResource(R.drawable.ic_board);
        holder.title.setText(itemList.get(position).getTitle());
        holder.remark.setText(itemList.get(position).getRemark());
        return convertView;
    }

    private class ItemViewHolder {
        ImageView icon;
        TextView title;
        TextView remark;
    }
}
