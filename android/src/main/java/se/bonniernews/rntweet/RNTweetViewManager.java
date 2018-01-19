/**
 * Created by daniel.lindstrom on 2018-01-17.
 */

package se.bonniernews.rntweet;

import android.content.Context;
import android.location.Location;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Canvas;

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

import android.widget.RelativeLayout;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.util.DisplayMetrics;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class RNTweetView extends RelativeLayout {
    private TweetView tweetView = null;
    private boolean postponedResize = false;

    public RNTweetView(Context context) {
        super(context);
    }

    public void setTweetId(long tweetId) {
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                setTweet(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
            }
        });
    }


    public void setTweet(Tweet tweet) {
        if(tweetView == null) {
            tweetView = new TweetView(getContext(), tweet);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);
            tweetView.setLayoutParams(layoutParams);
            addView(tweetView);
        }
        updateSize();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (postponedResize) {
            postponedResize = false;
            updateSize();
        }
    }

    private void updateSize() {
        if (getWidth() <= 0) {
            postponedResize = true;
            return;
        }

        measureTweet();
        requestLayout();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int height = Math.round(tweetView.getMeasuredHeight() / metrics.density);
        WritableMap event = Arguments.createMap();
        event.putInt("height", height);
        ReactContext reactContext = (ReactContext)getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
        .receiveEvent(getId(), "onSizeChange", event);
    }


    private void measureTweet() {

        if(tweetView != null) {
            int w = View.MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY);
            int h = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, MeasureSpec.UNSPECIFIED);

            tweetView.measure(w, h);
        }
    }
}

class RNTweetViewManager extends SimpleViewManager<RNTweetView> {
    public static final String REACT_CLASS = "RNTweetView";
    public static final String PROP_TWEET_ID = "tweetId";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected RNTweetView createViewInstance(ThemedReactContext themedReactContext) {
        RNTweetView tweetView = createTweetView(themedReactContext);
        // tweetView.addSizeChangeListener(this);
        return tweetView;
    }

    @ReactProp(name = PROP_TWEET_ID)
    public void setTweetId(final RNTweetView view, final String tweetId) {
        long parsedTweetId = Long.parseLong(tweetId);
        view.setTweetId(parsedTweetId);
    }

    @NonNull
    public static RNTweetView createTweetView(ThemedReactContext context) {
        return new RNTweetView(context);
    }

    @Override
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
        return builder
            .put("onSizeChange", MapBuilder.of("registrationName", "onSizeChange"))
            .build();
    }
}
