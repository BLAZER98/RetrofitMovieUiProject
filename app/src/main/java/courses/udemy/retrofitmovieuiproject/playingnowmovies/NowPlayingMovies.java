package courses.udemy.retrofitmovieuiproject.playingnowmovies;

import com.google.gson.annotations.SerializedName;

//class to store array of POJO's
public class NowPlayingMovies {

    @SerializedName("results")
    private final CurrentlyPlayingMoviesData[] results;

    public NowPlayingMovies(CurrentlyPlayingMoviesData[] results) {
        this.results = results;
    }

    public CurrentlyPlayingMoviesData[] getResults() {
        return results;
    }

//    public void setResults(CurrentlyPlayingMoviesData[] results) {
//        this.results = results;
//    }
}
