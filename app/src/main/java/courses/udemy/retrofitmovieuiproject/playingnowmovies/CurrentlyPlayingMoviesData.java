package courses.udemy.retrofitmovieuiproject.playingnowmovies;
//POJO class for storing the data for horizontal recyclerview

import com.google.gson.annotations.SerializedName;

public class CurrentlyPlayingMoviesData {

    @SerializedName("poster_path")
    private final String posterImagePath;

    public CurrentlyPlayingMoviesData(String posterImagePath) {
        this.posterImagePath = posterImagePath;
    }

    public String getPosterImagePath() {
        return posterImagePath;
    }

//    public void setPosterImage(String posterImagePath) {
//        this.posterImagePath = posterImagePath;
//    }
}
