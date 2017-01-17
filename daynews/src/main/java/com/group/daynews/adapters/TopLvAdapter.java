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
import com.group.daynews.activitys.MsgWebActivity;
import com.group.daynews.bean.TopDataBean;
import com.group.daynews.precenter.TopPrecenter;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class TopLvAdapter extends BaseAdapter {

    private ArrayList<TopDataBean> list;
    private Context context;
    private LayoutInflater inflater;
    private int type;


    public TopLvAdapter(Context context, ArrayList<TopDataBean> list, int type) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.type = type;
    }

    /**
     * 添加单条数据
     *
     * @param index
     * @param bean
     */
    public void addDataSingle(int index, TopDataBean bean) {
        list.add(index, bean);
        notifyDataSetChanged();
    }

    /**
     * 判断适配器中是否已经包含该条数据
     *
     * @param bean
     */
    public boolean isContains(TopDataBean bean) {
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

        ContantHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.top_fg_lv_item, viewGroup, false);
            holder = new ContantHolder();
            ButterKnife.bind(holder, view);
            view.setTag(holder);
        } else {
            holder = (ContantHolder) view.getTag();
        }

        final TopDataBean bean = list.get(i);
        holder.tv_title.setText(bean.getTitle());
        holder.tv_author_name.setText(bean.getAuthor_name());
        ImageOptions options = new ImageOptions.Builder().setUseMemCache(true)
                .setCircular(true)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .build();
        x.image().bind(holder.iv_author_avatar, bean.getAuthor_avatar(), options);
        options = new ImageOptions.Builder().setUseMemCache(true).setLoadingDrawableId(R.mipmap.image_preview_video)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .build();
        x.image().bind(holder.iv_thumbnail, bean.getThumbnail(), options);
        String section_name = bean.getSection_name();
        if (section_name.equals("")) {
            holder.tv_section_name.setText("推荐");
        } else {
            holder.tv_section_name.setText(section_name);
        }
        if (type == TopPrecenter.TOP_TYPE) {
            holder.vi.setVisibility(View.GONE);
            holder.iv_author_avatar.setVisibility(View.VISIBLE);
        } else if (type == TopPrecenter.CHANNEL_TYPE) {
            holder.vi.setVisibility(View.VISIBLE);
            holder.iv_author_avatar.setVisibility(View.GONE);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MsgWebActivity.class);
                intent.putExtra("comment_count", bean.getComment_count() + "");
                intent.putExtra("vote_count", bean.getVote_count() + "");
                intent.putExtra("share_url", bean.getShare_url());
                context.startActivity(intent);
            }
        });

        return view;
    }


    class ContantHolder {

        @BindView(R.id.iv_thumbnail)
        ImageView iv_thumbnail;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.iv_author_avatar)
        ImageView iv_author_avatar;
        @BindView(R.id.tv_author_name)
        TextView tv_author_name;
        @BindView(R.id.tv_section_name)
        TextView tv_section_name;
        @BindView(R.id.vi)
        View vi;
    }


}
