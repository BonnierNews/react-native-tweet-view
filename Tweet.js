import PropTypes from 'prop-types'
import React from 'react'
import {requireNativeComponent, ViewPropTypes} from 'react-native'

let savedHeight = {}

class Tweet extends React.Component {
  state = {
    height: (this.props.tweetId && savedHeight[this.props.tweetId])|| 0
  }
  onSizeChange = (event) => {
    const {height} = event.nativeEvent
    const {tweetId} = this.props
    if (height < this.state.height || !tweetId) return
    savedHeight[tweetId] = height
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
