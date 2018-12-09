package com.m800.itunes.ui.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.m800.itunes.R;
import com.m800.itunes.dataModel.Song;
import com.m800.itunes.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

public class SongDetailActivity extends AppCompatActivity {
    public static final String BUNDLE_SONG = "song";

    private Song mSong;
    private Activity mActivity;
    private ImageView mIvCover;
    private TextView mTvSongInfo;
    private SimpleExoPlayer mSimpleExoPlayer;
    private PlayerControlView mPlayerControlView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        init();
        bindViews();
        getIntentData();
        initPlayer();
        setToolBar();
        bindEvents();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return super.onNavigateUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSimpleExoPlayer.release();
    }

    private void init() {
        mActivity = this;
        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(mActivity);
    }

    private void bindViews() {
        mIvCover = findViewById(R.id.ivCover);
        mPlayerControlView = findViewById(R.id.playerControllerView);
        mTvSongInfo = findViewById(R.id.tvSongInfo);
    }

    private void bindEvents() {
        if (mSong != null) {
            int picSize = CommonUtils.dp2Px(mActivity, 320);
            if (!CommonUtils.isAbsEmpty(mSong.getArtworkUrl100())) {
                Picasso.get()
                        .load(mSong.getArtworkUrl100())
                        .noPlaceholder()
                        .resize(picSize, picSize)
                        .centerCrop()
                        .into(mIvCover);
            }


            String info = getString(R.string.collection_name) + "\n" + mSong.getCollectName() + "\n\n" +
                    getString(R.string.track_name) + "\n" + mSong.getTrackName() + "\n\n" +
                    getString(R.string.singer) + "\n" + mSong.getArtistName() + "\n\n";

            mTvSongInfo.setText(info);
        }
    }

    private void initPlayer() {

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mActivity,
                Util.getUserAgent(mActivity, getString(R.string.app_name)));
        MediaSource m4aSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(mSong.getPreviewUrl()));
        mSimpleExoPlayer.prepare(m4aSource);

        mSimpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });


        mPlayerControlView.setPlayer(mSimpleExoPlayer);
        mPlayerControlView.show();
    }


    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar.getNavigationIcon() != null) {
            DrawableCompat.setTint(toolbar.getNavigationIcon(), getResources().getColor(R.color.color_ffffff));

            TextView tvTitle = new TextView(this);
            tvTitle.setText(mSong.getTrackName());
            tvTitle.setTextSize(18);
            tvTitle.setTextColor(getResources().getColor(R.color.color_ffffff));


            Toolbar.LayoutParams lpTitle = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpTitle.gravity = Gravity.CENTER_HORIZONTAL;
            toolbar.addView(tvTitle, lpTitle);
            setSupportActionBar(toolbar);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }


    private void getIntentData() {
        mSong = (Song) getIntent().getSerializableExtra(BUNDLE_SONG);
    }
}
