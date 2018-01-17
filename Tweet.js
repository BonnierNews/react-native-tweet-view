import PropTypes from 'prop-types'
import React from 'react'
import {requireNativeComponent, ViewPropTypes} from 'react-native'

class Tweet extends React.Component {
  state = {
    height: 0
  }
  onSizeChange = (event) => {
    const {height} = event.nativeEvent
    height && this.setState({height})
  }
  render () {
    const {height} = this.state
    return <RNTweetView {...this.props} style={[this.props.style, {height}]} onSizeChange={this.onSizeChange} />
  }
}

Tweet.propTypes = {
  ...ViewPropTypes,
  tweetId: PropTypes.string,
  onSizeChange: PropTypes.func
}

const RNTweetView = requireNativeComponent('RNTweetView', Tweet)

export default Tweet
