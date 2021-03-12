import React from 'react';
import Proptypes from 'prop-types';
import KindergartenListComponent from './KindergartenListComponent';
import GuardianInfoComponent from './GuardianInfoComponent';
import SecondGuardianInfoComponent from './SecondGuardianInfoComponent';
import ChildInfoComponent from './ChildInfoComponent';
import PriorityListComponent from './PriorityListComponent';
import SecondGuardianButtonComponent from './SecondGuardianButtonComponent'
import NoApplicationsComponent from './NoApplicationsComponent'

let ApplicationComponent = (props) => {

  return (

    <div className="col-12 my-4 mx-auto px-0">
      <form className="form row col-12 px-0" onSubmit={props.onSubmit}>
        <NoApplicationsComponent
          currentStep={props.currentStep} />

        <div className="col-4 px-0">
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
            // childRegistratedMessage={props.childRegistratedMessage}
            // childRegistratedMessageStyle={props.childRegistratedMessageStyle}
            applicationMessage={props.applicationMessage}
            applicationMessageStyle={props.applicationMessageStyle}
            message={props.childMessage}
            messageStyle={props.childMessageStyle}
            onDetailsChange={props.onDetailsChange}
            saveChild={props.saveChild}
            prev={props.prev}
            next={props.next}
            previousButton={props.previousButton}
          />
        </div>

        <div className="col-4 px-0">
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
          />
        </div>

        <div className="col-4 px-0">
          {!props.wantsSecondGuardian
            &&
            <SecondGuardianButtonComponent
              currentStep={props.currentStep}
              fillSecondGuardian={props.fillSecondGuardian}
            />}
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
        <div className="row col-12 my-3">
          {props.nextButton()}
        </div>
        <div className="col-6 px-0">
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
          />
        </div>
        <div className="col-6 px-0">
          <PriorityListComponent
            currentStep={props.currentStep}
            onPrioritiesChange={props.onPrioritiesChange}
            priorities={props.priorities}
            emptyInputsMessage={props.emptyInputsMessage}
            emptyInputsMessageStyle={props.emptyInputsMessageStyle}
            noneKindergartenSelectedMessage={props.noneKindergartenSelectedMessage}
            noneKindergartenSelectedMessageStyle={props.noneKindergartenSelectedMessageStyle}
            noChildMessage={props.noChildMessage}
            noChildMessageStyle={props.noChildMessageStyle}
            applicationMessage={props.applicationMessage}
            applicationMessageStyle={props.applicationMessageStyle}
            prev={props.prev}
            next={props.next}
            previousButton={props.previousButton}
          />
        </div>

        <div className="col-12 px-0">

          <div className={props.noneKindergartenSelectedMessageStyle}>
            {props.noneKindergartenSelectedMessage}
          </div>

          <div className={props.noChildMessageStyle}>
            {props.noChildMessage}
          </div>

          <div className={props.emptyInputsMessageStyle}>
            {props.emptyInputsMessage}
          </div>

          <div className={props.applicationMessageStyle}>
            {props.applicationMessage}
          </div>

        </div>

        {/* <div className={props.noChildMessageStyle + " mt-2"}>
                <span>{props.noChildMessage}</span>
                </div> */}

        <div className="col-12 text-right p-0">
          {props.previousButton()}
          {props.currentStep === 2 && <button type="submit" className="templatemo-blue-button ml-2">Pateikti</button>}
        </div>
      </form>
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
  applicationMessage: Proptypes.string.isRequired,
  applicationMessageStyle: Proptypes.string.isRequired,
  childRegistratedMessage: Proptypes.string.isRequired,
  childRegistratedMessageStyle: Proptypes.string.isRequired,
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