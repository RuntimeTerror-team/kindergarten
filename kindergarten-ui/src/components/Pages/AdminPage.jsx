import React, { useContext, useState, useEffect } from 'react';
import ServicesContext from '../../context/ServicesContext';

const AdminPage = () => {
    const { userService } = useContext(ServicesContext);
    const [userRole, setUserRole] = useState(userService.getUserRole());

    userService.updateUserRole = () => setUserRole(userService.getUserRole());

    useEffect(() => {
        if (userRole.toLowerCase() === "ADMIN") {
            setUserRole("ADMIN")
        }
    }, [userRole])

    return (
    <div>
        {userRole === "ADMIN" ? <h1>Admin page</h1> : <h1>Access denied</h1>}
    </div>
    )
}

export default AdminPage;