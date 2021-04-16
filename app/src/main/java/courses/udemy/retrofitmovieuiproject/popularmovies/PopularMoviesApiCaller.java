package courses.udemy.retrofitmovieuiproject.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface PopularMoviesApiCaller {//interface to connect to the base url

//    String popularMovieBaseUrl = "https://api.themoviedb.org/3/movie/";//base url for retrofit

        @GET("popular?api_key=6793495b5ebfe86b9" +
                "91a75dd7950ee9d&language=en-US")//api for retrofit
        Call<MostPopularMovies> getResults(@Query("page") int pageNumber);
}
