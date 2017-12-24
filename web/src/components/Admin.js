import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Row, Col, Button } from 'react-bootstrap';
import * as actions from '../actions';
import * as moment from 'moment';

class Admin extends Component {
    constructor(props) {
        super(props);
        this.loadCurrentNAV = this.loadCurrentNAV.bind(this);
    }

    loadCurrentNAV() {
        this.props.loadCurrentNAV();
    }

    render() {
        return <Row>
            <Col md={12}>
                <Row>
                    <Col md={12}>
                        <div style={{ padding: '0px 0px 10px 0px'}}>
                            <Button bsStyle="primary" bsSize="large" onClick={() => this.loadCurrentNAV()}>Load current NAV</Button>
                        </div>
                    </Col>
                </Row>

                {
                    this.props.currentNAVLoad && <Row>
                        <Col md={12}>
                            <h3>Load id: {this.props.currentNAVLoad.id}</h3>
                            <h3>Loaded at: {moment(this.props.currentNAVLoad.loadTime).format("DD-MMM-YYYY")}</h3>
                            <h3>Latest NAV date: {moment(this.props.currentNAVLoad.latestNavDate).format("DD-MMM-YYYY")}</h3>
                            <h3>Max occurring date: {moment(this.props.currentNAVLoad.maxOccuringDate).format("DD-MMM-YYYY")}</h3>
                        </Col>
                    </Row>
                }

            </Col>
        </Row>
    }
}

const mapStateToProps = (state, ownProps) => {
    const currentNAVLoad = state.currentNAVLoad;
    return {
        currentNAVLoad
    }
}

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        loadCurrentNAV: () => {
            dispatch(actions.loadCurrentNAV())
        },
        loadNAV: (from, to) => {
            dispatch(actions.loadNAV())
        }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Admin)