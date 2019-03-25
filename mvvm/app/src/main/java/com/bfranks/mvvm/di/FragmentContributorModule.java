package com.bfranks.mvvm.di;

import com.bfranks.mvvm.movie.list.MovieListFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentContributorModule {
  @ContributesAndroidInjector abstract MovieListFragment contributesContentFragment();
}
