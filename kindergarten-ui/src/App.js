import React from 'react';
import { Switch } from 'react-router-dom';
import LoginContainer from './components/Login/LoginContainer';
import ApplicationListContainer from './components/ApplicationList/ApplicationListContainer';
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
import PermissionsContainer from './components/Permissions/PermissionsContainer'
import PrivateRoute from './components/Route/PrivateRoute';
import PublicRoute from './components/Route/PublicRoute';
import LoggingContainer from "./components/Logging/LoggingContainer";

function App(props) {
  return (
    <div>
      <Switch>
        <PublicRoute restricted={false} exact path='/' component={LoginContainer} />
        <PrivateRoute userRole="ROLE_ADMIN" exact path={`${urls.admin.userBase}`} component={AdminUserFormContainer} />
        <PrivateRoute userRole="ROLE_ADMIN" exact path={`${urls.admin.districtBase}`} component={AdminDistrictFormContainer} />
        <PrivateRoute userRole="ROLE_ADMIN" exact path={`${urls.admin.queueBase}`} component={QueueListContainer} />
        <PrivateRoute userRole="ROLE_ADMIN" exact path={`${urls.admin.permissionsBase}`} component={PermissionsContainer} />
        <PrivateRoute userRole="ROLE_ADMIN" exact path={`${urls.admin.loggingBase}`} component={LoggingContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.kindergartenBase}`} component={KindergartenAdministrationContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.kindergartenBase}/new`} component={KindergartenCreationFormContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.kindergartenBase}/:id`} component={KindergartenInfoFormContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups`} component={GroupInfoContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups/new`} component={GroupCreationFormContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.kindergartenBase}/:id/groups/:groupId`} component={GroupCreationFormContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.ageRangeBase}`} component={AgeRangeFormContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.queueBase}`} component={EsQueueListContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.applicationsBase}`} component={ESApprovedApplicationListContainer} />
        <PrivateRoute userRole="ROLE_EDUCATION_SPECIALIST" exact path={`${urls.educationSpecialist.queueBase}/passwordChange`} component={PasswordChangeESContainer} />
        <PrivateRoute userRole="ROLE_GUARDIAN" exact path={`${urls.guardian.applicationBase}`} component={ApplicationListContainer} />
        <PrivateRoute userRole="ROLE_GUARDIAN" exact path={`${urls.guardian.applicationBase}/new`} component={ApplicationContainer} />
        <PrivateRoute userRole="ROLE_GUARDIAN" exact path={`${urls.guardian.applicationBase}/passwordChange`} component={PasswordChangeContainer} />
        <PrivateRoute userRole="ROLE_GUARDIAN" exact path={`${urls.guardian.primaryDataBase}`} component={GuardianPrimaryDataFormContainer} />
        <PrivateRoute userRole="ROLE_GUARDIAN" exact path={`${urls.guardian.healthFormBase}`} component={HealthFormListContainer} />
        <PublicRoute restricted={false} exact path='/not-authorized' component={NotAuthorized} />
        <PublicRoute restricted={false} exact path='/forbidden' component={Forbidden} />
        <PublicRoute restricted={false} exact path='/error' component={Error} />
        <PublicRoute restricted={false} path='*' component={NoMatchComponent} />
        <PrivateRoute userRole="ROLE_ADMIN" exact path={`${urls.admin.loggingBase}`} component={LoggingContainer} />

        <PublicRoute restricted={false} component={NoMatchComponent} />
      </Switch>
      {props.children}
    </div>
  )
}

export default App;


