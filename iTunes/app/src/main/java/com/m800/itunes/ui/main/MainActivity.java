package com.m800.itunes.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m800.itunes.R;
import com.m800.itunes.constants.Field;
import com.m800.itunes.dataModel.RecyclerBaseItem;
import com.m800.itunes.dataModel.Song;
import com.m800.itunes.interfaces.OnAPIListener;
import com.m800.itunes.module.apiParams.InParamsTopSongs;
import com.m800.itunes.ui.activity.SongDetailActivity;
import com.m800.itunes.utils.APIUtils;
import com.m800.itunes.utils.CommonUtils;
import com.m800.itunes.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int HIDE_PROGRESSBAR = 0;
    private static final int SHOW_PROGRESSBAR = 1;


    @IntDef({HIDE_PROGRESSBAR, SHOW_PROGRESSBAR})
    @Retention(RetentionPolicy.SOURCE)
    private @interface ProgressBarStatus {
    }

    private Activity mActivity;
    private UIHandler mHandler;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private RecyclerView mSongRecyclerView;
    private List<RecyclerBaseItem> mSongList;
    private SongRecyclerViewAdapter mSongRecyclerViewAdapter;
    private int mSongsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        findViews();
        setViews();
        getInitData();
    }

    private void getInitData() {
        if (CommonUtils.isOnline(mActivity)) {
            pullTopSongs(".");
        } else {
            Toast.makeText(mActivity, R.string.occur_problem, Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        mActivity = this;
        mHandler = new UIHandler((MainActivity) mActivity);
        mSongList = new ArrayList<>();
        mSongsCount = 0;
    }

    private void findViews() {
        mSongRecyclerView = findViewById(R.id.songRecyclerView);
        mSearchView = findViewById(R.id.searchView);
        mProgressBar = findViewById(R.id.progressbar);
    }

    private void setViews() {
        mSongRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mSongRecyclerView.setHasFixedSize(true);
        mSongRecyclerView.setItemViewCacheSize(20);
        mSongRecyclerView.setDrawingCacheEnabled(true);
        mSongRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pullTopSongs(query.replace(" ", "+"));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void setAdapter() {
        if (mSongRecyclerViewAdapter == null) {
            mSongRecyclerViewAdapter = new SongRecyclerViewAdapter(mActivity, mSongList) {
                @Override
                void onSongItemClick(RecyclerBaseItem song) {
                    Intent intent = new Intent(mActivity, SongDetailActivity.class);
                    intent.putExtra(SongDetailActivity.BUNDLE_SONG, song);
                    startActivity(intent);
                }
            };

            mSongRecyclerView.setAdapter(mSongRecyclerViewAdapter);
        } else {
            mSongRecyclerViewAdapter.setList(mSongList);
        }
    }

    private void sendHandlerMessage(@ProgressBarStatus final int msg) {
        new Thread(new Runnable() {
            public void run() {
                Message message = new Message();
                message.what = msg;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    private void pullTopSongs(String keyword) {
        InParamsTopSongs ip = new InParamsTopSongs(keyword);
        APIUtils.request(mActivity, ip, new OnAPIListener() {
            @Override
            public void onError(int statusCode, String error) {
                sendHandlerMessage(HIDE_PROGRESSBAR);
            }

            @Override
            public void onPreExecute() {
                sendHandlerMessage(SHOW_PROGRESSBAR);
            }

            @Override
            public void onResponse(String response) {
                sendHandlerMessage(HIDE_PROGRESSBAR);

                if (response != null) {
                    try {
                        JSONObject object = new JSONObject(response);
                        mSongsCount = object.getInt(Field.RESULT_COUNT);
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mActivity, String.format(getString(R.string.count), mSongsCount), Toast.LENGTH_SHORT).show();
                            }
                        });

                        JSONArray songJsonArray = object.getJSONArray(Field.RESULTS);
                        Type listType = new TypeToken<List<Song>>() {
                        }.getType();
                        mSongList = new Gson().fromJson(songJsonArray.toString(), listType);
                        setAdapter();
                    } catch (JSONException e) {
                        LogUtils.e(TAG, e.toString());
                    }
                } else {
                    LogUtils.d(TAG, " onResponse Exception:");
                }
            }
        });
    }

    public static class UIHandler extends Handler {

        private UIHandler(MainActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        private WeakReference<MainActivity> mActivityWeakReference;

        @Override
        public String getMessageName(Message message) {
            Activity activity = mActivityWeakReference.get();
            switch (message.what) {
                case HIDE_PROGRESSBAR:
                    Log.d(TAG, "hide");
                    ((MainActivity) activity).mProgressBar.setVisibility(View.INVISIBLE);
                    break;
                case SHOW_PROGRESSBAR:
                    Log.d(TAG, "show");
                    ((MainActivity) activity).mProgressBar.setVisibility(View.VISIBLE);
                    break;
            }

            return super.getMessageName(message);
        }
    }

}
