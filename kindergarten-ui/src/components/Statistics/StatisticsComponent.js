import React from 'react'

const StatisticsComponent = ({ firstPlace, secondPlace, thirdPlace, fourthPlace, fifthPlace, fromEndFirstPlace, fromEndSecondPlace,
    fromEndThirdPlace, fromEndFourthPlace, fromEndFifthPlace, firstPlaceAddress, secondPlaceAddress, thirdPlaceAddress, fourthPlaceAddress,
    fifthPlaceAddress, fromEndFirstPlaceAddress, fromEndSecondPlaceAddress, fromEndThirdPlaceAddress, fromEndFourthPlaceAddress, fromEndFifthPlaceAddress,
    nrOfApplications, nrOfKindergartenSpots, nrOfWaitingApplications, nrOfApprovedApplications }) => {
    return (
        <div className="mx-auto col-10 mt-3">
            <h1 className="mb-3" style={{ marginLeft: "200px" }}>Populiariausių darželių Top 5</h1>
            <table id="groupTable" className='table col-12 mb-3'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "50px" }}>Vieta</th>
                        <th scope='col'>Pavadinimas</th>
                        <th scope='col'>Adresas</th>
                    </tr>
                </thead>

                <tbody>

                    <tr>
                        <th scope='row'>{1}</th>
                        <td>{firstPlace}</td>
                        <td>{firstPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{2}</th>
                        <td>{secondPlace}</td>
                        <td>{secondPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{3}</th>
                        <td>{thirdPlace}</td>
                        <td>{thirdPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{4}</th>
                        <td>{fourthPlace}</td>
                        <td>{fourthPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{5}</th>
                        <td>{fifthPlace}</td>
                        <td>{fifthPlaceAddress}</td>
                    </tr>

                </tbody>
            </table>
            <h1 className="mb-3" style={{ marginLeft: "200px" }}>Nepopuliariausių darželių Top 5</h1>
            <table id="groupTable" className='table col-12 mb-5'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "50px" }}>Vieta</th>
                        <th scope='col'>Pavadinimas</th>
                        <th scope='col'>Adresas</th>
                    </tr>
                </thead>

                <tbody>

                    <tr>
                        <th scope='row'>{1}</th>
                        <td>{fromEndFirstPlace}</td>
                        <td>{fromEndFirstPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{2}</th>
                        <td>{fromEndSecondPlace}</td>
                        <td>{fromEndSecondPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{3}</th>
                        <td>{fromEndThirdPlace}</td>
                        <td>{fromEndThirdPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{4}</th>
                        <td>{fromEndFourthPlace}</td>
                        <td>{fromEndFourthPlaceAddress}</td>
                    </tr>
                    <tr>
                        <th scope='row'>{5}</th>
                        <td>{fromEndFifthPlace}</td>
                        <td>{fromEndFifthPlaceAddress}</td>
                    </tr>

                </tbody>
            </table>
            <h1 className="mb-3" style={{ marginLeft: "200px" }}>Prašymų statistika</h1>
            <table id="groupTable" className='table col-12 mb-5'>

                <tbody>

                    <tr>
                        <th scope='row'>Prašymų skaičius</th>
                        <td>{nrOfApplications}</td>
                    </tr>
                    <tr>
                        <th scope='row'>Darželių vietų skaičius</th>
                        <td>{nrOfKindergartenSpots}</td>
                    </tr>
                    <tr>
                        <th scope='row'>Laukančių eilėje skaičius</th>
                        <td>{nrOfWaitingApplications === 0 ? "-" : nrOfWaitingApplications}</td>
                    </tr>
                    <tr>
                        <th scope='row'>Priimtų į darželį skaičius</th>
                        <td>{nrOfApprovedApplications === 0 ? "-" : nrOfApprovedApplications}</td>
                    </tr>

                </tbody>
            </table>
        </div>
    )
}

export default StatisticsComponent