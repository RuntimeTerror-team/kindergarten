import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { isLogin, getLoggedInRole } from '../../utils/utils';

const PrivateRoute = ({ component: Component, userRole, ...rest }) => {
    return (
        // Show the component only when the user is logged in
        // Otherwise, redirect the user to homepage / to login
        <Route {...rest} render={props => (
            isLogin() && userRole === getLoggedInRole() ?
                <Component {...props} />
                : <Redirect to="/not-authorized" />
        )} />
    );
};

export default PrivateRoute;