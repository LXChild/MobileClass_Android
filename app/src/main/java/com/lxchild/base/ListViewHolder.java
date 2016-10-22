package com.lxchild.base;

import android.content.Context;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxchild.mobileclass.R;

public class ListViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    public ListViewHolder(Context context, ViewGroup parent, int layoutId, int position){
        mPosition = position;
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    public static ListViewHolder getViewHolder(Context context, ViewGroup parent,
                                           int layoutId, int position, View convertView){
        if(convertView == null){
            return new ListViewHolder(context, parent, layoutId, position);
        }else{
            ListViewHolder holder = (ListViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 为TextView设置text值
     * @param viewId
     * @param text
     * @return
     */
    public ListViewHolder setText(int viewId, String text){
        TextView tv= getView(viewId);
        tv.setText(text);
        return this;
    }
    /**
     * 为课程列表设置desc值
     * @param
     * */
    public ListViewHolder setDesc(int viewId, String text) {
        TextView tv= getView(viewId);
        tv.setText(text);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ListViewHolder setImageByUrl(final int viewId, String url) {
        SimpleDraweeView view = getView(viewId);
        view.setImageURI(Uri.parse(url));
        return this;
    }
    /**
     * 为ImageView设置src
     * @param viewId
     * @param resId
     * @return
     */
    public ListViewHolder setImageResource(int viewId,int resId){
        SimpleDraweeView iv = getView(viewId);
        Uri uri = Uri.parse("res://mipmap/" + R.mipmap.ic_launcher);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(iv.getController())
                .build();
        iv.setController(controller);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }
}
