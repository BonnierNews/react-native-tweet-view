//
//  RNTweetManager.m
//  RNTweet
//
//  Created by Daniel Lindström (DN) on 2018-01-17.
//  Copyright © 2018 accosine. All rights reserved.
//

#import "RNTweetViewManager.h"


@implementation RNTweetViewManager

RCT_EXPORT_MODULE();

- (UIView *)view
{
    RNTweetView *tweetView = [[RNTweetView alloc] init];
    return twetView;
}

RCT_EXPORT_VIEW_PROPERTY(tweetId, NSString)

@end
