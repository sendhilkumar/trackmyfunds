import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Row, Col } from 'react-bootstrap';
import * as moment from 'moment';
import * as actions from '../actions';
import Grid from './common/Grid';
import NAVAndTxPlot from './plot/NAVAndTxPlot';

class PortfolioCurrentValue extends Component {

  constructor(props) {
    super(props);
    this.state = { selectedSchemeCode: undefined };
    this.selectScheme = this.selectScheme.bind(this);
  }

  selectScheme(lineItem) {
    this.setState({ selectedLineItem: lineItem })
  }

  componentDidMount() {
    const portfolioId = this.props.params && this.props.params.portfolioId ? this.props.params.portfolioId : 0
    this.props.getPortfolioCurrentValue(portfolioId);
  }

  render() {
    const currentPortfolioValue = this.props.currentPortfolioValue;
    if (currentPortfolioValue && currentPortfolioValue.portfolioValueOneDayDelta) {
      const cost = currentPortfolioValue.portfolioValueOneDayDelta.today.cost
      const value = currentPortfolioValue.portfolioValueOneDayDelta.today.value;
      const returns = value - cost;
      // const returnPct = 100 * returns / cost;

      const backgroundColor = portfolioNameHeaderColors[this.props.params.portfolioId % 2];

      return <div>
        <Row>
          <Col mdOffset={3} md={6}>

            <div style={{ backgroundColor, color: 'rgb(245, 245, 245)', padding: '4px 6px 4px 4px', fontSize: '13px', fontWeight: 'bold', boxShadow: '0 0 4px rgba(0,0,0,.4)'}}>
              <span>{this.props.params.portfolioName}</span>
            </div>

            <div style={{ backgroundColor: '#f5f5f5', fontSize: '10px', boxShadow: '0 0 4px rgba(0,0,0,.4)' }}>
              <Grid
                data={this.props.subHeadersOnly ? [] : currentPortfolioValue.portfolioValueOneDayDelta.schemeValues}
                subHeaders={currentPortfolioValue.portfolioValueOneDayDelta}
                columns={[
                  {
                    headerName: this.props.subHeadersOnly ? '' : 'Scheme',
                    valueFunction: lineItem => lineItem.scheme && lineItem.scheme.name,
                    displayFunction: (value, lineItem) => <a style={{ cursor: 'pointer' }}
                      onClick={() => this.selectScheme(lineItem.today)}>
                      {value}
                    </a>,
                    valueDataType: 'text',
                    className: 'scheme-name-in-grid'
                  },
                  {
                    headerName: 'Cost',
                    valueFunction: lineItem => lineItem.today.cost,
                    displayFunction: value => formatted(value)
                  },
                  {
                    headerName: `Today value ${moment(currentPortfolioValue.portfolioValueOneDayDelta.today.asOfDate).format("DD-MMM-YYYY")}`,
                    valueFunction: lineItem => lineItem.today.value,
                    displayFunction: value => formatted(value)
                  },
                  {
                    headerName: `1 day change in value`,
                    valueFunction: lineItem => (lineItem.today.value - lineItem.today.cost) - (lineItem.prior.value - lineItem.prior.cost),
                    displayFunction: value => formatted(value)
                  },
                  {
                    headerName: `1 day change in value%`,
                    valueFunction: lineItem => 100 * ((lineItem.today.value - lineItem.today.cost) - (lineItem.prior.value - lineItem.prior.cost)) / lineItem.prior.cost,
                    displayFunction: value => formattedPercentage(value)
                  },
                  {
                    headerName: `Return`,
                    valueFunction: lineItem => lineItem.today.value - lineItem.today.cost,
                    displayFunction: value => formatted(value)
                  },
                  {
                    headerName: `XIRR`,
                    valueFunction: lineItem => 100 * lineItem.today.xirr,
                    displayFunction: value => formattedPercentage(value)
                  }
                ]}
                rowKeyFunction={lineItem => lineItem.scheme.name} />

            </div>
          </Col>
        </Row>
        <Row>
          <Col mdOffset={3} md={6} style={{marginTop:'15px'}}>
            {
              this.state.selectedLineItem && <NAVAndTxPlot lineItem={this.state.selectedLineItem} />
            }
          </Col>

        </Row>
      </div>
    } else {
      return <div style={{ position: 'relative', height: '100vh' }}>
        <div style={{
          fontSize: '25px',
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)'
        }}>
          <i className='fa fa-cog fa-spin fa-3x fa-fw'></i>Loading
          </div>
      </div>

    }
  }
}

const formatted = value => new Intl.NumberFormat('en-IN', { maximumFractionDigits: 0 }).format(value);
const formattedPercentage = value => new Intl.NumberFormat('en-IN', { maximumFractionDigits: 2, minimumFractionDigits: 2 }).format(value);

const mapStateToProps = (state, ownProps) => {
  const currentPortfolioValue = state.currentPortfolioValue[ownProps.params.portfolioId];
  return {
    currentPortfolioValue
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    getPortfolioCurrentValue: (portfolioId) => {
      dispatch(actions.getPortfolioCurrentValue(portfolioId))
    }
  }
}
const portfolioNameHeaderColors = ['#3F51B5', '#4caf50'];

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PortfolioCurrentValue)
