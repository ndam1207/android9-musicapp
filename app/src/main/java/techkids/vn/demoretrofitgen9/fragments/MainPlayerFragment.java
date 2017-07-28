package techkids.vn.demoretrofitgen9.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import techkids.vn.demoretrofitgen9.R;
import techkids.vn.demoretrofitgen9.databases.MusicTypeModel;
import techkids.vn.demoretrofitgen9.databases.TopSongModel;
import techkids.vn.demoretrofitgen9.events.OnTopSongClick;

/**
 * Created by valky on 7/27/2017.
 */

public class MainPlayerFragment extends Fragment implements OnClickListener {
    public static final String TAG = MainPlayerFragment.class.toString();
    private TopSongModel topSongModel;


    @BindView(R.id.iv_background)
    ImageView ivBackground;
    @BindView(R.id.iv_song_image)
    ImageView ivSongImage;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sb_main_player)
    SeekBar sbMainPlayer;


    public MainPlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_player, container, false);

        setupUI(view);
        return view;
    }

    private void loadData() {
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                getActivity().findViewById(R.id.layout_mini_player).setVisibility(View.VISIBLE);
            }
        });

        tvTitle.setText(topSongModel.getSongName());
        tvAuthor.setText(topSongModel.getSingerName());
        Picasso.with(getContext()).load(topSongModel.getImage()).transform(new BlurTransformation(getContext())).into(ivBackground);
        Picasso.with(getContext()).load(topSongModel.getImage()).into(ivSongImage);

        sbMainPlayer.setPadding(0,0,0,0);
    }

    @Subscribe(sticky = true)
    public void onReceivedMusicType(OnTopSongClick onTopSongClick) {
        topSongModel = onTopSongClick.getTopSongModel();
    }

    @Override
    public void onClick(View v) {

    }


}
