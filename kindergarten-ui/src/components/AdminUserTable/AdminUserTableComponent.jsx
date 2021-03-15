import React from 'react';
import Proptypes from "prop-types";
import { BsSearch } from "react-icons/bs";
import { BsChevronLeft, BsChevronRight } from "react-icons/bs";


const AdminUserTableComponent = ({ users, downloadUserData, restoreOriginalPassword, updateSearchInputValue, currentPage,
    totalPages,
    firstPage,
    prevPage,
    lastPage,
    nextPage, }) => {
    return (
        <div>
            <div className="col-10 pt-2">
                <form className="form-inline">
                    <div className="form-inline mr-sm-3"><BsSearch size="18px" color="#4285F4" /></div>
                    <input className="form-control" type="search" style={{ width: "350px" }} placeholder="Įveskite prisijungimo vardą..." onChange={updateSearchInputValue} aria-label="Search" ></input>
                </form>
            </div>
            <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
                <div className="panel panel-default table-responsive">
                    <table className="table table-striped table-bordered templatemo-user-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Prisijungimo vardas</th>
                                <th>Rolė</th>
                                <th>Veiksmai</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.length > 0 && users.map(({ username, role }, index) => (
                                <tr key={username}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{username}</td>
                                    <td>{role.type === "GUARDIAN" ? "Vaiko atstovas" : role.type === "ADMIN" ? "Administratorius" : "Švietimo specialistas"}</td>
                                    <td>
                                        {(role.type === "GUARDIAN" || role.type === "EDUCATION_SPECIALIST")
                                            && <button className="templatemo-edit-btn mr-2" value={username} onClick={restoreOriginalPassword}>Atstatyti slaptažodį</button>}
                                        {role.type === "GUARDIAN" && <button className="templatemo-edit-btn" id={username} onClick={downloadUserData}>Atsisiųsti duomenis</button>}
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                <div>
                    <div className="button-toolbar mt-5 mb-4" role="toolbar" aria-label="Toolbar with button groups">
                        <div className="btn-group mr-2" role="group" aria-label="First group">
                            <div className="pr-3" style={{ color: "#AFAFAF", alignItems: "center" }}>
                                <p>
                                    Rodoma {currentPage} iš {totalPages} puslapių{" "}
                                </p>
                            </div>
                        </div>

                        <div className="btn-group mr-2" role="group" aria-label="First group">
                            <button type="button mr-2" className="templatemo-blue-button " onClick={firstPage}>
                                Pirmas
                    </button>
                        </div>

                        <div className="btn-group mr-2" role="group" aria-label="Second group">
                            <button type="button mr-2" className="templatemo-blue-button " onClick={prevPage}>
                                <BsChevronLeft />
                            </button>
                        </div>

                        <div className="btn-group mr-2" role="group" aria-label="Second group">
                            <button type="button mr-2" className="templatemo-blue-button " onClick={nextPage}>
                                <BsChevronRight />
                            </button>
                        </div>

                        <div className="btn-group" role="group" aria-label="Second group">
                            <button type="button" className="templatemo-blue-button" onClick={lastPage}>
                                Paskutinis
                    </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

AdminUserTableComponent.propTypes = {
    users: Proptypes.array.isRequired
}

export default AdminUserTableComponent;