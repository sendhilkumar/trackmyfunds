import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Row, Col } from 'react-bootstrap';
import * as actions from '../actions';
import PortfolioCurrentValue from './PortfolioCurrentValue';
import PortfolioHistory from './PortfolioHistory';

class Portfolios extends Component {
    componentDidMount() {
        this.props.getPortfolios();
    }

    render() {
        const keys = Object.keys(this.props.portfolios);
        return <div>
            <Row>
                <Col mdOffset={2} md={8}>
                    <PortfolioHistory />
                </Col>
            </Row>

            <Row>
                <Col xs={12} md={12} style={{ margin: '10px 0px 10px 10px' }}>
                    <PortfolioCurrentValue subHeadersOnly={true} params={{ portfolioId: 0, portfolioName: 'All' }} />
                </Col>
            </Row>

            {
                keys.map(key => <Row key={this.props.portfolios[key].id}>
                    <Col xs={12} md={12} style={{ margin: '10px 0px 10px 10px' }}>
                        <PortfolioCurrentValue params={{ portfolioId: this.props.portfolios[key].id, portfolioName: this.props.portfolios[key].name }} />
                    </Col>
                </Row>)
            }
        </div>

    }
}
const mapStateToProps = (state, ownProps) => {
    const portfolios = state.portfolios;
    return {
        portfolios
    }
}

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getPortfolios: () => {
            dispatch(actions.getPortfolios())
        }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Portfolios)