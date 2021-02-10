import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import EducationSpecialistPageContainer from './components/EducationSpecialistPage/EducationSpecialistPageContainer';
import GuardianPageContainer from './components/GuardianPage/GuardianPageContainer';
import NoMatchComponent from './components/NoMatch/NoMatchComponent';
import AdminUserFormContainer from './components/AdminUserForm/AdminUserFormContainer';
import AdminDistrictFormContainer from './components/AdminDistrictForm/AdminDistrictFormContainer';

function App(props) {
  return (
  <div>
    <Switch>
      <Route exact path='/' component={LoginContainer} />
      {/* <Route exact path='/admin' component={} /> */}
      <Route exact path='/admin/users' component={AdminUserFormContainer} />
      <Route exact path='/admin/districts' component={AdminDistrictFormContainer} />
      <Route exact path='/education-specialist' component={EducationSpecialistPageContainer} />
      <Route exact path='/guardian' component={GuardianPageContainer} />
      <Route path='*' component={NoMatchComponent} />
      <Route component={NoMatchComponent} />
    </Switch>
    {props.children}
  </div>
  )
}

export default App;
