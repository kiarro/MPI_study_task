import './DragonBase.css'
import leftArrow from './left-arrow.png'
import rightArrow from './right-arrow.png'
import React from 'react';

class DragonBase extends React.Component {
  render() {
    return (
      <table>
          <thead>
            {this.props.headers.map((header, i) => 
              <th scope="col" key={i}>{header}</th>
            )}
          </thead>
          <tbody>
          {this.props.rows.map((row, i) => 
            <tr key={i}>
              {row.map((cell, i) =>
                (this.props.rowHeaders && i < 1) ? (
                  <th scope="row" key={i}>{cell}</th>
                ) : (
                  <td key={i}>{cell}</td>
                )
              )}
            </tr>
           )}
        </tbody>
        <tfoot>
              <th>{this.props.page}</th>
              <th><img src={leftArrow} className="Arrow" alt="to left" /></th>
              <th><img src={rightArrow} className="Arrow" alt="to right" /></th>
          </tfoot>
      </table>
    );
  }
}


export default DragonBase;