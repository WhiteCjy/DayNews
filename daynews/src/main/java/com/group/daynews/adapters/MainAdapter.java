package com.group.daynews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.group.daynews.R;
import com.group.daynews.bean.User;

import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limxing on 16/7/23.
 * <p>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MasonryView> {

    private ArrayList<User> data = new ArrayList<>();

    public void getData(ArrayList<User> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public boolean isContanins(User user) {
        return data.contains(user);
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_fragment_listview_item, parent, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        User user = data.get(position);
        holder.user_fragment_name.setText(user.getUser_name());
        holder.user_fragment_title.setText(user.getUser_title());

        String url = user.getUser_head();//头像地址;
        x.image().bind(holder.user_fragment_head_img, url);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MasonryView extends RecyclerView.ViewHolder {
        @BindView(R.id.user_fragment_head_img)
        ImageView user_fragment_head_img;

        @BindView(R.id.user_fragment_title)
        TextView user_fragment_title;

        @BindView(R.id.user_fragment_name)
        TextView user_fragment_name;

        public MasonryView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}