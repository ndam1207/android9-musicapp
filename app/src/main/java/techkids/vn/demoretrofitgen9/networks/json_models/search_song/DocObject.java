package techkids.vn.demoretrofitgen9.networks.json_models.search_song;

/**
 * Created by Admins on 7/22/2017.
 */

public class DocObject {
    private String title;
    private String artist;
    private SourceObject source;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public SourceObject getSource() {
        return source;
    }

    public void setSource(SourceObject source) {
        this.source = source;
    }
}
