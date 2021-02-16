import React from 'react';
import Proptypes from "prop-types";

const AdminUserTableComponent = ({ users }) => {
    return (
        <table id="districtTable" className="table col-10 mx-auto mt-3">
            <thead>
                <tr>
                    <th scope="col" style={{ width: "30px" }}>
                        #
                        </th>
                    <th scope="col">Naudotojo vardas</th>
                    <th scope="col">Rolė</th>
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
    )
}

AdminUserTableComponent.propTypes = {
    users: Proptypes.array.isRequired
}

export default AdminUserTableComponent;