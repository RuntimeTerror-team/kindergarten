// import React, { useContext, useState, useEffect } from 'react';
import React, { useContext } from 'react';
import ServicesContext from '../../context/ServicesContext';

const AdminPage = () => {
    const { userService } = useContext(ServicesContext);
    // const [userRole, setUserRole] = useState(userService.getUserRole());

    // userService.updateUserRole = () => setUserRole(userService.getUserRole());

    // useEffect(() => {
    //     setUserRole(userService.getUserRole())
    // }, [userRole])

    return (
    <div>
        {userService.getUserRole() === "ADMIN" ? <h1>Admin page</h1> : <h1>Access denied</h1>}
    </div>
    )
}

export default AdminPage;