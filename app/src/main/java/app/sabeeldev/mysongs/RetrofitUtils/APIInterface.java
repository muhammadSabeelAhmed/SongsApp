package app.sabeeldev.mysongs.RetrofitUtils;

import app.sabeeldev.mysongs.Model.PlayList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("GetAppData?")
    Call<PlayList> getPlayListData(@Field("pid") String pid,
                                   @Field("password") String password);

}
