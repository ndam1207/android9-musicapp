package techkids.vn.demoretrofitgen9.networks.json_models.top_songs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admins on 5/14/2017.
 */

public class MainTopSongsJSON {
    @SerializedName("entry")
    private List<ObjectEntryJSON> entryJSONList;

    public List<ObjectEntryJSON> getObjectEntryJSONs() {
        return entryJSONList;
    }

    public void setObjectEntryJSONs(List<ObjectEntryJSON> entryJSONList) {
        this.entryJSONList = entryJSONList;
    }
}
