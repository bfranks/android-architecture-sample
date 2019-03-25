package com.bfranks.mvvm.network;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bfranks.mvvm.network.models.Movie;
import com.bfranks.mvvm.network.models.PageResult;
import com.bfranks.mvvm.util.AppExecutors;
import java.io.IOException;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

public class TMDBRepository {
  private final AppExecutors appExecutors;
  private final TMDBApi tmdbApi;

  @Inject
  public TMDBRepository(AppExecutors appExecutors, TMDBApi tmdbApi) {
    this.appExecutors = appExecutors;
    this.tmdbApi = tmdbApi;
  }

  public LiveData<FetchResource<PageResult<Movie>>> getMovies(int page) {
    FetchResource<PageResult<Movie>> getMovies = new FetchResource<>();
    MutableLiveData<FetchResource<PageResult<Movie>>> moviesListLiveResource = new MutableLiveData<>();
    moviesListLiveResource.setValue(getMovies);
    appExecutors.networkIO().execute(() -> fetchMoviesList(moviesListLiveResource, page));
    return moviesListLiveResource;
  }

  private FetchResource<PageResult<Movie>> fetchMoviesList(@Nullable MutableLiveData<FetchResource<PageResult<Movie>>> fetchResourceMutableLiveData, int page) {
    if (fetchResourceMutableLiveData != null) {
      fetchResourceMutableLiveData.postValue(FetchResource.loading());
    }
    Call<PageResult<Movie>> moviePageCall = tmdbApi.getTopRatedMovies(page, "");
    try {
      Response<PageResult<Movie>> getMoviesResponse = moviePageCall.execute();
      if (getMoviesResponse.isSuccessful()) {
        PageResult<Movie> moviePageResult = getMoviesResponse.body();
        FetchResource<PageResult<Movie>> success = FetchResource.success(moviePageResult);
        if (fetchResourceMutableLiveData != null) {
          fetchResourceMutableLiveData.postValue(success);
        }
        return success;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    FetchResource<PageResult<Movie>> errorValue = FetchResource.error(null);
    if (fetchResourceMutableLiveData != null) {
      fetchResourceMutableLiveData.postValue(errorValue);
    }
    return errorValue;
  }
}
