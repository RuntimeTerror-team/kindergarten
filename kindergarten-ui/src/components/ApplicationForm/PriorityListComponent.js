import React from 'react';
import Proptypes from 'prop-types';

let PriorityListComponent = (props) =>{

    if (props.currentStep !== 4) {
        return null
      }

    return(

        <div className="my-3 float-left">

            <h2>Papildoma informacija</h2>
            {/* <label htmlFor="checkSelections">Pasirinkite prioritetus</label> */}
            <div className="" name="checkSelections">
            <div className="form form-check d-inline">
            <input type="checkbox" className="form-check-input" name="priority1" checked={props.priorities[0].isChecked} value={props.priorities[0].value} onChange={props.onPrioritiesChange}></input>
            <label className="form-check-label" htmlFor="priority1">Vaiko deklaruojama gyvenamoji vieta yra miesto savivaldybė</label>
            </div>

            <div className="form-check d-block">
            <input type="checkbox" className="form-check-input" name="priority2"  checked={props.priorities[1].isChecked} value={props.priorities[1].value} onChange={props.onPrioritiesChange}></input>
            <label className="form-check-label" htmlFor="priority2">Vaikas yra įvaikintas</label>
             </div>

             <div className="form-check">
            <input type="checkbox" className="form-check-input" name="priority3" checked={props.priorities[2].isChecked} value={props.priorities[2].value} onChange={props.onPrioritiesChange}></input>
            <label className="form-check-label" htmlFor="priority3">Vaikas yra iš šeimos, turinčios tris ir daugiau vaikų</label>
            </div>

            {/* <div class="form-check">
            <input type="checkbox" class="form-check-input" name="priority4" value={props.child} onChange={props.onChildHandle}></input>
            <label class="form-check-label" htmlFor="priority4">Vaiko brolis ar sesuo lanko tą pačią įstaigą</label>
            </div> */}

            <div className="form-check">
            <input type="checkbox" className="form-check-input" name="priority4" checked={props.priorities[3].isChecked} value={props.priorities[3].value} onChange={props.onPrioritiesChange}></input>
            <label className="form-check-label" htmlFor="priority4">Vienas iš tėvų  mokosi bendrojo ugdymo mokykloje</label>
            </div>

            <div className="form-check">
            <input type="checkbox" className="form-check-input" name="priority5" checked={props.priorities[4].isChecked} value={props.priorities[4].value} onChange={props.onPrioritiesChange}></input>
            <label className="form-check-label" htmlFor="priority5">Vienas iš tėvų turi ne daugiau kaip 40 procentų darbingumo
lygio</label>
</div>
            </div>
            
            {props.previousButton()}
            {props.nextButton()}
            <button className="btn btn-info d-block">Siųsti</button>
        </div>
    )

}

PriorityListComponent.propTypes = {

    currentStep: Proptypes.number.isRequired,
    onPrioritiesChange: Proptypes.func.isRequired,
    priorities: Proptypes.array.isRequired,
    prev: Proptypes.func.isRequired,
    next: Proptypes.func.isRequired,
    previousButton: Proptypes.func.isRequired,
    nextButton: Proptypes.func.isRequired
}

export default PriorityListComponent