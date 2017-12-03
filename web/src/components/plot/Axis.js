import React from 'react';
import * as d3 from 'd3';
import ReactDOM from 'react-dom';

export default class Axis extends React.Component {

    componentDidMount() {
        this.renderAxis();
    }

    componentDidUpdate() {
        this.renderAxis();
    }

    renderAxis() {
        d3.select(ReactDOM.findDOMNode(this)).call(this.props.axis);
    }

    render() {
        const transform = `translate(${this.props.translate.x},${this.props.translate.y})`;
        return <g className="axis" transform={transform} />
    }
}
