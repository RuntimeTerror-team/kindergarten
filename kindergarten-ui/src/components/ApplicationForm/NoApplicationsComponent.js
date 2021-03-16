import React from 'react';

let NoApplicationsComponent = (props) => {

  if (props.currentStep !== 5) {
    return null
  }

  return (

    <div className="alert alert-danger mt-4">
      <span>Prašymų pildymas šiuo metu negalimas</span>
    </div>



  )

}



export default NoApplicationsComponent