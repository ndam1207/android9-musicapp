package techkids.vn.demoretrofitgen9.networks.json_models.top_songs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admins on 5/14/2017.
 */

public class ImageJSON {
    @SerializedName("label")
    private String jsonLabelImage;

    public String getJsonLabelImage() {
        return jsonLabelImage;
    }

    public void setJsonLabelImage(String jsonLabelImage) {
        this.jsonLabelImage = jsonLabelImage;
    }
}
