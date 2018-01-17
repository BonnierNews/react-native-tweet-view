import PropTypes from 'prop-types'
import React from 'react'
import {requireNativeComponent} from 'react-native'

class Tweet extends React.Component {
  render () {
    return <RNTweetView {...this.props} />
  }
}

Tweet.propTypes = {
  tweetId: PropTypes.string
}

const RNTweetView = requireNativeComponent('RNTweetView', Tweet)

export default Tweet
