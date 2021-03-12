import React from 'react';
import Proptypes from 'prop-types';

let SecondGuardianButtonComponent = (props) => {

  if (props.currentStep !== 1) {
    return null
  }

  return (

    <div className="templatemo-content-widget white-bg">
      <h3 className="margin-bottom-10">Vaiko atstovas</h3>
      <div className="col-12 text-right">
        <button className="templatemo-blue-button" onClick={props.fillSecondGuardian}>Pridėti</button>
      </div>
    </div>


  )

}

SecondGuardianButtonComponent.propTypes = {

  fillSecondGuardian: Proptypes.func.isRequired,
}

export default SecondGuardianButtonComponent