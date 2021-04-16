package courses.udemy.retrofitmovieuiproject.popularmovies;

import com.google.gson.annotations.SerializedName;

//class to store array of POJO's and number of total pages of data webpages
public class MostPopularMovies {

    @SerializedName("results")
    private final PopularMoviesData[] results;

//    public int getTot_pages() {
//        return tot_pages;
//    }

//    public void setTot_pages(int tot_pages) {
//        this.tot_pages = tot_pages;
//    }

    @SerializedName("total_pages")
    private final int tot_pages;

    public MostPopularMovies(PopularMoviesData[] results, int tot_pages, int movieCount) {
        this.results = results;
        this.tot_pages = tot_pages;
        this.movieCount = movieCount;
    }

    public int getMovieCount() {
        return movieCount;
    }

//    public void setMovieCount(int movieCount) {
//        this.movieCount = movieCount;
//    }

    @SerializedName("total_results")
    private final int movieCount;

    public PopularMoviesData[] getResults() {
        return results;
    }

    //    public void setResults(PopularMoviesData[] results) {
//        this.results = results;
//    }

    public int getTot_pages() {
        return tot_pages;
    }
}
