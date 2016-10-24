package com.lxchild.base;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Author: implistarry
 * Date: 2016-07-01
 * Time: 10:54
 * Usage: 适配一切RecyclerView.ViewHolder
 */
public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        //缓存View应该一般不会超过10个
        this.mViews = new SparseArray<>(10);
    }

    public SparseArray<View> getAllView() {
        return mViews;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseRecyclerHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public BaseRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public BaseRecyclerHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public BaseRecyclerHolder setImageByUrl(final int viewId, String url) {

        SimpleDraweeView draweeView = getView(viewId);
        draweeView.setImageURI(Uri.parse(url));
        return this;
    }
}
