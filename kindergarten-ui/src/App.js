import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import AdminPage from './components/Pages/AdminPage';
import HomePage from "./components/Pages/HomePage";

function App(props) {
  return (
  <div>
    <Switch>
      <Route exact path='/' component={HomePage} />
      <Route exact path='/admin' component={AdminPage} />
      <Route exact path='/login' component={LoginContainer} />
    </Switch>
    {props.children}
  </div>
  )
}

export default App;
