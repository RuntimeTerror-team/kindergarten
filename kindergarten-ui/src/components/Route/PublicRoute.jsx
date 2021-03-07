import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { isLogin } from '../../utils/utils';

const PublicRoute = ({ component: Component, restricted, ...rest }) => {
    return (
        <Route {...rest} render={props => (
            isLogin() && restricted ?
                <Redirect to="/forbidden" />
                : <Component {...props} />
        )} />
    );
};

export default PublicRoute;