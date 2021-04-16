package courses.udemy.retrofitmovieuiproject.playingnowmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import courses.udemy.retrofitmovieuiproject.R;

//adapter for recyclerview horizontal
public class RVNowPlayingAdapter extends RecyclerView.Adapter<RVNowPlayingAdapter.ViewHolder> {

    //to storeimage paths
    private final List<String> imagePaths;
    private final Context context;

    public RVNowPlayingAdapter(Context context,List<String> list) {
        this.imagePaths=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movies_list_now_playing,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //string coding
        String imageBasePath = "https://image.tmdb.org/t/p/w342";
        Picasso.get().load(imageBasePath +imagePaths.get(position)).into(holder.image);

        Log.i("size",imagePaths.size()+"");

    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.now_playing_imageview);
        }
    }
}
