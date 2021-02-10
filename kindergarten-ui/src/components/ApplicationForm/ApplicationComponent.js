import React from 'react';
import Proptypes from 'prop-types';
import KindergartenListComponent from './KindergartenListComponent';
import GuardianInfoComponent from './GuardianInfoComponent';
import ChildInfoComponent from './ChildInfoComponent';
import PriorityListComponent from './PriorityListComponent';
import { faCheck, faPhone } from '@fortawesome/free-solid-svg-icons';

let ApplicationComponent = (props) =>{

    return(

        <div className="container">
            <div className="row justify-content-center align-items-center">
              <div className="col-12">
            <h1 className>Prašymo pildymas {props.currentStep}/4</h1>
            </div>
            <form className="form ml-5" onSubmit={props.onSubmit}>
                 
                 <GuardianInfoComponent
                   currentStep={props.currentStep}
                   name={props.guardianName}
                   surname={props.guardianSurname}
                   id={props.guardianId}
                   phone={props.guardianPhone}
                   address={props.guardianAddress}
                   city={props.guardianCity}
                   postalCode={props.guardianPostalCode}
                   email={props.guardianEmail}
                   secondGuardianName={props.secondGuardianName}
                   secondGuardianSurname={props.secondGuardianSurname}
                   secondGuardianId={props.secondGuardianId}
                   secondGuardianPhone={props.secondGuardianPhone}
                   secondGuardianAddress={props.secondGuardianAddress}
                   secondGuardianCity={props.secondGuardianCity}
                   secondGuardianPostalCode={props.secondGuardianPostalCode}
                   secondGuardianEmail={props.secondGuardianEmail}
                   showSecondGuardianForm={props.showSecondGuardianForm}
                   onDetailsChange={props.onDetailsChange}
                   fillSecondGuardian={props.fillSecondGuardian}
                   prev={props.prev}
                   next={props.next}
                   previousButton={props.previousButton}
                   nextButton={props.nextButton}
                 />
              

                 <ChildInfoComponent
                  currentStep={props.currentStep}
                  name={props.childName}
                  surname={props.childSurname}
                  birthDate={props.childBirthDate}
                  id={props.childId}
                  street={props.childStreet}
                  city={props.childCity}
                  onDetailsChange={props.onDetailsChange}
                  prev={props.prev}
                  next={props.next}
                  previousButton={props.previousButton}
                  nextButton={props.nextButton}
                />

                <KindergartenListComponent
                  currentStep={props.currentStep}
                  kinderGartenList={props.kinderGartenList}
                  optionsValuesList={props.optionsValuesList}
                  showChoices={props.showChoices}
                  handleOnOptionsChange={props.handleOnOptionsChange}
                  onChosenKindergartens={props.onChosenKindergartens}
                  onDeleteSelection={props.onDeleteSelection}
                  prev={props.prev}
                  next={props.next}
                  previousButton={props.previousButton}
                  nextButton={props.nextButton}
                />

                <PriorityListComponent
                  currentStep={props.currentStep}
                  onPrioritiesChange={props.onPrioritiesChange}
                  priorities={props.priorities}
                  prev={props.prev}
                  next={props.next}
                  previousButton={props.previousButton}
                  nextButton={props.nextButton}
                />

            </form>
            </div>
        </div>      
        
    )


}

ApplicationComponent.propTypes = {

    currentStep: Proptypes.number.isRequired,
    kinderGartenList: Proptypes.array.isRequired,
    optionsValuesList: Proptypes.array.isRequired,
    priorities: Proptypes.array.isRequired,
    showChoices: Proptypes.bool.isRequired,
    guardianName: Proptypes.string.isRequired,
    guardianSurname: Proptypes.string.isRequired,
    guardianId: Proptypes.string.isRequired,
    guardianPhone: Proptypes.string.isRequired,
    guardianAddress: Proptypes.string.isRequired,
    guardianCity: Proptypes.string.isRequired,
    guardianPostalCode: Proptypes.string.isRequired,
    guardianEmail: Proptypes.string.isRequired,
    secondGuardianName: Proptypes.string.isRequired,
    secondGuardianSurname: Proptypes.string.isRequired,
    secondGuardianId: Proptypes.string.isRequired,
    secondGuardianPhone: Proptypes.string.isRequired,
    secondGuardianAddress: Proptypes.string.isRequired,
    secondGuardianCity: Proptypes.string.isRequired,
    secondguardianPostalCode: Proptypes.string.isRequired,
    secondGuardianEmail: Proptypes.string.isRequired,
    childName: Proptypes.string.isRequired,
    childSurname: Proptypes.string.isRequired,
    childBirthDate: Proptypes.string.isRequired,
    childId: Proptypes.string.isRequired,
    childStreet: Proptypes.string.isRequired,
    childCity: Proptypes.string.isRequired,
    showSecondGuardianForm: Proptypes.bool.isRequired,
    handleOnOptionsChange: Proptypes.func.isRequired,
    onChosenKindergartens: Proptypes.func.isRequired,
    onDeleteSelection: Proptypes.func.isRequired,
    onDetailsChange: Proptypes.func.isRequired,
    fillSecondGuardian: Proptypes.func.isRequired,
    onPrioritiesChange: Proptypes.func.isRequired,
    onSubmit: Proptypes.func.isRequired,
    prev: Proptypes.func.isRequired,
    next: Proptypes.func.isRequired,
    previousButton: Proptypes.func.isRequired,
    nextButton: Proptypes.func.isRequired

}

export default ApplicationComponent