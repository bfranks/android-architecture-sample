package com.bfranks.mvvm.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.bfranks.mvvm.movie.list.MovieListViewModel;
import com.bfranks.mvvm.util.ViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(MovieListViewModel.class)
  abstract ViewModel bindUserViewModel(MovieListViewModel ticketListViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
