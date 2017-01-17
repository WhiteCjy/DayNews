package com.group.daynews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.daynews.R;
import com.group.daynews.bean.VideoDataBean;
import com.group.daynews.interfaces.SampleListener;
import com.group.daynews.utils.TimeUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CJY on 2016/12/27.
 */

public class VideoAdapter extends BaseAdapter {
    public static final String TAG = "VideoAdapter";
    private ArrayList<VideoDataBean> datas = new ArrayList<>();
    private Context context;
    private boolean isFullVideo;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void setVideoDataBeen(ArrayList<VideoDataBean> data) {
        datas.addAll(data);
        notifyDataSetChanged();
    }

    public boolean isContainer(VideoDataBean dataBean) {
        return datas.contains(dataBean);
    }

    public void setSingleVideoDataBeen(int index, VideoDataBean data) {
        datas.add(index, data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public VideoDataBean getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private VideoDataBean videoDataBeen;
    private int curPosition = -1;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item_layout, null);
            // ButterKnife.bind(viewHolder,view);
            viewHolder = new ViewHolder(view);
            viewHolder.video_iv = new ImageView(context);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        videoDataBeen = datas.get(i);
        viewHolder.video_title_tv.setText(videoDataBeen.getTitle());
        viewHolder.video_player_tv.setText(videoDataBeen.getPlay_count() + "     播放");
        viewHolder.video_comment_tv.setText(videoDataBeen.getComment_count());
        viewHolder.video_favor_tv.setText(videoDataBeen.getVote_count());
        ImageOptions imageOptions = new ImageOptions.Builder().setUseMemCache(true)
                .setLoadingDrawableId(R.mipmap.image_preview_video)
                .setImageScaleType(ImageView.ScaleType.FIT_XY).build();
        x.image().bind(viewHolder.video_iv, videoDataBeen.getImage(), imageOptions);
        if (viewHolder.video_iv.getParent() != null) {
            ViewGroup viewG = (ViewGroup) viewHolder.video_iv.getParent();
            viewG.removeView(viewHolder.video_iv);
        }
        viewHolder.video_vv.setThumbImageView(viewHolder.video_iv);

        //  viewHolder.video_vv.setBottomProgressBarDrawable(context.getDrawable(R.drawable.video_play_normal));
        viewHolder.video_playtime_tv.setText(TimeUtil.format2Time(Long.parseLong(videoDataBeen.getPlay_time()) * 1000));
        viewHolder.video_vv.setUp(videoDataBeen.getFile_url(), false, null, "标题");
        //增加title
        viewHolder.video_vv.getTitleTextView().setVisibility(View.GONE);

        //设置返回键
        viewHolder.video_vv.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
        viewHolder.video_vv.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(viewHolder.video_vv);
            }
        });
        viewHolder.video_vv.setRotateViewAuto(true);
        viewHolder.video_vv.setLockLand(true);
        viewHolder.video_vv.setPlayTag(TAG);
        viewHolder.video_vv.setShowFullAnimation(true);
        //循环
        //holder.gsyVideoPlayer.setLooping(true);
        viewHolder.video_vv.setNeedLockFull(true);

        viewHolder.video_vv.setPlayPosition(i);
        viewHolder.video_vv.setStandardVideoAllCallBack(sampleListener);
        return view;
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, false, true);
        isFullVideo = true;
    }

    class ViewHolder {
        //  @BindView(R.id.image_iv)
        ImageView video_iv;
        @BindView(R.id.video_vv)
        StandardGSYVideoPlayer video_vv;
        @BindView(R.id.video_title_tv)
        TextView video_title_tv;
        @BindView(R.id.video_player_tv)
        TextView video_player_tv;
        @BindView(R.id.video_favor_tv)
        TextView video_favor_tv;
        @BindView(R.id.video_comment_tv)
        TextView video_comment_tv;
        @BindView(R.id.video_share_iv)
        ImageView video_share_iv;
        //        @BindView(R.id.video_btn_iv)
//        ImageView video_btn_iv;
        @BindView(R.id.video_playtime_tv)
        TextView video_playtime_tv;

        public ViewHolder(View converview) {
            ButterKnife.bind(this, converview);
        }

    }

    //小窗口关闭被点击的时候回调处理回复页面
    SampleListener sampleListener = new SampleListener() {
        @Override
        public void onPrepared(String url, Object... objects) {
            super.onPrepared(url, objects);
            Debuger.printfLog("onPrepared");
        }

        @Override
        public void onQuitSmallWidget(String url, Object... objects) {
            super.onQuitSmallWidget(url, objects);
            Debuger.printfLog("onQuitSmallWidget");
        }

        @Override
        public void onClickBlankFullscreen(String url, Object... objects) {
            super.onClickBlankFullscreen(url, objects);
            Debuger.printfLog("onClickBlankFullscreen");
        }

        @Override
        public void onEnterFullscreen(String url, Object... objects) {
            super.onEnterFullscreen(url, objects);
            Debuger.printfLog("onEnterFullscreen");
        }
    };


}
