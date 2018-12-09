package com.m800.itunes.ui.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m800.itunes.R;
import com.m800.itunes.adapter.RecyclerBaseAdapter;
import com.m800.itunes.dataModel.RecyclerBaseItem;
import com.m800.itunes.dataModel.Song;
import com.m800.itunes.utils.CommonUtils;
import com.m800.itunes.utils.ImageUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class SongRecyclerViewAdapter extends RecyclerBaseAdapter<RecyclerBaseItem> {
    public static final int LAYOUT_TYPE_SONG = 1;

    abstract void onSongItemClick(RecyclerBaseItem song);

    SongRecyclerViewAdapter(Context context, List<RecyclerBaseItem> songList) {
        super(context, songList);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return list != null ? list.get(position).getLayoutType() : -1;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder ret;
        View view;
        int layoutResId;
        layoutResId = R.layout.recyclerview_item_song;
        view = layoutInflater.inflate(layoutResId, null);
        ret = new SongViewHolder(view);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SongViewHolder) holder).setItem((Song) getItem(position));
    }


    private class SongViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCollectionCoverIv;
        private TextView mCollectionNameTv;
        private TextView mTrackNameTv;
        private Song mSong;


        private SongViewHolder(View itemView) {
            super(itemView);
            bindViews(itemView);
            bindEvents();
        }

        private void bindViews(View view) {
            mCollectionCoverIv = view.findViewById(R.id.albumCoverIv);
            mCollectionNameTv = view.findViewById(R.id.collectionNameTv);
            mTrackNameTv = view.findViewById(R.id.trackNameTv);
        }

        private void bindEvents() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSongItemClick(mSong);
                }
            });
        }

        private void setItem(Song song) {
            this.mSong = song;
            if (mSong != null) {
                int picSize = CommonUtils.dp2Px(ctx, 100);
                int radius = CommonUtils.dp2Px(ctx, 5);
                if (!CommonUtils.isAbsEmpty(mSong.getArtworkUrl100())) {
                    Picasso.get()
                            .load(mSong.getArtworkUrl100())
                            .transform(new ImageUtils.RoundedCornersTransform(radius))
                            .noPlaceholder()
                            .resize(picSize, picSize)
                            .centerCrop()
                            .into(mCollectionCoverIv);
                }
                mCollectionNameTv.setText(song.getCollectName());
                mTrackNameTv.setText(song.getTrackName());
            }
        }

    }
}
