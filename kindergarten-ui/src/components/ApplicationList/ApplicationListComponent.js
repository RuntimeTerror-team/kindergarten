import React from 'react'
import Proptypes from 'prop-types';
import AppModal from "../common/AppModal";

const ApplicationListComponent = ({ applications, distributedApplications, queueStatus, cancelApplication}) => {
  
    let table = (
        <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
                    <thead>
                        <tr>
                            <th scope='col'>#</th>
                            <th scope='col'>Vaiko vardas ir pavardė</th>
                            <th scope='col'>Data</th>
                            <th scope='col'>Statusas</th>
                            <th scope='col'>Veiksmai</th>
                        </tr>
                    </thead>

                    <tbody>
                        {applications !== "" ? applications.map(({ applicationId, childFirstName, childLastName, applicationCreationDate, applicationStatus }, index) =>
                            <tr key={applicationId}>
                                <th scope='row'>{index + 1}</th>
                                <td>{childFirstName + " " + childLastName}</td>
                                <td>{applicationCreationDate}</td>
                                <td>{applicationStatus}</td>
                                {console.log("statusas: " + applicationStatus)}
                                <td>{ applicationStatus !== "Atmestas" ? <button className="templatemo-edit-btn-danger" id={applicationId}
                                data-toggle="modal" data-target={`#exampleModal${applicationId}`}>Atšaukti prašymą</button> : null}
                                <AppModal
                                    targetId={applicationId}
                                    modalTitle="Prašymo atšaukimas"
                                    modalMessage="Ar tikrai norite atšaukti prašymą?"
                                    modalApprove={cancelApplication}
                                    modalButtonMessage="Atšaukti prašymą"
                                    modalButtonStyle="danger"
                                    />
                                </td>
                            </tr>
                        ) : null}
                    </tbody>
                </table>
            </div>

        </div>
    )

    let finalTable = (

        <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
                    <thead>
                        <tr>
                            <th scope='col'>#</th>
                            <th scope='col'>Vaiko vardas ir pavardė</th>
                            <th scope='col'>Data</th>
                            <th scope='col'>Statusas</th>
                            <th scope='col'>Gautas darželis</th>
                            <th scope='col'>Eilės numeris</th>
                        </tr>
                    </thead>

                    <tbody>
                        {distributedApplications !== "" ? distributedApplications.map(({ id, childFirstName, childLastName, applicationCreationDate, applicationStatus,
                            approvedKindergarten, waitingNumber }, index) =>
                            <tr key={id}>
                                <th scope='row'>{index + 1}</th>
                                <td>{childFirstName + " " + childLastName}</td>
                                <td>{applicationCreationDate}</td>
                                <td>{applicationStatus}</td>
                                <td>{approvedKindergarten === null ? "-" : approvedKindergarten}</td>
                                <td>{waitingNumber === null ? "-" : waitingNumber}</td>
                            </tr>
                        ) : null}
                    </tbody>
                </table>
            </div>

        </div>
    )

    return (

        applications.length === 0 || applications === null ? <h6 className="text-center">Šiuo metu nesate pateikę jokio prašymo</h6>
        : distributedApplications === null || distributedApplications.length === 0 || queueStatus !== "INACTIVE" ? table : finalTable
    )
}

ApplicationListComponent.propTypes = {
    groups: Proptypes.array
}

export default ApplicationListComponent