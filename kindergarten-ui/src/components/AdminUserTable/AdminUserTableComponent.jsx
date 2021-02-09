import React from 'react';

const AdminUserTableComponent = ({ users }) => {
    return (
        <div className="col-12 mt-3">
            <table id="districtTable" className="table col-12 mx-auto">
                <thead>
                    <tr>
                        <th scope="col" style={{ width: "30px" }}>
                            #
                        </th>
                        <th scope="col">Naudotojo vardas</th>
                        <th scope="col">Vardas</th>
                        <th scope="col">Pavardė</th>
                        <th scope="col">Rolė</th>
                    </tr>
                </thead>

                <tbody>
                    {users.map(({ username, firstName, lastName, role }, index) => (
                        <tr key={username}>
                            <th scope="row">{index + 1}</th>
                            <td>{username}</td>
                            <td>{firstName}</td>
                            <td>{lastName}</td>
                            <td>{role}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default AdminUserTableComponent;