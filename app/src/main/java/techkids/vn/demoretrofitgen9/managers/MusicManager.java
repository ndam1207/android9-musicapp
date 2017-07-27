package techkids.vn.demoretrofitgen9.managers;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.vn.demoretrofitgen9.R;
import techkids.vn.demoretrofitgen9.databases.TopSongModel;
import techkids.vn.demoretrofitgen9.networks.GetSearchSong;
import techkids.vn.demoretrofitgen9.networks.RetrofitFactory;
import techkids.vn.demoretrofitgen9.networks.json_models.search_song.DocObject;
import techkids.vn.demoretrofitgen9.networks.json_models.search_song.SearchMain;
import techkids.vn.demoretrofitgen9.utils.FuzzyMatch;

/**
 * Created by Admins on 7/22/2017.
 */

public class MusicManager {
    private static final String TAG = "MusicManager";
    private static HybridMediaPlayer hybridMediaPlayer;
    private static SeekBar sbMiniPlayer;
    private static FloatingActionButton fbMini;

    public static void loadSearchSong(final TopSongModel topSongModel, final Context context,
                                      SeekBar sbMiniPlayer, FloatingActionButton fbMini) {
        MusicManager.sbMiniPlayer = sbMiniPlayer;
        MusicManager.fbMini = fbMini;
        GetSearchSong getSearchSong = RetrofitFactory.getInstance()
                .create(GetSearchSong.class);

        getSearchSong.getSearchSong("{\"q\":\""
                + topSongModel.getSongName() + " " + topSongModel.getSingerName()
                +"\", \"sort\":\"hot\", \"start\":\"0\", \"length\":\"10\"}")
                .enqueue(new Callback<SearchMain>() {
                    @Override
                    public void onResponse(Call<SearchMain> call, Response<SearchMain> response) {
                        if (response.body().getDocs().size() > 0) {
                            //1. list ratio
                            List<Integer> ratioList = new ArrayList<>();
                            for (DocObject docObject : response.body().getDocs()) {
                                int ratio = FuzzyMatch.getRatio(topSongModel.getSongName() + " " + topSongModel.getSingerName(),
                                        docObject.getTitle() + " " + docObject.getArtist(), false);
                                ratioList.add(ratio);
                            }
                            //2. get max
                            for (int i = 0; i < ratioList.size(); i++ ) {
                                if (Collections.max(ratioList) == ratioList.get(i)) {
                                    String linkSource = response.body().getDocs().get(i)
                                            .getSource().getLinkSource();
                                    topSongModel.setLinkSource(linkSource);
                                    setupMusic(topSongModel, context);
                                }
                            }
                        } else {
                            Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchMain> call, Throwable t) {
                        Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public static void setupMusic(TopSongModel topSongModel, Context context) {
        if (hybridMediaPlayer != null) {
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getLinkSource());

        hybridMediaPlayer.prepare();
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybrid) {
                hybridMediaPlayer.play();
                updateSongRealtime(MusicManager.sbMiniPlayer, MusicManager.fbMini);
            }
        });
    }

    public static void playPause() {
        if (hybridMediaPlayer.isPlaying()) {
            hybridMediaPlayer.pause();
        } else {
            hybridMediaPlayer.play();
        }
    }

    public static void updateSongRealtime(final SeekBar sbMiniPlayer,
                                          final FloatingActionButton fbMini) {

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sbMiniPlayer.setMax(hybridMediaPlayer.getDuration());
                sbMiniPlayer.setProgress(hybridMediaPlayer.getCurrentPosition());

                if (hybridMediaPlayer.isPlaying()) {
                    fbMini.setImageResource(R.drawable.exo_controls_pause);
                } else {
                    fbMini.setImageResource(R.drawable.exo_controls_play);
                }

                handler.postDelayed(this, 100);
            }
        };
        runnable.run();

        final int[] currentPosition = new int[1];
        sbMiniPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentPosition[0] = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hybridMediaPlayer.seekTo(currentPosition[0]);
            }
        });
    }
}
