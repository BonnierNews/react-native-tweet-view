//
//  RNTweetView.h
//  RNTweet
//
//  Created by Daniel Lindström (DN) on 2018-01-17.
//  Copyright © 2018 accosine. All rights reserved.
//

#import <UIKit/UIKit.h>

@import TwitterKit;

@interface RNTweetView : UIView

@property(nonatomic) NSString* tweetId;
@property(nonatomic, copy) TWTRTweetView* tweetView;

@end
