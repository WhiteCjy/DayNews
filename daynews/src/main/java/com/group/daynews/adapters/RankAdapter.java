package com.group.daynews.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.daynews.R;
import com.group.daynews.bean.RankDataBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CJY on 2016/12/29.
 */

public class RankAdapter extends BaseAdapter {
    private ArrayList<RankDataBean> datas = new ArrayList<>();

    public void setDatas(ArrayList<RankDataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public boolean isContainer(RankDataBean dataBean) {
        return datas.contains(dataBean);
    }

    public void setSingleData(int index, RankDataBean dataBean) {
        datas.add(0, dataBean);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public RankDataBean getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.rank_read_lv_item, viewGroup, false
            );
            ButterKnife.bind(viewHolder, view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        RankDataBean bean = datas.get(i);
        viewHolder.tv_title.setText(bean.getTitle());
        viewHolder.tv_author_name.setText(bean.getAuthor_name());
        viewHolder.rank_selection_tv.setText(bean.getSection_name());
        ImageOptions imageOptions = new ImageOptions.Builder().setUseMemCache(true)
                .setLoadingDrawableId(R.mipmap.image_preview_video)
                .setImageScaleType(ImageView.ScaleType.FIT_XY).build();
        x.image().bind(viewHolder.iv_thumbnail, bean.getThumbnail(), imageOptions);
        return view;
    }

    class ViewHolder {
        @BindView(R.id.iv_thumbnail)
        ImageView iv_thumbnail;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_author_name)
        TextView tv_author_name;
        @BindView(R.id.rank_selection)
        TextView rank_selection_tv;
    }
}
