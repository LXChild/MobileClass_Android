package com.lxchild.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by LXChild on 22/10/2016.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mDatas;

    private int mPosition;

    public CommonAdapter(Context context, List<T> datas){
        mContext=context;
        mDatas=datas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListViewHolder holder= ListViewHolder.getViewHolder(
                mContext,
                parent,
                getItemLayoutId(),
                position,
                convertView);
        mPosition = position;
        convert(holder, getItem(position), position);

        return holder.getConvertView();
    }

    /**
     * 为每个item上面的控件填充数据
     * @param holder
     * @param t
     */
    public abstract void convert(ListViewHolder holder,T t,int mPosition);

    public abstract int getItemLayoutId();


    public void setNewData(List<T> data) {
        this.mDatas = data;
//        if(this.mRequestLoadMoreListener != null) {
//            this.mNextLoadEnable = true;
//            this.mFooterView = null;
//        }
//
//        this.mLastPosition = -1;
        this.notifyDataSetChanged();
    }

    public int getPosition(){
        return mPosition;
    }
}
