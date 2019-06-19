package com.watayouxiang.demoshell;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private final List<ListBean> mData = new ArrayList<>();

    /**
     * 覆盖数据
     *
     * @param data 数据
     */
    void setNewData(List<ListBean> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int i) {
        ListBean bean = mData.get(i);
        if (bean.name != null && bean.listener != null) {
            setItemStyle(holder.tv_name);
            holder.tv_name.setText(bean.name);
            holder.tv_name.setOnClickListener(bean.listener);
        } else if (bean.name != null) {
            setSectionStyle(holder.tv_name);
            holder.tv_name.setText(bean.name);
            holder.tv_name.setOnClickListener(null);
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    private void setSectionStyle(TextView tv_name) {
        tv_name.setGravity(Gravity.CENTER);
        tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv_name.setTextColor(Color.parseColor("#FFFFFF"));
        tv_name.setBackgroundColor(Color.parseColor("#AA0000FF"));
        tv_name.setEnabled(false);
    }

    private void setItemStyle(TextView tv_name) {
        tv_name.setGravity(Gravity.START);
        tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tv_name.setTextColor(Color.parseColor("#1A1A1A"));
        tv_name.setBackgroundColor(Color.parseColor("#AACCCCCC"));
        tv_name.setEnabled(true);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        final TextView tv_name;

        ListHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
        }
    }
}