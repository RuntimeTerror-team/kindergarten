import React from 'react';
import Proptypes from "prop-types";

const AdminUserTableComponent = ({ users }) => {
    return (
        <div className="templatemo-content-widget no-padding col-12">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
                    <thead>
                        <tr>
                            <td><button className="btn text-white templatemo-sort-by"># <span className="caret"></span></button></td>
                            <td><button className="btn text-white templatemo-sort-by">Prisijungimo vardas <span className="caret"></span></button></td>
                            <td><button className="btn text-white templatemo-sort-by">Rolė<span className="caret"></span></button></td>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map(({ username, role }, index) => (
                            <tr key={username}>
                                <th scope="row">{index + 1}</th>
                                <td>{username}</td>
                                <td>{role}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

AdminUserTableComponent.propTypes = {
    users: Proptypes.array.isRequired
}

export default AdminUserTableComponent;