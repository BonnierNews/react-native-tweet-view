# react-native-tweet

A react-native module for fetching and displaying a tweet.

Wrapping [Twitter Kit](https://github.com/twitter/twitter-kit-ios)

#### Manual Installation

##### iOS

1. `yarn add BonnierNews/react-native-tweet`
2. Add the [Google Mobile Ads SDK](https://developers.google.com/admob/ios/quick-start#import_the_mobile_ads_sdk) to your Xcode project with CocoaPods or manually. This is only needed for iOS and guarantees your app is using the newest AdMob version.
3. Add react-native-tweet static library to your Xcode project like explained [here](http://facebook.github.io/react-native/docs/linking-libraries-ios.html#manual-linking). (Step 3 of this guide is not needed)

##### Android

1. `yarn add BonnierNews/react-native-tweet`
2. Make the following additions to the given files:

**`android/settings.gradle`**

```groovy
include ':react-native-tweet'
project(':react-native-tweet').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-tweet/android')
```

**`android/app/build.gradle`**

```groovy
dependencies {
   // ...
   compile project(':react-native-tweet')
}
```

**`MainApplication.java`**

In **MainAplication.java** on top, where imports are:

```java
import se.bonniernews.RNDfp.RNDFPPackage;
```

Under `protected List<ReactPackage> getPackages() {`:

```java
  return Arrays.<ReactPackage>asList(
    new MainReactPackage(),
    new RNDFPPackage()
  );
```

### Usage

```javascript
import Tweet from 'react-native-tweet'

// Display a tweet
<Tweet id="tweet id" />
