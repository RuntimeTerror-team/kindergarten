import React, { useContext } from 'react';
import ServicesContext from '../../context/ServicesContext';

const AdminPage = () => {
    const { userService } = useContext(ServicesContext);

    return (
    <div>
        {userService.getUserRole() === "ADMIN" ? <h1>Admin page</h1> : <h1>Access denied</h1>}
    </div>
    )
}

export default AdminPage;