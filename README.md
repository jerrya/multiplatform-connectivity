# Multiplatform connectivity

## What is it?

Multiplatform-connectivity is a lightweight library for Compose/Kotlin Multiplatform that monitors device connectivity.

It currently only supports iOS and Android.

## Platform Support

| Android | iOS | MacOS | Web | Linux | Windows |
| :-----: | :-: | :---: | :-: | :---: | :-----: |
|   ✅    | ✅  |  ❌   | ❌  |  ❌   |   ❌    |


## Installation

Add the dependency to your libraries.toml file:

```toml
[libraries]
multiplatform-connectivity = { module = "io.github.jerrya:multiplaform-connectivity", version.ref = "multiplaform-connectivity" }

[versions]
multiplaform-connectivity = "1.0.0"
```

Then sync your project with Gradle and add the dependency:

```groovy
kotlin {
    sourceSets {
        commonMain.dependencies {
          implementation(libs.multiplatform.connectivity)
        }
    }
}
```

## Usage

Multiplatform-connectivity exposes a Flow that produces two states: Online and Offline.

To collect on the flow, first get the reference to the connectivity repository and launch the `latestStatus` flow in a coroutine scope.

```kotlin
import io.github.jerrya.connectivity.getConnectivity

viewModelScope.launch {
    val connectivity = getConnectivity()
    connectivity.latestStatus().collect {
        when (it) {
            is ConnectivityRepository.Status.Online -> {
                // You are online
            }
            is ConnectivityRepository.Status.Offline -> {
                // You are offline
            }
        }
    }
}
```

## Notes

### iOS
- There is a known issue with iOS Simulators not responding correctly to connectivity changes. I recommend using a physical iPhone device to test connectivity.