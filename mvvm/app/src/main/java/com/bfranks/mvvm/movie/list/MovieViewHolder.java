package com.bfranks.mvvm.movie.list;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bfranks.mvvm.R;
import com.bfranks.mvvm.network.models.Movie;
import com.bfranks.mvvm.util.MovieUtil;
import com.bumptech.glide.Glide;

public class MovieViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.iv_movie_poster) ImageView moviePoster;
  @BindView(R.id.cv_movie_card) CardView movieCard;

  public MovieViewHolder(final View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(final Movie movie, final MovieClickListener movieClickListener) {
    movieCard.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth()/2, getMeasuredPosterHeight(getScreenWidth()/2)));
    Glide
        .with(movieCard)
        .load(MovieUtil.imagePathBuilder(movie.getPosterPath()))
        .centerCrop()
        .placeholder(R.drawable.loading_spinner)
        .into(moviePoster);

    itemView.setOnClickListener(v -> movieClickListener.onMovieClick(movie));
  }

  private int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }
  private int getMeasuredPosterHeight(int width) {
    return (int) (width * 1.5f);
  }
}
