package com.watayouxiang.demoshell;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private final List<ListBean> mData;

    ListAdapter(List<ListBean> date) {
        this.mData = date;
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
        if (i < getItemCount() - 1) {
            UIUtils.setMargin(holder.tv_name, new Rect(5, 10, 5, 0));
        } else {
            UIUtils.setMargin(holder.tv_name, new Rect(5, 10, 5, 10));
        }
        ListBean bean = mData.get(i);
        if (bean.listener != null) {
            setItemStyle(holder.tv_name);
        } else {
            setSectionStyle(holder.tv_name);
        }
        holder.tv_name.setText(String.valueOf(bean.name));
        holder.tv_name.setOnClickListener(bean.listener);
    }

    private void setSectionStyle(TextView tv_name) {
        tv_name.setGravity(Gravity.CENTER);
        tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv_name.setTextColor(Color.parseColor("#000000"));
        tv_name.setBackgroundColor(Color.TRANSPARENT);
        tv_name.setEnabled(false);
        UIUtils.setPadding(tv_name, 0);
    }

    private void setItemStyle(TextView tv_name) {
        tv_name.setGravity(Gravity.START);
        tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tv_name.setTextColor(Color.parseColor("#1A1A1A"));
        tv_name.setBackgroundColor(Color.parseColor("#AACCCCCC"));
        tv_name.setEnabled(true);
        UIUtils.setPadding(tv_name, 10);
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