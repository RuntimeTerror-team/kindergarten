import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import AdminPage from './components/Pages/AdminPage';

function App(props) {
  return (
  <div>
    <Switch>
      <Route exact path='/' component={LoginContainer} />
      <Route exact path='/admin' component={AdminPage} />
    </Switch>
    {props.children}
  </div>
  )
}

export default App;
