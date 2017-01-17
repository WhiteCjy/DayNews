package com.group.daynews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.daynews.R;
import com.group.daynews.activitys.ChannelChildActivity;
import com.group.daynews.bean.SideChannelBean;

import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class SideChannelLvAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SideChannelBean> list;
    private LayoutInflater inflater;

    public SideChannelLvAdapter(Context context, ArrayList<SideChannelBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 添加单条数据
     *
     * @param bean
     */
    public void addDataSingle(int index, SideChannelBean bean) {
        list.add(index, bean);
        notifyDataSetChanged();
    }


    public boolean isContains(SideChannelBean bean) {
        return list.contains(bean);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.sidechannel_fg_lv_item, viewGroup, false);
            holder = new ViewHolder();
            ButterKnife.bind(holder, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final SideChannelBean bean = list.get(i);

        x.image().bind(holder.iv_thumbnail, bean.getThumbnail());
        holder.tv_name.setText(bean.getName());
        holder.tv_summary.setText(bean.getSummary());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChannelChildActivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("name", bean.getName());
                context.startActivity(intent);
            }
        });

        return view;
    }

    class ViewHolder {
        @BindView(R.id.iv_thumbnail)
        ImageView iv_thumbnail;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_summary)
        TextView tv_summary;
    }

}
