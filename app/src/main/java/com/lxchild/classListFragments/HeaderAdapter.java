package com.lxchild.classListFragments;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lxchild.mobileclass.R;

/**
 * Created by LXChild on 22/10/2016.
 */

public class HeaderAdapter extends PagerAdapter {

    private Context mContext;

    public HeaderAdapter(Context context) {
        this.mContext = context;
    }

    public int[] images = new int[]{//
            R.mipmap.image1, R.mipmap.image2, R.mipmap.image3,//
            R.mipmap.image4, R.mipmap.image5};

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
