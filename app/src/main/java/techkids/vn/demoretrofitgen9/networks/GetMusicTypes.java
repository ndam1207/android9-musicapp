package techkids.vn.demoretrofitgen9.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import techkids.vn.demoretrofitgen9.networks.json_models.music_types.AllMusicTypesJSONModel;

/**
 * Created by Admins on 7/15/2017.
 */

public interface GetMusicTypes {
    @GET("api")
    Call<AllMusicTypesJSONModel> getMusicTypes();
}
