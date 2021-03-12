import React from 'react';
import Proptypes from 'prop-types';

let PriorityListComponent = (props) => {

  if (props.currentStep !== 2) {
    return null
  }

  return (
    <div className="templatemo-content-widget white-bg">

      <h3 className="margin-bottom-10">Papildoma informacija</h3>
      {/* <label htmlFor="checkSelections">Pasirinkite prioritetus</label> */}
      <div className="" name="checkSelections" style={{ textAlign: "left" }}>
        {/* <div className="form form-check">
            <input type="checkbox" className="form-check-input" name="priority1" checked={props.priorities[0].isChecked} value={props.priorities[0].value} onChange={props.onPrioritiesChange}></input>
            <label className="form-check-label" htmlFor="priority1">Vaiko deklaruojama gyvenamoji vieta yra miesto savivaldybė</label>
            </div> */}

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

        {/* <div class="form-check">
            <input type="checkbox" class="form-check-input" name="priority4" value={props.child} onChange={props.onChildHandle}></input>
            <label class="form-check-label" htmlFor="priority4">Vaiko brolis ar sesuo lanko tą pačią įstaigą</label>
            </div> */}

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


      {/* <button className="templatemo-blue-button">Pateikti</button>
      {props.previousButton()}


      <span className={props.noneKindergartenSelectedMessageStyle + " mt-5"} style={{ width: "23em" }}>
        {props.noneKindergartenSelectedMessage}
      </span>




      <div className={" " + props.noChildMessageStyle + " ml-1"} style={{ width: "23em" }}>
        <span>{props.noChildMessage}</span>
      </div>


      <span className={props.emptyInputsMessageStyle} style={{ width: "23em" }}>
        {props.emptyInputsMessage}
      </span>


      <span className={props.applicationMessageStyle + ""} style={{ width: "23em" }}>
        {props.applicationMessage}
      </span> */}



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