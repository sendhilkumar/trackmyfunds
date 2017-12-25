import React, { Component } from 'react';
import { connect } from 'react-redux';
// import { Row, Col } from 'react-bootstrap';
import Axis from './plot/Axis';
// import * as moment from 'moment';
import * as actions from '../actions';
import * as d3 from 'd3';


class PortfolioHistory extends Component {

  componentDidMount() {
    const portfolioId = this.props.portfolioId ? this.props.portfolioId : 0
    this.props.getPortfolioHistory(portfolioId);
  }

  render() {
    if (this.props.portfolioHistory && Object.values(this.props.portfolioHistory).length>0) {

      const portfolioHistory = Object.values(this.props.portfolioHistory);
      
      const plotWidth = 700;
      const plotHeight = 300;
      const axisSize = 50;

      const min_max_cost = portfolioHistory.reduce((result, current) => { return { min: Math.min(result.min, current.cost), max: Math.max(result.max, current.cost) } }, { min: Number.POSITIVE_INFINITY, max: 0 })
      const min_max_value = portfolioHistory.reduce((result, current) => { return { min: Math.min(result.min, current.value), max: Math.max(result.max, current.value) } }, { min: Number.POSITIVE_INFINITY, max: 0 })
      const min_max_date = portfolioHistory.reduce((result, current) => { return { min: Math.min(result.min, current.asOfDate), max: Math.max(result.max, current.asOfDate) } }, { min: new Date(2020, 12, 30), max: 0 })

      const xScale = d3.scaleTime().domain([new Date(min_max_date.min), new Date(min_max_date.max)]).range([axisSize, plotWidth - axisSize]);
      const minY = Math.min(min_max_cost.min, min_max_value.min)
      const maxY = Math.max(min_max_cost.max, min_max_value.max)

      const yScale = d3.scaleLinear().domain([minY, maxY]).range([plotHeight - axisSize, axisSize]);

      const valueArea = d3.area()
        .x(function (d) { return xScale(d.asOfDate); })
        .y1(function (d) { return yScale(d.value); })
        .y0(yScale(minY));

      const costArea = d3.area()
        .x(function (d) { return xScale(d.asOfDate); })
        .y1(function (d) { return yScale(d.cost); })
        .y0(yScale(minY));

      const xAxis = d3.axisBottom()
        .scale(xScale)
        .ticks(d3.timeMonth.every(1))
        .tickFormat(d3.timeFormat("%d/%m/%y"));

      const yAxis = d3.axisLeft()
        .scale(yScale)
        .ticks(10);


      return <div>
          <svg viewBox={`0 0 ${plotWidth} ${plotHeight}`} >
            <Axis axisType="x" axis={xAxis} translate={{ x: 0, y: plotHeight - axisSize }} />
            <Axis axisType="y" axis={yAxis} translate={{ x: axisSize, y: 0 }} />

            <path d={valueArea(portfolioHistory)} className='valueAreaFill' />
            <path d={costArea(portfolioHistory)} className='costAreaFill' />
          </svg>
      </div>
    } else {
      return <div />
    }
  }
}

const mapStateToProps = (state, ownProps) => {
  const portfolioHistory = state.portfolioHistory[ownProps.portfolioId];
  return {
    portfolioHistory
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    getPortfolioHistory: (portfolioId) => {
      dispatch(actions.getPortfolioHistory(portfolioId))
    }
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PortfolioHistory)
