package courses.udemy.retrofitmovieuiproject.playingnowmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NowPlayingMoviesApiCaller {//interface to connect to the webpage url

        //query way
        @GET("now_playing?api_key=6793495b5ebfe86b991a75dd7950ee9d&language=en-US")//api for retrofit
        Call<NowPlayingMovies> getResults(@Query("page")int page);

}