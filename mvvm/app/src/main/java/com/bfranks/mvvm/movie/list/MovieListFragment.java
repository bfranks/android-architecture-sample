package com.bfranks.mvvm.movie.list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bfranks.mvvm.R;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class MovieListFragment extends Fragment {

  @Inject ViewModelProvider.Factory viewModelFactory;
  private MovieListViewModel viewModel;
  private MovieAdapter movieAdapter;
  private RecyclerView recyclerView;

  public static MovieListFragment newInstance() {
    return new MovieListFragment();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    AndroidSupportInjection.inject(this);
    movieAdapter = new MovieAdapter(movie -> {
      Log.d(MovieListFragment.class.getSimpleName(), "Movie pressed: " + movie.getTitle());
    });
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel.class);
    viewModel.getMovieListLiveData().observe(this, movieFetchResource -> {
      switch (movieFetchResource.getState()) {
        case ERROR:
          break;
        case LOADING:
          break;
        case SUCCESS:
          movieAdapter.updateMovieList(movieFetchResource.getData().getResults());
          break;
        case UNINITIALIZED:
          break;
        default:
      }
    });
    viewModel.getMovieList(1);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.content_fragment, container, false);

    recyclerView = view.findViewById(R.id.rv_movies);
    GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return 1;
      }
    });
    recyclerView.setLayoutManager(manager);
    recyclerView.setAdapter(movieAdapter);
    return view;
  }
}
