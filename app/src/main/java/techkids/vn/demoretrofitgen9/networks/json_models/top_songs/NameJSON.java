package techkids.vn.demoretrofitgen9.networks.json_models.top_songs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admins on 5/14/2017.
 */

public class NameJSON {
    @SerializedName("label")
    private String labelName;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
