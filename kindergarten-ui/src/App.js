import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import GuardianPageContainer from './components/GuardianPage/GuardianPageContainer';
import NoMatchComponent from './components/NoMatch/NoMatchComponent';
import AdminUserFormContainer from './components/AdminUserForm/AdminUserFormContainer';
import AdminDistrictFormContainer from './components/AdminDistrictForm/AdminDistrictFormContainer';
import KindergartenAdministrationContainer from './components/KindergartenAdministration/KindergartenAdministrationContainer';
import KindergartenCreationFormContainer from './components/KindergartenCreationForm/KindergartenCreationFormContainer';
import AgeRangeFormContainer from './components/AgeRangeForm/AgeRangeFormContainer';
import KindergartenInfoFormContainer from './components/KindergartenInfoForm/KindergartenInfoFormContainer';
import GroupInfoContainer from './components/GroupInfo/GroupInfoContainer';
import GroupCreationFormContainer from './components/GroupCreationForm/GroupCreationFormContainer';

function App(props) {
  return (
  <div>
    <Switch>
      <Route exact path='/' component={LoginContainer} />
      <Route exact path='/admin/users' component={AdminUserFormContainer} />
      <Route exact path='/admin/districts' component={AdminDistrictFormContainer} />
      <Route exact path='/education-specialist/kindergartens' component={KindergartenAdministrationContainer} />
      <Route exact path='/education-specialist/kindergartens/new' component={KindergartenCreationFormContainer} />
      <Route exact path='/education-specialist/kindergartens/:id' component={KindergartenInfoFormContainer} />
      <Route exact path='/education-specialist/kindergartens/:id/groups' component={GroupInfoContainer} />
      <Route exact path='/education-specialist/kindergartens/:id/groups/new' component={GroupCreationFormContainer} />
      <Route exact path='/education-specialist/age-ranges' component={AgeRangeFormContainer} />
      <Route exact path='/guardian' component={GuardianPageContainer} />
      <Route path='*' component={NoMatchComponent} />
      <Route component={NoMatchComponent} />
    </Switch>
    {props.children}
  </div>
  )
}

export default App;
