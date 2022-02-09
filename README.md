# Experimenting with making an MVI-Compose framework

* Compose
* ViewModels
* Flow/Coroutines
* Hilt
* MVVM blend with MVI
* Animations
* Compose Navigation
* Retrofit
* Coil
* GSON (Switch to Moshi?)

# Experimenting with a Compose friendly navigation scheme.
I studied a dozen github repos on compose navigation and learned some things.

I believe Compose navigation makes it extremely difficult to be type-safe because they are trying to make "implicit" deeplinks work.

Once you eschew implicit deeplinks, all sorts of Compose navigation schemes become possible.

This repo contains one of my favorite approaches I that I found.  Basically I just wrapped a library called "Voyager": https://github.com/adrielcafe/voyager)

### Goals:
* navigate
* deeplink
* synthesizeBackStack
* navigateForResult. (Wish this was cleaner... maybe we can think of something.)
* scales for massively multi-modular apps (some other schemes were "multi-module" but did not scale well)
* scoped/safe/lifecycle aware for ViewModels

### Nice bonuses:
* can load/unload modules with ease.
* Single activity


### Negatives:
* Do we REALLY want single activity? An activity makes a natural boundary for a feature. That's why we can have so many legacy architectures existing side-by-side maybe?
* UnitTesting navigation might be hard? Integration test might be only option?
* Integrating a SingleActivity scheme like this in our app would require synthesizing a backstack that include both kinds of navigation potentially
