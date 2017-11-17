import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Row, Col, Navbar } from 'react-bootstrap';
import * as moment from 'moment';
import * as actions from '../actions';
import Grid from './common/Grid';
import NAVAndTxPlot from './plot/NAVAndTxPlot';
import StatementUpload from './StatementUpload';

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
    if (currentPortfolioValue.portfolioValueOneDayDelta) {
      const cost = currentPortfolioValue.portfolioValueOneDayDelta.today.cost
      const value = currentPortfolioValue.portfolioValueOneDayDelta.today.value;
      const returns = value - cost;
      const returnPct = 100 * returns / cost;

      return <Row>
        <Col md={6}>
          <div style={{ backgroundColor: '#f5f5f5', padding: '2px', fontSize: '10px', boxShadow: '0 0 4px rgba(0,0,0,.4)', marginLeft: '-15px' }}>
            <Grid
              data={currentPortfolioValue.portfolioValueOneDayDelta.schemeValues}
              subHeaders={currentPortfolioValue.portfolioValueOneDayDelta}
              columns={[
                {
                  headerName: 'Scheme',
                  valueFunction: lineItem => lineItem.scheme && lineItem.scheme.name,
                  displayFunction: (value, lineItem) => <a style={{ cursor: 'pointer' }}
                    onClick={() => this.selectScheme(lineItem.today)}>
                    {value}
                  </a>,
                  valueDataType: 'text'
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

        <Col md={6} style={{margin: '0px -15px'}}>
          {
            this.state.selectedLineItem && <NAVAndTxPlot lineItem={this.state.selectedLineItem} />
          }
        </Col>

      </Row>
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
  const currentPortfolioValue = state.currentPortfolioValue;
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

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PortfolioCurrentValue)
