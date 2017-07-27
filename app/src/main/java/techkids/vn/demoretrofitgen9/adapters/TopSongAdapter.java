package techkids.vn.demoretrofitgen9.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import techkids.vn.demoretrofitgen9.R;
import techkids.vn.demoretrofitgen9.databases.TopSongModel;

/**
 * Created by Admins on 7/22/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder> {
    private Context context;
    private List<TopSongModel> topSongModels;
    private View.OnClickListener onClickListener;

    public TopSongAdapter(Context context, List<TopSongModel> topSongModels) {
        this.context = context;
        this.topSongModels = topSongModels;
    }

    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_top_song, parent, false);
        view.setOnClickListener(onClickListener);
        return new TopSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setData(topSongModels.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModels.size();
    }

    public class TopSongViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemMusic;
        TextView tvSong;
        TextView tvSinger;

        View view;

        public TopSongViewHolder(View itemView) {
            super(itemView);
            ivItemMusic = (ImageView) itemView.findViewById(R.id.iv_top_song);
            tvSong = (TextView) itemView.findViewById(R.id.tv_song_name);
            tvSinger = (TextView) itemView.findViewById(R.id.tv_singer);
            view = itemView;
        }

        public void setData(TopSongModel topSongModel) {
            if (topSongModel != null) {
                tvSinger.setText(topSongModel.getSingerName());
                tvSong.setText(topSongModel.getSongName());
                Picasso.with(context).load(topSongModel.getImage())
                        .transform(new CropCircleTransformation())
                        .into(ivItemMusic);

                view.setTag(topSongModel);
            }
        }
    }
}
