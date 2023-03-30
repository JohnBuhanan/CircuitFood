# Experimenting with making a scalable app using Slack's "Circuit" by Zac Sweers

* Circuit
* Compose
* Molecule Presenters
* Anvil
* Flow/Coroutines
* Animations
* Retrofit
* Coil
* Moshi

# Experimenting with a Compose friendly navigation scheme.
I studied a dozen github repos on compose navigation and learned some things.

I believe Compose navigation makes it extremely difficult to be type-safe because they are trying to make "implicit" deeplinks work.

Once you eschew implicit deeplinks, all sorts of Compose navigation schemes become possible.

This repo contains one of my favorite approaches that I found.

Circuit (https://github.com/slackhq/circuit)

### Goals:
* navigate
* deeplink
* synthesizeBackStack
* navigateForResult. (I made a hack for this, not sure if it's okay)
* scales for massively multi-modular apps (some other schemes were "multi-module" but did not scale well)

### Nice bonuses:
* can load/unload modules with ease.
* Single activity


### Negatives:
* Hard to integrate into an existing app that already uses other navigation schemes.
