import React from 'react';
import Proptypes from 'prop-types';

let KindergartenListComponent = (props) =>{

    let Options = props.kinderGartenList.map((kinderGarten, index) => {

        return(
            <option key={index} value={kinderGarten.title}>{kinderGarten.title}</option>
        )
    })

    let choosenKindergartens = props.optionsValuesList.map(kinderGarten => {

        let queueNumber = (props.optionsValuesList.indexOf(kinderGarten)+1) + ". ";

        return(

            

            // <div style={{width:"40%"}} className="mb-2">
            // <span className="d-block" style={{border: 'solid 1px black',borderRadius:"5%"}}>{queueNumber + kinderGarten}
            // <button className="btn btn-link float right" value={kinderGarten} onClick={props.onDeleteSelection}>Pašalinti</button>
            // </span>
            // </div>

             <tr>
             <td>{queueNumber}</td>
             <td className="">{kinderGarten}</td>
             <td><button className="btn btn-link float right" value={kinderGarten} onClick={props.onDeleteSelection}>Atšaukti</button></td>
             
</tr>
        )
    })

    let myTable = () =>{

        return(

        <div className="">

            <table className='table col-3'>
                {/* <thead>
                    <tr>
                        <th scope='col' style={{width: "40px"}}>#</th>
                        <th scope='col'>Darzelis</th>
                    </tr>
                </thead> */}

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

    return(

        <div className="form-group">
            <h2 className="my-3">Darželių pasirinkimas</h2>
            {/* <label className="my-3 ml-n5" htmlFor="kindergartens">Pridėkite bent vieną daržėlį iš sąrašo. Max 5</label> */}
            <select style={{width:"40%"}} multiple={true} className="form-control mb-2" name="kindergartens" value={props.optionsValuesList} onChange={props.handleOnOptionsChange}>
                <option value="" disabled>Pasirinkti</option>
                {Options}
            </select>
            <div className="invalid-feedback">Prašome pridėti bent vieną darželį</div>
            <button className="btn btn-info float-left" onClick={props.onChosenKindergartens}>Pridėti darželį</button>
            
            {props.showChoices ? myTable() : null}
            <div className="row  ml-1 my-5">
                <div className="mr-2">

            {props.previousButton()}

            </div>
            {props.nextButton()}
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
    previousButton: Proptypes.func.isRequired,
    nextButton: Proptypes.func.isRequired
}

export default KindergartenListComponent