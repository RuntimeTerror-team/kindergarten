import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { isLogin, getLoggedInRole, hashCode } from '../../utils/utils';

const PrivateRoute = ({ component: Component, userRole, ...rest }) => {
    return (
        // Show the component only when the user is logged in
        // Otherwise, redirect the user to not authorized page /not-authorized to inform and suggest logging in
        <Route {...rest} render={props => (
            isLogin() && hashCode(userRole) === +getLoggedInRole() ?
                <Component {...props} />
                : <Redirect to="/not-authorized" />
        )} />
    );
};

export default PrivateRoute;