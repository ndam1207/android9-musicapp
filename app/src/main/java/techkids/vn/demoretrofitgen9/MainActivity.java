package techkids.vn.demoretrofitgen9;

import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import techkids.vn.demoretrofitgen9.adapters.PagerAdapter;
import techkids.vn.demoretrofitgen9.databases.TopSongModel;
import techkids.vn.demoretrofitgen9.events.OnTopSongClick;
import techkids.vn.demoretrofitgen9.managers.MusicManager;
import techkids.vn.demoretrofitgen9.managers.ScreenManager;

public class MainActivity extends AppCompatActivity {
    private Toolbar tbMain;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @BindView(R.id.layout_mini_player)
    RelativeLayout rlMiniPlayer;
    @BindView(R.id.seek_bar_mini)
    SeekBar sbMiniPlayer;
    @BindView(R.id.iv_image_of_song_mini)
    ImageView ivImageSongMiniPlayer;
    @BindView(R.id.tv_song_name_mini)
    TextView tvSongMiniPlayer;
    @BindView(R.id.tv_singer_name_mini)
    TextView tvSingerMiniPlayer;
    @BindView(R.id.fb_play_mini)
    FloatingActionButton fbPlayMiniPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    private void setupUI() {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        tbMain = (Toolbar) findViewById(R.id.tb_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        tbMain.setTitle("Free Music");
        tbMain.setTitleTextColor(getResources().getColor(R.color.primary_text));

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_get_app_black_24dp));

        tabLayout.getTabAt(0).getIcon().setColorFilter(
                (getResources().getColor(R.color.icon_selected)), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(
                (getResources().getColor(R.color.icon_unselected)), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(
                (getResources().getColor(R.color.icon_unselected)), PorterDuff.Mode.SRC_IN);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter((getResources().getColor(R.color.icon_selected)),
                        PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter((getResources().getColor(R.color.icon_unselected)),
                        PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        sbMiniPlayer.setPadding(0, 0, 0, 0);
        fbPlayMiniPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicManager.playPause();
            }
        });
    }

    @Subscribe
    public void onClickTopSong(OnTopSongClick onTopSongClick) {
        TopSongModel topSongModel = onTopSongClick.getTopSongModel();
        rlMiniPlayer.setVisibility(View.VISIBLE);

        tvSongMiniPlayer.setText(topSongModel.getSongName());
        tvSingerMiniPlayer.setText(topSongModel.getSingerName());
        Picasso.with(this).load(topSongModel.getImage())
                .transform(new CropCircleTransformation())
                .into(ivImageSongMiniPlayer);

        MusicManager.loadSearchSong(topSongModel, this, sbMiniPlayer, fbPlayMiniPlayer);

    }
}
