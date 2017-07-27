package techkids.vn.demoretrofitgen9.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import techkids.vn.demoretrofitgen9.networks.json_models.top_songs.FeedJSON;

/**
 * Created by Admins on 5/14/2017.
 */

public interface GetTopSongsService {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id_music_type}/explicit=true/json")
    Call<FeedJSON> getTopSongs(@Path("id_music_type") String idMusicType);
}
