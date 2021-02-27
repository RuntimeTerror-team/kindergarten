import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import GuardianPageContainer from './components/GuardianPage/GuardianPageContainer';
import ApplicationContainer from './components/ApplicationForm/ApplicationContainer'
import PasswordChangeContainer from './components/PasswordChange/PasswordChangeContainer'
import PasswordChangeESContainer from './components/PasswordChange/PasswordChangeESContainer'
import NoMatchComponent from './components/ErrorHandling/NoMatchComponent';
import AdminUserFormContainer from './components/AdminUserForm/AdminUserFormContainer';
import AdminDistrictFormContainer from './components/AdminDistrictForm/AdminDistrictFormContainer';
import KindergartenAdministrationContainer from './components/KindergartenAdministration/KindergartenAdministrationContainer';
import KindergartenCreationFormContainer from './components/KindergartenCreationForm/KindergartenCreationFormContainer';
import AgeRangeFormContainer from './components/AgeRangeForm/AgeRangeFormContainer';
import KindergartenInfoFormContainer from './components/KindergartenInfoForm/KindergartenInfoFormContainer';
import GroupInfoContainer from './components/GroupInfo/GroupInfoContainer';
import GroupCreationFormContainer from './components/GroupCreationForm/GroupCreationFormContainer';
import Error from './components/ErrorHandling/Error';
import Forbidden from './components/ErrorHandling/Forbidden';
import NotAuthorized from './components/ErrorHandling/NotAuthorized';
import urls from './constants/urls'
import GuardianPrimaryDataFormContainer from './components/GuardianPrimaryDataForm/GuardianPrimaryDataFormContainer';
import QueueListContainer from './components/QueueList/QueueListContainer';
import EsQueueListContainer from './components/EsQueueList/EsQueueListContainer';
import HealthFormListContainer from './components/HealthFormList/HealthFormListContainer';

function App(props) {
  return (
    <div>
      <Switch>
        <Route exact path='/' component={LoginContainer} />
        <Route exact path={`${urls.admin.userBase}`} component={AdminUserFormContainer} />
        <Route exact path={`${urls.admin.districtBase}`} component={AdminDistrictFormContainer} />
        <Route exact path={`${urls.admin.queueBase}`} component={QueueListContainer} />
        <Route exact path={`${urls.educationSpecialist.kindergartenBase}`} component={KindergartenAdministrationContainer} />
        <Route exact path={`${urls.educationSpecialist.kindergartenBase}/new`} component={KindergartenCreationFormContainer} />
        <Route exact path={`${urls.educationSpecialist.kindergartenBase}/:id`} component={KindergartenInfoFormContainer} />
        <Route exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups`} component={GroupInfoContainer} />
        <Route exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups/new`} component={GroupCreationFormContainer} />
        <Route exact path={`${urls.educationSpecialist.ageRangeBase}`} component={AgeRangeFormContainer} />
        <Route exact path={`${urls.educationSpecialist.queueBase}`} component={EsQueueListContainer} />
        <Route exact path={`${urls.educationSpecialist.queueBase}/passwordChange`} component={PasswordChangeESContainer} />
        <Route exact path={`${urls.guardian.applicationBase}`} component={GuardianPageContainer} />
        <Route exact path={`${urls.guardian.applicationBase}/new`} component={ApplicationContainer} />
        <Route exact path={`${urls.guardian.applicationBase}/passwordChange`} component={PasswordChangeContainer} />
        <Route exact path={`${urls.guardian.primaryDataBase}`} component={GuardianPrimaryDataFormContainer} />
        <Route exact path={`${urls.guardian.healthFormBase}`} component={HealthFormListContainer} />
        <Route exact path='/not-authorized' component={NotAuthorized} />
        <Route exact path='/forbidden' component={Forbidden} />
        <Route exact path='/error' component={Error} />
        <Route path='*' component={NoMatchComponent} />
        <Route component={NoMatchComponent} />
      </Switch>
      {props.children}
    </div>
  )
}

export default App;
