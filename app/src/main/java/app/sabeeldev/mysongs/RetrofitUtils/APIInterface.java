package app.sabeeldev.mysongs.RetrofitUtils;

import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.Model.Video;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @FormUrlEncoded
    @POST("GetAppData?")
    Call<PlayList> getPlayListData(@Field("pid") String pid,
                                   @Field("password") String password);

//    @Headers({
//            "x-rapidapi-key: e833a616b2mshee1f6fe52763456p17b9a0jsn2cc1159eb10a",
//            "x-rapidapi-host: youtube-video-info.p.rapidapi.com",
//            "useQueryString: true"
//    })
    @GET("video_formats?")
    Call<Video> getVideoDetails(@Query("video") String videoId,
                                @Header("x-rapidapi-key") String api_key,
                                @Header("x-rapidapi-host") String api_host,
                                @Header("useQueryString") String api_query);


}
