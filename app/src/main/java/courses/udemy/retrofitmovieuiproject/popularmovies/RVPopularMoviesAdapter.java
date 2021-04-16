package courses.udemy.retrofitmovieuiproject.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import courses.udemy.retrofitmovieuiproject.R;

import static courses.udemy.retrofitmovieuiproject.MainActivity.presentPage;
import static courses.udemy.retrofitmovieuiproject.MainActivity.totalPages;

//adapter for the vertical recyclerview
public class RVPopularMoviesAdapter extends RecyclerView.Adapter<RVPopularMoviesAdapter.ViewHolder> {

    private final List<String> imagePaths;
    private final List<String> movieTitles;
    private final List<String> movieReleaseDates;
    private final Context context;
    public static boolean isMovieLoading=false;

    public RVPopularMoviesAdapter(List<String> imagePaths, List<String> movieTitles, List<String> movieReleaseDates, Context context) {
        this.imagePaths = imagePaths;
        this.movieTitles = movieTitles;//usually done with pojo class
        this.movieReleaseDates = movieReleaseDates;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==imagePaths.size()-1&&presentPage!=totalPages){
            return 1;
        }else {
            return 0;
        }
    }

    @NonNull
    @Override
    public RVPopularMoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = null;
        if(viewType==0){

                view = inflater.inflate(R.layout.popular_movies_list_recyclerview,parent,false);

            }else if(viewType==1){

                view = inflater.inflate(R.layout.progress_bar,parent,false);

            }
        return new ViewHolder(Objects.requireNonNull(view), viewType);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(getItemViewType(position)==1){

            isMovieLoading=true;

        }else{

            String imageBasePath = "https://image.tmdb.org/t/p/w154";
            Picasso.get().load(imageBasePath +imagePaths.get(position)).into(holder.image);
            holder.txtTitle.setText(movieTitles.get(position));
            holder.txtReleaseDate.setText(movieReleaseDates.get(position));

//
//        Log.i("size",imagePaths.size()+"");

        }

//
    }


    @Override
    public int getItemCount() {
        return imagePaths.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView txtTitle,txtReleaseDate;

//        public ImageView getImage() {
//            return image;
//        }

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if(viewType==0){
                this.image = itemView.findViewById(R.id.imgPopularPoster);
                this.txtTitle=itemView.findViewById(R.id.txtTitle);
                this.txtReleaseDate=itemView.findViewById(R.id.txtReleaseDate);
            }
        }
    }

//    class ProgressViewHolder extends RecyclerView.ViewHolder{
//        public ProgressViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
}
