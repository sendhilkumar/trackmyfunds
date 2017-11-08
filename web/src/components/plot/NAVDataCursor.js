import React, { Component } from 'react';

export default class NAVDataCursor extends Component {

  shouldComponentUpdate(nextProps, nextState) {
    return this.props.focussed !== nextProps.focussed;
  }

  render() {

    const axisSize = this.props.axisSize;
    const plotHeight = this.props.plotHeight;
    const xCoord = this.props.xScale(this.props.navData.date);
    const yCoord = this.props.yScale(this.props.navData.value);

    const focussed = this.props.focussed
    const tootipDisplay = focussed ? 'default' : 'none';

    return <g>
      <line
        fill="none"
        stroke="#0074d9"
        strokeWidth="1"
        x1={xCoord}
        y1={yCoord}
        x2={xCoord}
        y2={plotHeight-axisSize}
        display={tootipDisplay}
        opacity='0.4'
      />

      <line
        fill="none"
        stroke="#0074d9"
        strokeWidth="1"
        x1={axisSize}
        y1={yCoord}
        x2={xCoord}
        y2={yCoord}
        display={tootipDisplay}
        opacity='0.4'
      />

      <circle
        fill="#006666"
        stroke='#006666'
        cx={xCoord}
        cy={yCoord}
        r={focussed ? '1' : '0'}
      />

      <circle
        fill="#228866"
        opacity='0.5'
        cx={xCoord}
        cy={yCoord}
        r={focussed ? '4' : '0'}
      />

      {/* <text
        x={xCoord > 400 ? xCoord - 50 : xCoord + 20}
        y={yCoord > 400 ? yCoord - 50 : yCoord + 20}
        display={tootipDisplay}>
        {this.props.navData.value + ',  ' + this.props.navData.date.toDateString()}
      </text> */}

    </g>
  }
}
