import React, { Component } from 'react';
import { Row, Col, Grid } from 'react-bootstrap';
import { Link } from 'react-router'

export default class App extends Component {
    render() {
        return <Grid fluid>
            <Row>
                <Col xs={12} md={12} style={{ backgroundColor: 'rgb(34, 34, 34)'}}>
                    <div className='app-header'>
                        <Row>
                            <Col xs={6} md={6} style={{ fontSize: '20px', padding: '10px 0px 10px 10px' }}>
                                <Link to="/web"><strong>Track my funds</strong></Link>
                            </Col>

                            <Col xs={6} md={6} style={{ fontSize: '16px', padding: '15px 10px 5px 10px' }}>
                                <div style={{ float: 'right' }}>
                                    <strong>
                                        <Link to="/web/upload">Upload statement</Link>
                                    </strong>
                                </div>
                            </Col>
                        </Row>
                    </div>
                </Col>
            </Row>

            <Row>
                <Col xs={12} md={12}>
                    {this.props.children}
                </Col>
            </Row>

        </Grid>
    }
}