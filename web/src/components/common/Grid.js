import React, { Component } from 'react';

/*
ColumnConfig: 
    [id]
    headerName
    valueFunction [(row => cellValue)]
    displayFunction [(cellValue => cellDisplay)]
    valueDataType [text, number]
    [formatter]
    [width]
    [movable]
    [removable]

rowIdFunction[row => id]

*/

export default class Grid extends Component {

    constructor(props) {
        super(props);
        this.sort = this.sort.bind(this);
        this.createComparator = this.createComparator.bind(this);

        const orderBy = { order: 1, column: this.props.columns[0] };
        this.state = {
            orderBy: orderBy,
            data: this.props.data.sort(this.createComparator(orderBy))
        };
    }
    
    shouldComponentUpdate(nextProps, nextState){
        return true;
    }

    sort(column) {
        const orderBy = this.state.orderBy;
        if (orderBy.column.headerName === column.headerName) {
            orderBy.order *= -1;
        } else {
            orderBy.order = 1;
            orderBy.column = column;
        }

        this.setState({
            orderBy: orderBy,
            data: this.props.data.sort(this.createComparator(orderBy))
        });
    }

    createComparator(orderBy) {
        const comparator = (d1, d2) => {
            const f = orderBy.column.valueFunction;
            const v1 = f(d1);
            const v2 = f(d2);
            if (v1 === v2) return 0;
            return orderBy.order * (v1 < v2 ? -1 : 1);
        }
        return comparator;
    }

    render() {
        const headers = this.props.columns.map(
            (column) =>
                <div
                    key={column.headerName}
                    className={`grid-header ${getCellClassName(column.valueDataType)} ${column.className}`}
                    style={{ cursor: 'pointer' }} onClick={() => this.sort(column)}>
                    {column.headerName}
                </div>
        )

        const subHeaders = this.props.subHeaders && this.props.columns.map(
            (column) =>
                <div
                    key={column.headerName}
                    className={`grid-subheader ${getCellClassName(column.valueDataType)}`}>
                    {column.displayFunction(column.valueFunction(this.props.subHeaders))}
                </div>
        )

        const rows = this.state.data.map(
            (row) =>
                <div key={this.props.rowKeyFunction(row)} className='grid-row'>
                    {
                        this.props.columns.map(
                            (column) =>
                                <div key={column.headerName} className={`grid-cell ${getCellClassName(column.valueDataType)}`}>
                                    {column.displayFunction(column.valueFunction(row), row)}
                                </div>
                        )
                    }
                </div>
        )

        return <div>
            {headers}
            {subHeaders && <div className='grid-row'>{subHeaders}</div>}
            {rows}
        </div>
    }
}

function getCellClassName(dataType) { return dataType === 'text'||dataType === 'date' ? 'grid-text-cell' : 'grid-number-cell' };


