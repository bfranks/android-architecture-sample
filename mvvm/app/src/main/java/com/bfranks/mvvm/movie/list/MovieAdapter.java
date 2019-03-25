package com.bfranks.mvvm.movie.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bfranks.mvvm.R;
import com.bfranks.mvvm.network.models.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

  private final MovieClickListener movieClickListener;
  private final List<Movie> movieList;

  public MovieAdapter(MovieClickListener movieClickListener) {
    movieList = new ArrayList<>();
    this.movieClickListener = movieClickListener;
  }

  public synchronized void updateMovieList(@NonNull List<Movie> movies) {
    movieList.clear();
    movieList.addAll(movies);
    notifyDataSetChanged();
  }

  @Override
  public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MovieViewHolder holder, int position) {
    Movie movie = this.movieList.get(position);
    holder.bind(movie, movieClickListener);
  }

  @Override
  public int getItemCount() {
    return this.movieList.size();
  }

  @Override
  public void onViewRecycled(MovieViewHolder holder) {
    super.onViewRecycled(holder);
  }
}
