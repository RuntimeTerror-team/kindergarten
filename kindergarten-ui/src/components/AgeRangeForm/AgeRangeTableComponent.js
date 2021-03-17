import React from 'react';
import Proptypes from 'prop-types';

let AgeRangeTableComponent = (props) => {


    return (
        <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
                    <thead>
                        <tr>
                            <th scope='col'>#</th>
                            <th scope='col'>Metai</th>
                        </tr>
                    </thead>

                    <tbody>
                        {props.groups.map((group, index) =>
                            <tr key={group.id}>
                                <td>{index + 1}</td>
                                <td>{group.minAge + " - " + group.maxAge}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

AgeRangeTableComponent.propTypes = {
    groups: Proptypes.array.isRequired,
    onDelete: Proptypes.func.isRequired
}

export default AgeRangeTableComponent;