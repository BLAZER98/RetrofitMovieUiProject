package courses.udemy.retrofitmovieuiproject.popularmovies;

//POJO class for storing the data for Veritical recyclerview

import com.google.gson.annotations.SerializedName;

public class PopularMoviesData {

    @SerializedName("title")
    private String movieName;

    @SerializedName("release_date")
    private String movieReleaseDate;

    @SerializedName("poster_path")
    private String movieImagePath;

    public String getMovieName() {
        return movieName;
    }

//    public void setMovieName(String movieName) {
//        this.movieName = movieName;
//    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

//    public void setMovieReleaseDate(String movieReleaseDate) {
//        this.movieReleaseDate = movieReleaseDate;
//    }

    public String getMovieImagePath() {
        return movieImagePath;
    }

//    public void setMovieImagePath(String movieImagePath) {
//        this.movieImagePath = movieImagePath;
//    }
}
