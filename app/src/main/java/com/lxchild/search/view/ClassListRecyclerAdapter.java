package com.lxchild.search.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lxchild.base.BaseRecyclerAdapter;
import com.lxchild.base.BaseRecyclerHolder;
import com.lxchild.bean.ClassBean;
import com.lxchild.mobileclass.R;

import java.util.List;


/**
 * Created by mingjun on 16/7/18.
 */
public class ClassListRecyclerAdapter extends BaseRecyclerAdapter<ClassBean> {

    public ClassListRecyclerAdapter(RecyclerView recyclerView, List<ClassBean> datas, int itemLayoutId) {
        super(recyclerView, datas, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, ClassBean item, int position, boolean isScrolling) {
        holder.setText(R.id.tv_name, item.getName());
        holder.setText(R.id.tv_desc, item.getTime() + " | " + item.getRoom() + " | " + item.getTeacher());
        // TODO set class icon
        holder.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO show class detail
                // onViewClickLisenter.onChanageViewClick(OnViewClickLisenter.TYPE_DELETE, item, position, holder.getView(R.id.delete));
            }
        });
    }

    public void setNewData(List<ClassBean> datas) {
        this.realDatas = datas;
    }
}
