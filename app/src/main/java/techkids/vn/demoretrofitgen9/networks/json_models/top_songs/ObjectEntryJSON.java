package techkids.vn.demoretrofitgen9.networks.json_models.top_songs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admins on 5/14/2017.
 */

public class ObjectEntryJSON {
    @SerializedName("im:name")
    private NameJSON jsonName;

    @SerializedName("im:image")
    private List<ImageJSON> jsonImageList;

    @SerializedName("im:artist")
    private ArtistJSON jsonArtist;

    public NameJSON getJsonName() {
        return jsonName;
    }

    public void setJsonName(NameJSON jsonName) {
        this.jsonName = jsonName;
    }

    public List<ImageJSON> getJsonImageList() {
        return jsonImageList;
    }

    public void setJsonImageList(List<ImageJSON> jsonImageList) {
        this.jsonImageList = jsonImageList;
    }

    public ArtistJSON getJsonArtist() {
        return jsonArtist;
    }

    public void setJsonArtist(ArtistJSON jsonArtist) {
        this.jsonArtist = jsonArtist;
    }
}
