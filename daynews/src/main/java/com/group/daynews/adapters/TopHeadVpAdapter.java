package com.group.daynews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.daynews.R;
import com.group.daynews.activitys.MsgWebActivity;
import com.group.daynews.bean.TopDataBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class TopHeadVpAdapter extends PagerAdapter {

    private ArrayList<TopDataBean> list;
    private Context context;
    private LayoutInflater inflater;
    private int perPosition = 0;

    public TopHeadVpAdapter(Context context, ArrayList<TopDataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    /**
     * @param bean
     * @return
     */
    public boolean isContains(TopDataBean bean) {
        return list.contains(bean);
    }


    /**
     * @param index
     * @param bean
     */
    public void addDataSingle(int index, TopDataBean bean) {
        list.add(index, bean);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        perPosition = position;
        View view = inflater.inflate(R.layout.top_fg_head_item, null);
        ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        final TopDataBean bean = list.get(position);
        x.image().bind(iv_image, bean.getImage(), new ImageOptions.Builder().
                setLoadingDrawableId(R.mipmap.image_preview_video).setUseMemCache(true).build());
        tv_title.setText(bean.getTitle());
        container.addView(view);
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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }

}
