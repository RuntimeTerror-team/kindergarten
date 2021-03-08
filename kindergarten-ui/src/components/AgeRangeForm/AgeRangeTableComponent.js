import React from 'react';
import Proptypes from 'prop-types';

let AgeRangeTableComponent = (props) => {


    return (
        <div className="footerBottom">
            <h4 className="my-4 text-center">Amžiaus grupės</h4>

            <table className='table col-4 mx-auto'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Metai</th>
                    </tr>
                </thead>

                <tbody>
                    {props.groups.map((group, index) =>
                        <tr key={group.id}>
                            <td>{index + 1}</td>
                            <td>{group.minAge + " - " + group.maxAge}</td>
                            {/* <button className="btn btn-link" value={group.minAge + "-" + group.maxAge} onClick={props.onDelete}>Ištrinti</button> */}
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

AgeRangeTableComponent.propTypes = {
    groups: Proptypes.array.isRequired,
    onDelete: Proptypes.func.isRequired
}

export default AgeRangeTableComponent;