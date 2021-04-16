package courses.udemy.retrofitmovieuiproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//singleton instance of retrofit instantiation
public class RetrofitClient {

    private static Retrofit retrofitInstance;

    public static Retrofit getRetrofitInstance() {
        if(retrofitInstance == null){
            //base url for retrofit
            String baseUrl = "https://api.themoviedb.org/3/movie/";
            retrofitInstance =  new Retrofit.Builder()//used to build and initialize the retrofit api
                    .baseUrl(baseUrl)//the base url loading from the apiCaller interface
                    .addConverterFactory(GsonConverterFactory.create())//used gson retrieval from the url
                    .build();//retrofit initialized

        }
        return retrofitInstance;
    }
}
