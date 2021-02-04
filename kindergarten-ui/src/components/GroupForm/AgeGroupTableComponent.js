import React from 'react';
import Proptypes from 'prop-types';

let AgeGroupTableComponent = (props) => {


    return (
        <div>
            <h2 className="my-4">Amžiaus grupės</h2>

            <table className='table col-8 mx-auto'>
                <thead>
                    <tr>
                        <th scope='col' style={{width: "40px"}}>#</th>
                        <th scope='col'>Metai</th>
                    </tr>
                </thead>

                <tbody>
                    {props.groups.map((group, index) =>
                        <tr>
                            <td>{index + 1}</td>
                            <td className="pl-5">{group.minAge + "-" + group.maxAge}<button className="btn btn-link" value={group.minAge + "-" + group.maxAge} onClick={props.onDelete}>Ištrinti</button></td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )


}

AgeGroupTableComponent.propTypes = {

    groups: Proptypes.array.isRequired,
    onDelete: Proptypes.func.isRequired
}

export default AgeGroupTableComponent;