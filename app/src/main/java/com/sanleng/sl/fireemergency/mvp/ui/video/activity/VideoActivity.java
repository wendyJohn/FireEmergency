package com.sanleng.sl.fireemergency.mvp.ui.video.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout r_back;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.videos_activity;
    }

    @Override
    protected void initToolbar() {

    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(this);
        JCVideoPlayerStandard player = findViewById(R.id.player_list_video);
        boolean setUp = player.setUp("rtmp://rtmp01open.ys7.com/openlive/7eb4c7cd5a344bdd8ecb0ae6b67685a7", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");

        JCVideoPlayerStandard players = findViewById(R.id.player_list_videos);
        boolean setUps = players.setUp("rtmp://rtmp01open.ys7.com/openlive/a2783b451ed34d2098e9336dd61bf94d", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_back:
                finish();
                break;

        }
    }
}
