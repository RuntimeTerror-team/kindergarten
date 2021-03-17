import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { isLogin, getLoggedInRole, hashCode } from '../../utils/utils';

const PrivateRoute = ({ component: Component, userRole, ...rest }) => {
    return (
        <Route {...rest} render={props => (
            isLogin() && hashCode(userRole) === +getLoggedInRole() ?
                <Component {...props} />
                : <Redirect to="/not-authorized" />
        )} />
    );
};

export default PrivateRoute;