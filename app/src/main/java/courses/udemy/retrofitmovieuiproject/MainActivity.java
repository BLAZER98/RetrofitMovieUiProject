package courses.udemy.retrofitmovieuiproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import courses.udemy.retrofitmovieuiproject.playingnowmovies.CurrentlyPlayingMoviesData;
import courses.udemy.retrofitmovieuiproject.playingnowmovies.NowPlayingMovies;
import courses.udemy.retrofitmovieuiproject.playingnowmovies.NowPlayingMoviesApiCaller;
import courses.udemy.retrofitmovieuiproject.playingnowmovies.RVNowPlayingAdapter;
import courses.udemy.retrofitmovieuiproject.popularmovies.MostPopularMovies;
import courses.udemy.retrofitmovieuiproject.popularmovies.PopularMoviesApiCaller;
import courses.udemy.retrofitmovieuiproject.popularmovies.PopularMoviesData;
import courses.udemy.retrofitmovieuiproject.popularmovies.RVPopularMoviesAdapter;
import courses.udemy.retrofitmovieuiproject.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static courses.udemy.retrofitmovieuiproject.popularmovies.RVPopularMoviesAdapter.isMovieLoading;

public class MainActivity extends AppCompatActivity {

    private CurrentlyPlayingMoviesData[] currentlyPlayingMoviesData;
    private PopularMoviesData[] popularMoviesData;
    private RVNowPlayingAdapter rvNowPlayingAdapter;
    private RVPopularMoviesAdapter rvPopularMoviesAdapter;
    private List<String> nowPlayingPosterImagePaths;

    private List<String> popularMoviesImagePaths;
    private List<String> popularMoviesTitles;
    private List<String> popularMoviesReleaseDates;

    private RecyclerView nowPlayingRecyclerView, popularMoviesRecyclerView;
    private final String TAG = "nowPlaying";

    public static int totalPages = 1;
    public static int presentPage = 1;
    public static int movieCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarInitialization();

        init();

        nowPlayingPosterImagePaths = new ArrayList<>();
        popularMoviesImagePaths = new ArrayList<>();
        popularMoviesReleaseDates = new ArrayList<>();
        popularMoviesTitles = new ArrayList<>();

        nowPlayingMovieLoading();

        popularMoviePageLoading();

        popularMoviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (presentPage != totalPages && isMovieLoading) {

                    Log.d("count", rvPopularMoviesAdapter.getItemCount() + "");
                    popularMoviesPagingLoading();

                }
            }
        });

    }

    /*
     * methods for initialization of views
     * */
    private void init() {

        nowPlayingRecyclerView = findViewById(R.id.nowPlayingRecyclerView);
        nowPlayingRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        popularMoviesRecyclerView = findViewById(R.id.popularMoviesRecyclerView);
        popularMoviesRecyclerView.setItemViewCacheSize(20);

        popularMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

    }

    //method for loading paging data to the vertical recyclerview
    public void popularMoviesPagingLoading() {
        if (isMovieLoading) {
            presentPage += 1;
            popularMoviePageLoading();

            isMovieLoading = false;
        }

//        Log.i("length",nowPlayingPosterImagePaths.size()+"");


    }

    //method for loading first page of vertical recycler view
    private void popularMoviePageLoading() {

        PopularMoviesApiCaller popularMoviesApiCaller = RetrofitClient.getRetrofitInstance().create(PopularMoviesApiCaller.class);//creating api that is extension of url /employees from the apicaller interface
        Call<MostPopularMovies> popularMoviesCall = popularMoviesApiCaller.getResults(presentPage);//call the retrofit api data
        popularMoviesCall.enqueue(new Callback<MostPopularMovies>() {//response when the http is connected
            @Override
            public void onResponse(@NonNull Call<MostPopularMovies> call,
                                   @NonNull Response<MostPopularMovies> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());

                    try {
                        if (response.body() != null) {
                            totalPages = response.body().getTot_pages();
                            popularMoviesData = response.body().getResults();
                            movieCount = response.body().getMovieCount();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    totalPages=10;

                    Log.i("popular length", popularMoviesData.length + "");

                    for (PopularMoviesData popularMoviesData : popularMoviesData) {
                        popularMoviesImagePaths.add(popularMoviesData.getMovieImagePath());
                        popularMoviesTitles.add(popularMoviesData.getMovieName());
                        popularMoviesReleaseDates.add(popularMoviesData.getMovieReleaseDate());
                    }
                    if(presentPage==1) {
                        rvPopularMoviesAdapter = new RVPopularMoviesAdapter(popularMoviesImagePaths,
                                popularMoviesTitles, popularMoviesReleaseDates, MainActivity.this);
                        popularMoviesRecyclerView.setAdapter(rvPopularMoviesAdapter);
                    }else {
                        rvPopularMoviesAdapter.notifyDataSetChanged();
                    }



//                    rvNowPlayingAdapter.notifyItemRangeInserted();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MostPopularMovies> call,//on failure to connect to url = baseurl + API
                                  @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

//        Log.i("length",nowPlayingPosterImagePaths.size()+"");

    }

    //method for loading horizontal recycler view
    private void nowPlayingMovieLoading() {

        NowPlayingMoviesApiCaller nowPlayingMoviesApiCaller = RetrofitClient.getRetrofitInstance().create(NowPlayingMoviesApiCaller.class);//creating api that is extension of url /employees from the apicaller interface
        Call<NowPlayingMovies> nowPlayingMoviesCall = nowPlayingMoviesApiCaller.getResults(1);//call the retrofit api data
        nowPlayingMoviesCall.enqueue(new Callback<NowPlayingMovies>() {//response when the http is connected
            @Override
            public void onResponse(@NonNull Call<NowPlayingMovies> call,
                                   @NonNull Response<NowPlayingMovies> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());

                    try {
                        if (response.body() != null) {
                            currentlyPlayingMoviesData = response.body().getResults();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Log.i("length", currentlyPlayingMoviesData.length + "");

                    for (CurrentlyPlayingMoviesData currentlyPlayingMoviesDatum : currentlyPlayingMoviesData) {
                        nowPlayingPosterImagePaths.add(currentlyPlayingMoviesDatum.getPosterImagePath());
                    }


                    Log.i("length", nowPlayingPosterImagePaths.size() + "");
                    rvNowPlayingAdapter = new RVNowPlayingAdapter(MainActivity.this, nowPlayingPosterImagePaths);
                    nowPlayingRecyclerView.setAdapter(rvNowPlayingAdapter);
//                    rvNowPlayingAdapter.notifyItemRangeInserted();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NowPlayingMovies> call,//on failure to connect to url = baseurl + API
                                  @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

//        Log.i("length",nowPlayingPosterImagePaths.size()+"");


    }


    private void toolbarInitialization() {
        Toolbar toolbar = findViewById(R.id.myToolBar);
        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);

        setSupportActionBar(toolbar);
        toolbarTitle.setText(R.string.MovieBox);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

//    private Retrofit retrofitInitialization(){
//
//    }


}