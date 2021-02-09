import React from 'react';
import Proptypes from 'prop-types';
import KindergartenListComponent from './KindergartenListComponent';
import ChildrenListComponent from './ChildrenListComponent';
import PriorityListComponent from './PriorityListComponent';
import { faCheck } from '@fortawesome/free-solid-svg-icons';

let ApplicationComponent = (props) =>{

    return(

        
            <div className="row justify-content-center align-items-center">
            <h1 className="mb-3 mr-5">Vaiko registracija</h1>
            <form className="form ml-5" onSubmit={props.onSubmit}>
              

                <ChildrenListComponent
                  name={props.name}
                  surname={props.surname}
                  birthDate={props.birthDate}
                  id={props.id}
                  street={props.street}
                  city={props.city}
                  onChildDetailsChange={props.onChildDetailsChange}
                />

                <KindergartenListComponent
                  kinderGartenList={props.kinderGartenList}
                  optionsValuesList={props.optionsValuesList}
                  showChoices={props.showChoices}
                  handleOnOptionsChange={props.handleOnOptionsChange}
                  onChosenKindergartens={props.onChosenKindergartens}
                  onDeleteSelection={props.onDeleteSelection}
                />

                <PriorityListComponent
                  onPrioritiesChange={props.onPrioritiesChange}
                  priorities={props.priorities}
                />

                <button className="btn btn-info d-block">Siųsti</button>
            </form>
            </div>
        
    )


}

ApplicationComponent.propTypes = {

    kinderGartenList: Proptypes.array.isRequired,
    optionsValuesList: Proptypes.array.isRequired,
    priorities: Proptypes.array.isRequired,
    showChoices: Proptypes.bool.isRequired,
    name: Proptypes.string.isRequired,
    surname: Proptypes.string.isRequired,
    birthDate: Proptypes.string.isRequired,
    id: Proptypes.string.isRequired,
    street: Proptypes.string.isRequired,
    city: Proptypes.string.isRequired,
    handleOnOptionsChange: Proptypes.func.isRequired,
    onChosenKindergartens: Proptypes.func.isRequired,
    onDeleteSelection: Proptypes.func.isRequired,
    onChildDetailsChange: Proptypes.func.isRequired,
    onPrioritiesChange: Proptypes.func.isRequired,
    onSubmit: Proptypes.func.isRequired
}

export default ApplicationComponent