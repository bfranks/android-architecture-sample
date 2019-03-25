package com.bfranks.mvvm.movie.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.bfranks.mvvm.network.FetchResource;
import com.bfranks.mvvm.network.TMDBRepository;
import com.bfranks.mvvm.network.models.Movie;
import com.bfranks.mvvm.network.models.PageResult;
import javax.inject.Inject;

public class MovieListViewModel extends ViewModel {
  // TODO: Implement the ViewModel
  private final MutableLiveData<Integer> getMoviesTrigger = new MutableLiveData<>();
  private final LiveData<FetchResource<PageResult<Movie>>> getMoviesLiveData;

  @Inject
  public MovieListViewModel(TMDBRepository tmdbRepository) {
    getMoviesLiveData = Transformations.switchMap(getMoviesTrigger, tmdbRepository::getMovies);
  }

  public LiveData<FetchResource<PageResult<Movie>>> getMovieListLiveData() {
    return getMoviesLiveData;
  }

  public void getMovieList(@NonNull int page) {
    getMoviesTrigger.setValue(page);
  }
}
