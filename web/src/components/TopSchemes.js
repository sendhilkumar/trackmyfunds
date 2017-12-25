import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Row, Col } from 'react-bootstrap';
import * as actions from '../actions';
import Grid from './common/Grid';

class TopSchemes extends Component {
    componentDidMount() {
        const criteria = this.props.location.query && this.props.location.query.criteria ? this.props.location.query.criteria:null
        this.props.getTopSchemes(criteria);
    }

    render() {
        return this.props.topSchemes.length>0?
         <Row>
            <Col md={12}>
                <div>
                    <Row>
                        <Col md={12}>
                            <div style={{ borderRadius: '2px', backgroundColor: '#f5f5f5' }}>
                                <Row>
                                    <Col md={12}>
                                        <div style={{ padding: '20px', fontSize: '10px' }}>
                                            <Grid
                                                data={this.props.topSchemes}
                                                columns={[
                                                    {
                                                        headerName: 'Scheme',
                                                        valueFunction: lineItem => lineItem.scheme.name,
                                                        displayFunction: (value, lineItem) => <a style={{ cursor: 'pointer' }}
                                                            onClick={() => this.selectScheme(lineItem)}>
                                                            {value}
                                                        </a>,
                                                        valueDataType: 'text',
                                                        className: 'scheme-name-in-grid'
                                                    },
                                                    {
                                                        headerName: `1d`,
                                                        valueFunction: lineItem => lineItem.returns['1d'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `1w`,
                                                        valueFunction: lineItem => lineItem.returns['1w'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `2w`,
                                                        valueFunction: lineItem => lineItem.returns['2w'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `1m`,
                                                        valueFunction: lineItem => lineItem.returns['1m'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `6m`,
                                                        valueFunction: lineItem => lineItem.returns['6m'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `1y`,
                                                        valueFunction: lineItem => lineItem.returns['1y'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `2y`,
                                                        valueFunction: lineItem => lineItem.returns['2y'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `3y`,
                                                        valueFunction: lineItem => lineItem.returns['3y'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `4y`,
                                                        valueFunction: lineItem => lineItem.returns['4y'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    },
                                                    {
                                                        headerName: `5y`,
                                                        valueFunction: lineItem => lineItem.returns['5y'],
                                                        displayFunction: value => formattedPercentage(value)
                                                    }
                                                ]}
                                                rowKeyFunction={lineItem => lineItem.scheme.name} />

                                        </div>
                                    </Col>
                                </Row>
                            </div>
                        </Col>
                    </Row>
                </div>
            </Col>
        </Row>: <div>Loading..</div>
    }
}
const formattedPercentage = value => new Intl.NumberFormat('en-IN', { maximumFractionDigits: 2, minimumFractionDigits: 2 }).format(value);

const mapStateToProps = (state, ownProps) => {
    const topSchemes = state.topSchemes;
    const topSchemesAsArray =Object.keys(topSchemes).map(k => topSchemes[k]);
    return {
        topSchemes:topSchemesAsArray
    }
}

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        getTopSchemes: (criteria) => {
            dispatch(actions.getTopSchemes(criteria))
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(TopSchemes)
