import React, { Component } from 'react';
import * as d3 from 'd3';
import * as moment from 'moment';
import * as actions from '../../actions'
import { connect } from 'react-redux'
import NAVDataPoint from './NAVDataPoint.js';
import NAVDataCursor from './NAVDataCursor.js';
import TransactionPlot from './TransactionPlot.js';
import Axis from './Axis';
import Grid from '../common/Grid';
import { Row, Col } from 'react-bootstrap';

class NAVAndTxPlot extends Component {

  constructor(props) {
    super(props);
    this.state = { currentEntry: undefined }
    this.handleMouseOverOnNAV = this.handleMouseOverOnNAV.bind(this);
  }

  componentDidMount() {
    this.props.getHistory(this.props.schemeCode);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.schemeCode !== nextProps.schemeCode) {
      this.setState({ currentEntry: undefined });
      nextProps.getHistory(nextProps.schemeCode);
    }
  }

  handleMouseOverOnNAV(entry) {
    this.setState({ currentEntry: entry });
  }

  render() {
    const navPlotData = this.props.navPlotData;

    if (navPlotData && navPlotData.length > 0) {
      const plotWidth = 500;
      const plotHeight = 300;
      const axisSize = 50;

      const min_max_nav = navPlotData.reduce((result, current) => { return { min: Math.min(result.min, current.value), max: Math.max(result.max, current.value) } }, { min: Number.POSITIVE_INFINITY, max: 0 })
      const min_max_date = navPlotData.reduce((result, current) => { return { min: Math.min(result.min, current.date), max: Math.max(result.max, current.date) } }, { min: new Date(2020, 12, 30), max: 0 })

      const xScale = d3.scaleTime().domain([new Date(min_max_date.min), new Date(min_max_date.max)]).range([axisSize, plotWidth - axisSize]);
      const yScale = d3.scaleLinear().domain([min_max_nav.min, min_max_nav.max]).range([plotHeight - axisSize, axisSize]);

      const line = d3.area()
        .x(function (d) { return xScale(d.date); })
        .y1(function (d) { return yScale(d.value); })
        .y0(yScale(min_max_nav.min));

      const xAxis = d3.axisBottom()
        .scale(xScale)
        .ticks(d3.timeMonth.every(1))
        .tickFormat(d3.timeFormat("%d/%m/%y"));

      const yAxis = d3.axisLeft()
        .scale(yScale)
        .ticks(10);

      const transactions = this.props.lineItem && this.props.lineItem.transactions;

      return (
        <div className="App">
          <Row>
            <Col md={12}>
              {
                this.props.lineItem && <div style={{ margin: '20px' }}>
                  <h4> {this.props.lineItem.scheme.name} </h4>

                  <h5>
                    As of {moment(this.props.lineItem.latestNAVDate).format("DD-MMM-YYYY")}
                    &nbsp; &nbsp; &nbsp;Cost: {formatted(this.props.lineItem.cost)}
                    &nbsp; &nbsp; &nbsp;Value: {formatted(this.props.lineItem.value)}
                  </h5>

                  <h6>
                    {this.state.currentEntry ? this.state.currentEntry.value + ',  ' + this.state.currentEntry.date.toDateString() : '##'}
                  </h6>

                </div>
              }
            </Col>
          </Row>

          <Row>
            <Col md={12}>
              <svg viewBox={`0 0 ${plotWidth} ${plotHeight}`} >
                <Axis axisType="x" axis={xAxis} translate={{ x: 0, y: plotHeight - axisSize }} />
                <Axis axisType="y" axis={yAxis} translate={{ x: axisSize, y: 0 }} />

                {
                  <path
                    d={line(navPlotData)}
                    className='navPlotAreFill'
                  />
                }
                {
                  transactions && transactions.map((entry, i) => <TransactionPlot
                    key={entry.date}
                    transaction={entry}
                    xScale={xScale}
                    yScale={yScale}
                  />)
                }

                {
                  navPlotData.map((entry, i) => <NAVDataPoint
                    key={entry.date}
                    navData={entry}
                    nextData={i < navPlotData.length - 1 ? navPlotData[i + 1] : undefined}
                    xScale={xScale}
                    yScale={yScale}
                    handleMouseOver={this.handleMouseOverOnNAV}
                    plotHeight={plotHeight}
                    axisSize={axisSize}
                  />
                  )
                }
                {
                  navPlotData.map((entry, i) => <NAVDataCursor
                    key={entry.date}
                    navData={entry}
                    xScale={xScale}
                    yScale={yScale}
                    focussed={this.state.currentEntry === entry}
                    plotHeight={plotHeight}
                    axisSize={axisSize}
                  />)
                }
              </svg>
            </Col>
          </Row>

          <Row>
            <Col md={12}>
              <Grid key={this.props.lineItem.scheme.name}
                data={transactions}
                columns={[
                  {
                    headerName: 'Date',
                    valueFunction: lineItem => lineItem.date,
                    displayFunction: value => moment(value).format("DD-MMM-YYYY"),
                    valueDataType: 'date'
                  },
                  {
                    headerName: 'Description',
                    valueFunction: lineItem => lineItem.description,
                    displayFunction: value => value,
                    valueDataType: 'text'
                  },
                  {
                    headerName: `Amount`,
                    valueFunction: lineItem => lineItem.amount,
                    displayFunction: value => formatted(value)
                  },
                  {
                    headerName: `Price`,
                    valueFunction: lineItem => lineItem.price,
                    displayFunction: value => value
                  }
                ]}
                rowKeyFunction={lineItem => lineItem.id + lineItem.schemeCode} />
            </Col>
          </Row>
        </div>
      );
    } else {
      return <div>Loading data.. </div>
    }
  }
}

const formatted = value => new Intl.NumberFormat('en-IN', { maximumFractionDigits: 0 }).format(value);

const mapStateToProps = (state, ownProps) => {
  const schemeCode = ownProps.lineItem ? ownProps.lineItem.scheme.code : ownProps.params.fundId;
  const hist = state.hist[schemeCode];
  const navPlotData = hist && hist.map(navEntry => { return { date: new Date(moment(navEntry.date)), value: navEntry.netAssetValue } });
  return {
    navPlotData: navPlotData
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    schemeCode: ownProps.lineItem ? ownProps.lineItem.scheme.code : ownProps.params.fundId,
    getHistory: (schemeCode) => {
      dispatch(actions.getHistory(schemeCode))
    }
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NAVAndTxPlot)
