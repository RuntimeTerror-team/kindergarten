import React from 'react';
import Proptypes from 'prop-types';

let KindergartenListComponent = (props) => {

    let Options = props.kinderGartenList.map((kinderGarten, index) => {

        return (
            <option key={index} value={kinderGarten.title}>{kinderGarten.title}</option>
        )
    })

    let choosenKindergartens = props.optionsValuesList.map(kinderGarten => {

        let queueNumber = (props.optionsValuesList.indexOf(kinderGarten) + 1) + ". ";

        return (
            <tr>
                <td>{queueNumber}</td>
                <td className="">{kinderGarten}</td>
                <td><button className="templatemo-edit-btn" value={kinderGarten} onClick={props.onDeleteSelection}>Atšaukti</button></td>

            </tr>
        )
    })

    let myTable = () => {

        return (

            <div className="mt-4">

                <table className='table'>

                    <tbody>
                        {choosenKindergartens}
                    </tbody>
                </table>
            </div>

        )

    }

    if (props.currentStep !== 2) {
        return null
    }

    return (
        <div className="templatemo-content-widget white-bg">
            <div className="form-group col-12">
                <h3 className="margin-bottom-10">Darželių pasirinkimas</h3>
                <select multiple={true} className="form-control mb-2" name="kindergartens" value={props.optionsValuesList} onChange={props.handleOnOptionsChange}>
                    <option value="" disabled>Pasirinkti</option>
                    {Options}
                </select>
                <div className="invalid-feedback">Prašome pridėti bent vieną darželį</div>

                {props.showChoices ? myTable() : null}
                <div className="row  ml-1 mb-0">

                </div>
            </div>
        </div>


    )

}

KindergartenListComponent.propTypes = {

    currentStep: Proptypes.number.isRequired,
    kinderGartenList: Proptypes.array.isRequired,
    optionsValuesList: Proptypes.array.isRequired,
    showChoices: Proptypes.bool.isRequired,
    handleOnOptionsChange: Proptypes.func.isRequired,
    onChosenKindergartens: Proptypes.func.isRequired,
    onDeleteSelection: Proptypes.func.isRequired,
    prev: Proptypes.func.isRequired,
    next: Proptypes.func.isRequired,
    previousButton: Proptypes.func.isRequired
}

export default KindergartenListComponent