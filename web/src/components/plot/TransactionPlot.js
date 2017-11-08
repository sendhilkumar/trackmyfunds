import React, { Component } from 'react';

export default class TransactionPlot extends Component {

  render() {

    const xCoord = this.props.xScale(this.props.transaction.date);
    const yCoord = this.props.yScale(this.props.transaction.price);
    const r = this.props.transaction.amount / 1000;

    return <g>
      <circle
        fill="#ff0000"
        stroke='#ff0000'
        cx={xCoord}
        cy={yCoord}
        r={2}
      />

      <circle
        fill="#00ff00"
        stroke='#00ff00'
        cx={xCoord}
        cy={yCoord}
        r={r}
        opacity='0.8'
      />

      <circle
        fill="#668866"
        opacity='0.5'
        cx={xCoord}
        cy={yCoord}
        r={r + 2}
      />
    </g>
  }
}
