package com.lxchild.classListFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lxchild.mobileclass.R;
import com.lxchild.utils.GenerateColorUtils;

import java.util.ArrayList;
import java.util.List;

public class ContentList_2_Fragment extends HeaderViewPagerFragment {

    private RecyclerView mRecyclerView;

    public static ContentList_2_Fragment newInstance() {
        return new ContentList_2_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new RecyclerAdapter());
        return view;
    }

    @Override
    public View getScrollableView() {
        return mRecyclerView;
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.SimpleViewHolder> {

        private List<String> strings;

        public RecyclerAdapter() {
            strings = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                strings.add("条目" + i);
            }
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleViewHolder(View.inflate(getActivity(), android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        public class SimpleViewHolder extends RecyclerView.ViewHolder {
            TextView itemView;
            int position;

            public SimpleViewHolder(View itemView) {
                super(itemView);
                this.itemView = (TextView) itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "点击RecycleView item" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void bindData(int position) {
                itemView.setGravity(Gravity.CENTER);
                itemView.setTextColor(Color.WHITE);
                ViewGroup.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                itemView.setLayoutParams(params);
                itemView.setText(strings.get(position));
                itemView.setBackgroundColor(GenerateColorUtils.generateBeautifulColor());
            }
        }
    }
}
