package techkids.vn.demoretrofitgen9.networks.json_models.top_songs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admins on 5/14/2017.
 */

public class FeedJSON {
    @SerializedName("feed")
    private MainTopSongsJSON listEntryJSON;

    public MainTopSongsJSON getListEntryJSON() {
        return listEntryJSON;
    }

    public void setListEntryJSON(MainTopSongsJSON listEntryJSON) {
        this.listEntryJSON = listEntryJSON;
    }
}
