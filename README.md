# react-native-tweet

A react-native module for fetching and displaying a tweet.

Wrapping [Twitter Kit](https://github.com/twitter/twitter-kit-ios)

#### Manual Installation

##### iOS

1. `yarn add BonnierNews/react-native-tweet`
2. [Install Twitter Kit](https://dev.twitter.com/twitterkit/ios/installation)

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
import se.bonniernews.RNTweet.RNTweetPackage;
```

Under `protected List<ReactPackage> getPackages() {`:

```java
  return Arrays.<ReactPackage>asList(
    new MainReactPackage(),
    new RNTweetPackage()
  );
```

### Usage

```javascript
import Tweet from 'react-native-tweet'

// Display a tweet
<Tweet id="tweet id" />
