import React from 'react';
import Proptypes from 'prop-types';
import KindergartenListComponent from './KindergartenListComponent';
import GuardianInfoComponent from './GuardianInfoComponent';
import SecondGuardianInfoComponent from './SecondGuardianInfoComponent';
import ChildInfoComponent from './ChildInfoComponent';
import PriorityListComponent from './PriorityListComponent';
import SecondGuardianButtonComponent from './SecondGuardianButtonComponent'
import NoApplicationsComponent from './NoApplicationsComponent'

let ApplicationComponent = (props) =>{

    return(

        <div className="container">
            <div className="row justify-content-center align-items-center">
              <div className="col-12">
            <h1 className="my-3">Registracijos į darželį forma</h1>
            </div>
            <form className="form ml-5" onSubmit={props.onSubmit}>

              <div className="row">

              <NoApplicationsComponent
               currentStep={props.currentStep}/>

                <div className="col-4 px-5">
                <ChildInfoComponent
                  currentStep={props.currentStep}
                  name={props.childName}
                  surname={props.childSurname}
                  birthDate={props.childBirthDate}
                  id={props.childId}
                  street={props.childStreet}
                  city={props.childCity}
                  childNameValidation={props.childNameValidation}
                  childSurnameValidation={props.childSurnameValidation}
                  childBirthDateValidation={props.childBirthDateValidation}
                  childIdValidation={props.childIdValidation}
                  childStreetValidation={props.childStreetValidation}
                  childCityValidation={props.childCityValidation}
                  emptyChildInputsMessage={props.emptyChildInputsMessage}
                  emptyChildInputsMessageStyle={props.emptyChildInputsMessageStyle}
                  message={props.childMessage}
                  messageStyle={props.childMessageStyle}
                  onDetailsChange={props.onDetailsChange}
                  saveChild={props.saveChild}
                  prev={props.prev}
                  next={props.next}
                  previousButton={props.previousButton}
                  nextButton={props.nextButton}
                />
                  </div>

                  <div className="col-4 px-5">
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
                   message={props.guardianMessage}
                   messageStyle={props.guardianMessageStyle}
                   guardianButtonText={props.guardianButtonText}
                   isDisabled={props.isDisabled}
                   guardianNameValidation={props.guardianNameValidation}
                   guardianSurnameValidation={props.guardianSurnameValidation}
                   guardianIdValidation={props.guardianIdValidation}
                   guardianPhoneValidation={props.guardianPhoneValidation}
                   guardianAddressValidation={props.guardianAddressValidation}
                   guardianCityValidation={props.guardianCityValidation}
                   guardianPostalCodeValidation={props.guardianPostalCodeValidation}
                   guardianEmailValidation={props.guardianEmailValidation}
                   emptyInputsMessage={props.emptyInputsMessage}
                   emptyInputsMessageStyle={props.emptyInputsMessageStyle}
                   onDetailsChange={props.onDetailsChange}
                   saveGuardian={props.saveGuardian}
                   prev={props.prev}
                   next={props.next}
                   previousButton={props.previousButton}
                   nextButton={props.nextButton}
                 />
                   </div>

                   <div className="col-4 px-5">
                     <SecondGuardianInfoComponent
                       currentStep={props.currentStep}
                       secondGuardianName={props.secondGuardianName}
                       secondGuardianSurname={props.secondGuardianSurname}
                       secondGuardianId={props.secondGuardianId}
                       secondGuardianPhone={props.secondGuardianPhone}
                       secondGuardianAddress={props.secondGuardianAddress}
                       secondGuardianCity={props.secondGuardianCity}
                       secondGuardianPostalCode={props.secondGuardianPostalCode}
                       secondGuardianEmail={props.secondGuardianEmail}
                       message={props.secondGuardianMessage}
                       messageStyle={props.secondGuardianMessageStyle}
                       secondGuardianNameValidation={props.secondGuardianNameValidation}
                       secondGuardianSurnameValidation={props.secondGuardianSurnameValidation}
                       secondGuardianIdValidation={props.secondGuardianIdValidation}
                       secondGuardianPhoneValidation={props.secondGuardianPhoneValidation}
                       secondGuardianAddressValidation={props.secondGuardianAddressValidation}
                       secondGuardianCityValidation={props.secondGuardianCityValidation}
                       secondGuardianPostalCodeValidation={props.secondGuardianPostalCodeValidation}
                       emptyGuardianInputsMessage={props.emptyGuardianInputsMessage}
                       emptyGuardianInputsMessageStyle={props.emptyGuardianInputsMessageStyle}
                       secondGuardianEmailValidation={props.secondGuardianEmailValidation}
                       showSecondGuardianForm={props.showSecondGuardianForm}
                       onDetailsChange={props.onDetailsChange}
                       saveSecondGuardian={props.saveSecondGuardian}
                     />
                   </div>

                 </div>

                 <SecondGuardianButtonComponent
                   currentStep={props.currentStep}
                   fillSecondGuardian={props.fillSecondGuardian}
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
                  emptyInputsMessage={props.emptyInputsMessage}
                  emptyInputsMessageStyle={props.emptyInputsMessageStyle}
                  noneKindergartenSelectedMessage={props.noneKindergartenSelectedMessage}
                  noneKindergartenSelectedMessageStyle={props.noneKindergartenSelectedMessageStyle}
                  prev={props.prev}
                  next={props.next}
                  previousButton={props.previousButton}
                  nextButton={props.nextButton}
                />

                <div className={props.noChildMessageStyle + " mt-2"}>
                <span>{props.noChildMessage}</span>
                </div>
                <div className={props.noGuardianMessageStyle + " mt-2"}>
                <span>{props.noGuardianMessage}</span>
                </div>

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
    secondGuardianPostalCode: Proptypes.string.isRequired,
    secondGuardianEmail: Proptypes.string.isRequired,
    childName: Proptypes.string.isRequired,
    childSurname: Proptypes.string.isRequired,
    childBirthDate: Proptypes.string.isRequired,
    childId: Proptypes.string.isRequired,
    childStreet: Proptypes.string.isRequired,
    childCity: Proptypes.string.isRequired,
    showSecondGuardianForm: Proptypes.bool.isRequired,
    childMessage: Proptypes.string.isRequired,
    childMessageStyle: Proptypes.string.isRequired,
    guardianMessage: Proptypes.string.isRequired,
    guardianMessageStyle: Proptypes.string.isRequired,
    secondGuardianMessage: Proptypes.string.isRequired,
    secondGuardianMessageStyle: Proptypes.string.isRequired,
    noChildMessage: Proptypes.string.isRequired,
    noChildMessageStyle: Proptypes.string.isRequired,
    noGuardianMessage: Proptypes.string.isRequired,
    noGuardianMessageStyle: Proptypes.string.isRequired,
    guardianButtonText: Proptypes.string.isRequired,
    isDisabled: Proptypes.bool.isRequired,
    handleOnOptionsChange: Proptypes.func.isRequired,
    onChosenKindergartens: Proptypes.func.isRequired,
    onDeleteSelection: Proptypes.func.isRequired,
    onDetailsChange: Proptypes.func.isRequired,
    fillSecondGuardian: Proptypes.func.isRequired,
    onPrioritiesChange: Proptypes.func.isRequired,
    saveGuardian: Proptypes.func.isRequired,
    saveSecondGuardian: Proptypes.func.isRequired,
    saveChild: Proptypes.func.isRequired,
    onSubmit: Proptypes.func.isRequired,
    prev: Proptypes.func.isRequired,
    next: Proptypes.func.isRequired,
    previousButton: Proptypes.func.isRequired,
    nextButton: Proptypes.func.isRequired

}

export default ApplicationComponent