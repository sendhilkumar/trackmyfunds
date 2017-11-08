import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Row, Col } from 'react-bootstrap';
import * as moment from 'moment';
import * as actions from '../actions';
import Grid from './common/Grid';
import NAVAndTxPlot from './plot/NAVAndTxPlot';

export default class FundSearch extends Component {

    render() {
        return <Row>
            <Col md={12}>
                <div style={{ padding: '15px' }}>
                    <Row>
                        <Col md={12}>
                            <div style={{ padding: '0px 0px 10px 0px', fontSize: '20px', color: '#f5f5f5' }}>
                                Find funds
                            </div>
                        </Col>
                    </Row>

                    <Row>
                        <Col md={12}>
                            <div style={{ borderRadius: '2px', backgroundColor: '#f5f5f5' }}>
                                <Row>
                                    <Col md={12}>
                                        <div style={{ padding: '20px', fontSize: '10px' }}>
                                            <Grid
                                                data={this.props.schemes}
                                                columns={[
                                                    {
                                                        headerName: 'Scheme',
                                                        valueFunction: lineItem => lineItem.scheme.name,
                                                        displayFunction: (value, lineItem) => <a style={{ cursor: 'pointer' }}
                                                            onClick={() => this.selectScheme(lineItem)}>
                                                            {value}
                                                        </a>,
                                                        valueDataType: 'text'
                                                    },
                                                    {
                                                        headerName: `1d`,
                                                        valueFunction: lineItem => lineItem.d1,
                                                        displayFunction: value => formatted(value)
                                                    },
                                                    {
                                                        headerName: `1y`,
                                                        valueFunction: lineItem => lineItem.y1,
                                                        displayFunction: value => formatted(value)
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
        </Row>
    }
}

