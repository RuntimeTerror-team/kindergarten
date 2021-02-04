import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import AdminPageContainer from './components/AdminPage/AdminPageContainer';
import EducationSpecialistPageContainer from './components/EducationSpecialistPage/EducationSpecialistPageContainer';
import GuardianPageContainer from './components/GuardianPage/GuardianPageContainer';
import NoMatchComponent from './components/NoMatch/NoMatchComponent';
function App(props) {
  return (
  <div>
    <Switch>
      <Route exact path='/' component={LoginContainer} />
      <Route exact path='/admin' component={AdminPageContainer} />
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
