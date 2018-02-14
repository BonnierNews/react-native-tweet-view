//
//  RNTweetView.m
//  RNTweet
//
//  Created by Daniel Lindström (DN) on 2018-01-17.
//  Copyright © 2018 accosine. All rights reserved.
//

#import "RNTweetView.h"

@implementation RNTweetView

- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        self.tweetView = [[TWTRTweetView alloc] init];
        self.tweetView.showActionButtons = false;
        [self addSubview:self.tweetView];
    }
    return self;
}

- (void)layoutSubviews {
    [super layoutSubviews];
    if (self.tweetView != nil) {
        CGFloat desiredHeight = [self.tweetView sizeThatFits:CGSizeMake(self.bounds.size.width, CGFLOAT_MAX)].height;
        if (self.onSizeChange) {
            self.onSizeChange(@{@"width": [NSNumber numberWithDouble:self.frame.size.width], @"height": [NSNumber numberWithDouble:desiredHeight]});
        }
    }
    self.tweetView.frame = self.bounds;
}

- (void) setTweetId:(NSString *)tweetId {
    if (tweetId == nil) {
        return;
    }
    TWTRAPIClient *client = [[TWTRAPIClient alloc] init];
    [client loadTweetWithID:tweetId completion:^(TWTRTweet *tweet, NSError *error) {
        if (tweet) {
            [self.tweetView configureWithTweet:tweet];
        } else {
            NSLog(@"Failed to load tweet: %@", [error localizedDescription]);
        }
    }];
}

@end
