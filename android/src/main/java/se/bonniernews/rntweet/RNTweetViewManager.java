/**
 * Created by daniel.lindstrom on 2018-01-17.
 */

package se.bonniernews.rntweet;

import android.content.Context;
import android.location.Location;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ReactTweetView extends ReactViewGroup {

  public void setTweetId(string tweetId) {
    TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
      @Override
      public void success(Result<Tweet> result) {
        return new TweetView(EmbeddedTweetsActivity.this, tweet);
      }

      @Override
      public void failure(TwitterException exception) {
      }
    });
  }
}

class ReactTweetViewManager extends SimpleViewManager<ReactTweetView> {
  public static final String REACT_CLASS = "RNTweetView";
  public static final String PROP_TWEET_ID = "tweetId";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected ReactTweetView createViewInstance(ThemedReactContext themedReactContext) {
    ReactTweetView tweetView = new ReactTweetView(themedReactContext);
    return tweetView;
  }

  @ReactProp(name = PROP_TWEET_ID)
  public void setTweetId(final ReactTweetView view, final string tweetId) {
    view.setTweetId(tweetId);
  }
}