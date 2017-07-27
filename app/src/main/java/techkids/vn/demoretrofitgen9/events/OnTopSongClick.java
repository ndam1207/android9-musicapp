package techkids.vn.demoretrofitgen9.events;

import techkids.vn.demoretrofitgen9.databases.TopSongModel;

/**
 * Created by Admins on 7/26/2017.
 */

public class OnTopSongClick {
    private TopSongModel topSongModel;

    public OnTopSongClick(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }
}
