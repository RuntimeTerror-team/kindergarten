import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import GuardianPageContainer from './components/GuardianPage/GuardianPageContainer';
import ApplicationContainer from './components/ApplicationForm/ApplicationContainer'
import ESApprovedApplicationListContainer from './components/ApprovedApplicationList/ESApprovedApplicationListContainer'
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
import PrivateRoute from './components/Route/PrivateRoute';
import PublicRoute from './components/Route/PublicRoute';

function App(props) {
  return (
    <div>
      <Switch>
        <PublicRoute restricted={false} exact path='/' component={LoginContainer} />
        <PublicRoute restricted={true} exact path={`${urls.admin.userBase}`} component={AdminUserFormContainer} />
        <PrivateRoute exact path={`${urls.admin.districtBase}`} component={AdminDistrictFormContainer} />
        <PrivateRoute exact path={`${urls.admin.queueBase}`} component={QueueListContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.kindergartenBase}`} component={KindergartenAdministrationContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.kindergartenBase}/new`} component={KindergartenCreationFormContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.kindergartenBase}/:id`} component={KindergartenInfoFormContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups`} component={GroupInfoContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups/new`} component={GroupCreationFormContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups/:groupId`} component={GroupCreationFormContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.ageRangeBase}`} component={AgeRangeFormContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.queueBase}`} component={EsQueueListContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.applicationsBase}`} component={ESApprovedApplicationListContainer} />
        <PrivateRoute exact path={`${urls.educationSpecialist.queueBase}/passwordChange`} component={PasswordChangeESContainer} />
        <PrivateRoute exact path={`${urls.guardian.applicationBase}`} component={GuardianPageContainer} />
        <PrivateRoute exact path={`${urls.guardian.applicationBase}/new`} component={ApplicationContainer} />
        <PrivateRoute exact path={`${urls.guardian.applicationBase}/passwordChange`} component={PasswordChangeContainer} />
        <PrivateRoute exact path={`${urls.guardian.primaryDataBase}`} component={GuardianPrimaryDataFormContainer} />
        <PrivateRoute exact path={`${urls.guardian.healthFormBase}`} component={HealthFormListContainer} />
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
