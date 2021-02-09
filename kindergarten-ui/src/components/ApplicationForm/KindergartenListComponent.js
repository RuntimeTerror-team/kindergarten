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

        <div className="ml-5">

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

    return(

        <div className="form-group">
            <label className="my-3 ml-n5" htmlFor="kindergartens">Pridėkite bent vieną daržėlį iš sąrašo. Max 5</label>
            <select style={{width:"30%"}} multiple={true} className="form-control mb-2 ml-5" name="kindergartens" value={props.optionsValuesList} onChange={props.handleOnOptionsChange}>
                <option value="" disabled>Pasirinkti</option>
                {Options}
            </select>
            <div className="invalid-feedback">Prašome pridėti bent vieną darželį</div>
            <button className="btn btn-info my-3 d-block ml-5" onClick={props.onChosenKindergartens}>Pridėti darželį</button>
            {props.showChoices ? myTable() : null}
        </div>

        
    )

}

KindergartenListComponent.propTypes = {

    kinderGartenList: Proptypes.array.isRequired,
    optionsValuesList: Proptypes.array.isRequired,
    showChoices: Proptypes.bool.isRequired,
    handleOnOptionsChange: Proptypes.func.isRequired,
    onChosenKindergartens: Proptypes.func.isRequired,
    onDeleteSelection: Proptypes.func.isRequired
}

export default KindergartenListComponent