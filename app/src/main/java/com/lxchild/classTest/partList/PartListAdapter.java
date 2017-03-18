package com.lxchild.classTest.partList;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lxchild.base.BaseRecyclerAdapter;
import com.lxchild.base.BaseRecyclerHolder;
import com.lxchild.mobileclass.R;

import java.util.Collection;
import java.util.List;

/**
 * Created by LXChild on 19/11/2016.
 */

public class PartListAdapter extends BaseRecyclerAdapter {

    public PartListAdapter(RecyclerView recyclerView, Collection datas, int itemLayoutId) {
        super(recyclerView, datas, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, Object item, int position, boolean isScrolling) {
        holder.setText(R.id.tv_part_name, item.toString());
        // TODO set class icon
        holder.getView(R.id.tv_part_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO show class detail
                // onViewClickLisenter.onChanageViewClick(OnViewClickLisenter.TYPE_DELETE, item, position, holder.getView(R.id.delete));
            }
        });
    }

    public void setNewData(List datas) {
        this.realDatas = datas;
    }
}
