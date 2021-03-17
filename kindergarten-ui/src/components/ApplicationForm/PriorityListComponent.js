import React from 'react';
import Proptypes from 'prop-types';

let PriorityListComponent = (props) => {

  if (props.currentStep !== 2) {
    return null
  }

  return (
    <div className="templatemo-content-widget white-bg">

      <h3 className="margin-bottom-10">Papildoma informacija</h3>
      <div className="" name="checkSelections" style={{ textAlign: "left" }}>

        <div class="form-group">
          <div class="checkbox squaredTwo">
            <input type="checkbox" className="form-check-input" id="priority2" name="priority2" checked={props.priorities[1].isChecked} value={props.priorities[1].value} onChange={props.onPrioritiesChange}></input>
            <label htmlFor="priority2"><span></span>Vaikas yra įvaikintas</label>
          </div>
        </div>

        <div class="form-group">
          <div class="checkbox squaredTwo">
            <input type="checkbox" className="form-check-input" id="priority3" name="priority3" checked={props.priorities[2].isChecked} value={props.priorities[2].value} onChange={props.onPrioritiesChange}></input>
            <label htmlFor="priority3"><span></span>Vaikas yra iš šeimos, turinčios tris ir daugiau vaikų</label>
          </div>
        </div>

        <div class="form-group">
          <div class="checkbox squaredTwo">
            <input type="checkbox" className="form-check-input" id="priority4" name="priority4" checked={props.priorities[3].isChecked} value={props.priorities[3].value} onChange={props.onPrioritiesChange}></input>
            <label htmlFor="priority4"><span></span>Vienas iš tėvų  mokosi bendrojo ugdymo mokykloje</label>
          </div>
        </div>

        <div class="form-group">
          <div class="checkbox squaredTwo">
            <input type="checkbox" className="form-check-input" id="priority5" name="priority5" checked={props.priorities[4].isChecked} value={props.priorities[4].value} onChange={props.onPrioritiesChange}></input>
            <label className="form-check-label" htmlFor="priority5"><span></span>Vienas iš tėvų turi ne daugiau kaip 40 procentų darbingumo
             lygio</label>
          </div>
        </div>
      </div>
    </div>
  )

}


PriorityListComponent.propTypes = {

  currentStep: Proptypes.number.isRequired,
  onPrioritiesChange: Proptypes.func.isRequired,
  priorities: Proptypes.array.isRequired,
  applicationMessage: Proptypes.string.isRequired,
  applicationMessageStyle: Proptypes.string.isRequired,
  prev: Proptypes.func.isRequired,
  next: Proptypes.func.isRequired,
  previousButton: Proptypes.func.isRequired
}

export default PriorityListComponent