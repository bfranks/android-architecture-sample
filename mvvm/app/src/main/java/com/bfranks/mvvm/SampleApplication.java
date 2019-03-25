package com.bfranks.mvvm;

import android.app.Application;
import androidx.fragment.app.Fragment;
import com.bfranks.mvvm.di.AppComponent;
import com.bfranks.mvvm.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public class SampleApplication extends Application implements HasSupportFragmentInjector {
  @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;
  protected AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
    initDagger();
  }

  protected void initDagger() {
    this.component = DaggerAppComponent.builder()
        .application(this)
        .build();
    component.inject(this);
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentInjector;
  }
}
