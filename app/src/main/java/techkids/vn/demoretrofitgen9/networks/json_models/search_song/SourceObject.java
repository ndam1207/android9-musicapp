package techkids.vn.demoretrofitgen9.networks.json_models.search_song;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admins on 7/22/2017.
 */

public class SourceObject {
    @SerializedName("128")
    private String linkSource;

    public String getLinkSource() {
        return linkSource;
    }

    public void setLinkSource(String linkSource) {
        this.linkSource = linkSource;
    }
}
