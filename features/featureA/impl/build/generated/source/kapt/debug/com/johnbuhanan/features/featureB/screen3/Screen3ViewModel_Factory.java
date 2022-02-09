package com.johnbuhanan.features.featureB.screen3;

import com.johnbuhanan.navigation.Router;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class Screen3ViewModel_Factory implements Factory<Screen3ViewModel> {
  private final Provider<CoroutineDispatcher> dispatcherProvider;

  private final Provider<Router> routerProvider;

  public Screen3ViewModel_Factory(Provider<CoroutineDispatcher> dispatcherProvider,
      Provider<Router> routerProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.routerProvider = routerProvider;
  }

  @Override
  public Screen3ViewModel get() {
    return newInstance(dispatcherProvider.get(), routerProvider.get());
  }

  public static Screen3ViewModel_Factory create(Provider<CoroutineDispatcher> dispatcherProvider,
      Provider<Router> routerProvider) {
    return new Screen3ViewModel_Factory(dispatcherProvider, routerProvider);
  }

  public static Screen3ViewModel newInstance(CoroutineDispatcher dispatcher, Router router) {
    return new Screen3ViewModel(dispatcher, router);
  }
}
