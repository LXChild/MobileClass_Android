package com.lxchild.search.model;

import android.content.Context;

import com.lxchild.base.CommonAdapter;
import com.lxchild.base.ListViewHolder;
import com.lxchild.bean.ClassBean;
import com.lxchild.mobileclass.R;

import java.util.List;

/**
 * Created by mingjun on 16/7/18.
 */
public class ClassListRecyclerAdapter extends CommonAdapter<ClassBean> {

    public ClassListRecyclerAdapter(Context context, List<ClassBean> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ListViewHolder holder, ClassBean entity, int mPosition) {
        holder.setText(R.id.item_name, entity.getName());
        holder.setText(R.id.item_desc, entity.getTime() + " | " + entity.getRoom() + " | " + entity.getTeacher());
    }

    @Override
    public int getItemLayoutId() {
        return 0;
    }
}
