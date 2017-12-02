import React, { Component } from 'react';

export default class NAVDataPoint extends Component {

  constructor(props) {
    super(props);
    this.handleMouseOver = this.handleMouseOver.bind(this);
  }

  handleMouseOver() {
    this.props.handleMouseOver(this.props.navData)
  }

  shouldComponentUpdate(nextProps, nextState) {
    return this.props.focussed !== nextProps.focussed;
  }

  render() {

    const xCoord = this.props.xScale(this.props.navData.date);
    const xCoordNextDataPoint = this.props.nextData ? this.props.xScale(this.props.nextData.date) : xCoord + 10;

    return <g>
      <rect
        x={xCoord}
        y='0'
        width={xCoordNextDataPoint - xCoord}
        height={this.props.plotHeight}
        opacity='0'
        onMouseOver={this.handleMouseOver} />
    </g>
  }
}
