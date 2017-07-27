package techkids.vn.demoretrofitgen9.events;

import techkids.vn.demoretrofitgen9.databases.MusicTypeModel;

/**
 * Created by Admins on 7/22/2017.
 */

public class OnClickMusicType {
    private MusicTypeModel musicTypeModel;

    public OnClickMusicType(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {
        return musicTypeModel;
    }

    public void setMusicTypeModel(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }
}
