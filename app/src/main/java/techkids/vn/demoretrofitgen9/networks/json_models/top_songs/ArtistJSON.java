package techkids.vn.demoretrofitgen9.networks.json_models.top_songs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admins on 5/14/2017.
 */

public class ArtistJSON {
    @SerializedName("label")
    private String jsonNameArtist;

    public String getJsonNameArtist() {
        return jsonNameArtist;
    }

    public void setJsonNameArtist(String jsonNameArtist) {
        this.jsonNameArtist = jsonNameArtist;
    }
}
