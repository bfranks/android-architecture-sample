package com.bfranks.mvvm.network;

import com.bfranks.mvvm.network.models.Movie;
import com.bfranks.mvvm.network.models.PageResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api that interacts with the movie database
 * @see <a href="https://developers.themoviedb.org/3/getting-started/introduction">The Movie Database</a>
 */
public interface TMDBApi {
  @GET("movie/top_rated")
  Call<PageResult<Movie>> getTopRatedMovies(@Query("page") int page, @Query("api_key") String userkey);
}
