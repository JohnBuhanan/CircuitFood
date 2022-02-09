package com.johnbuhanan.features.featureB.screen2;

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
public final class Screen2ViewModel_Factory implements Factory<Screen2ViewModel> {
  private final Provider<CoroutineDispatcher> dispatcherProvider;

  private final Provider<Router> routerProvider;

  public Screen2ViewModel_Factory(Provider<CoroutineDispatcher> dispatcherProvider,
      Provider<Router> routerProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.routerProvider = routerProvider;
  }

  @Override
  public Screen2ViewModel get() {
    return newInstance(dispatcherProvider.get(), routerProvider.get());
  }

  public static Screen2ViewModel_Factory create(Provider<CoroutineDispatcher> dispatcherProvider,
      Provider<Router> routerProvider) {
    return new Screen2ViewModel_Factory(dispatcherProvider, routerProvider);
  }

  public static Screen2ViewModel newInstance(CoroutineDispatcher dispatcher, Router router) {
    return new Screen2ViewModel(dispatcher, router);
  }
}
