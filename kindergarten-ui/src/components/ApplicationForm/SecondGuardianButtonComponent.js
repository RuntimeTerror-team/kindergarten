import React from 'react';
import Proptypes from 'prop-types';

let SecondGuardianButtonComponent = (props) =>{

  if (props.currentStep !== 1) {
    return null
  }

    return(

        <div className="row">
            <button className="btn btn-info" onClick={props.fillSecondGuardian}>Pridėti antrą vaiko atstovą</button>
        </div>


    )

}

SecondGuardianButtonComponent.propTypes = {

  fillSecondGuardian: Proptypes.func.isRequired,
}

export default SecondGuardianButtonComponent