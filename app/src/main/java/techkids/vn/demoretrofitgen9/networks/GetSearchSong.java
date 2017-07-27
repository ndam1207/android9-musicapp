package techkids.vn.demoretrofitgen9.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import techkids.vn.demoretrofitgen9.networks.json_models.search_song.SearchMain;

/**
 * Created by Admins on 7/22/2017.
 */

public interface GetSearchSong {
    @GET("http://api.mp3.zing.vn/api/mobile/search/song")
    Call<SearchMain> getSearchSong(@Query("requestdata") String request);
}
