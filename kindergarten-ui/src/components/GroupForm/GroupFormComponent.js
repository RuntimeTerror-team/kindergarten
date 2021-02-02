import React from 'react';
import Proptypes from 'prop-types';


let GroupFormComponent = (props) => {

    return (
        <div className="row justify-content-center align-items-center">
            <div>
                <h1 className="mb-4">Grupės sukūrimas</h1>
                <form className="form"  onSubmit={props.onSubmit}>
                    <div className="form-group">
                      <select className={"form-control " + props.fromAgeFieldValidation} name="fromAge" style={{width:"25em"}} value={props.fromAge} onChange={props.onFromAgeChange}>
                        <option value="">Amžius nuo</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                      </select>
                      <div className="invalid-feedback">Prašom užpildyti amžių nuo</div>
                     </div>
                     <div className="form-group">
                      <select className={"form-control " + props.toAgeFieldValidation} name="toAge" style={{width:"25em"}} value={props.toAge} onChange={props.onToAgeChange}>
                        <option value="">Amžius iki</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                      </select>
                      <div className="invalid-feedback">Prašom užpildyti amžių iki</div>
                     </div>
                    <button className="btn btn-info float-right">Sukurti</button>
                    <div className="row pt-5">
                    {props.invalidInterval ? <span style={{color:"red"}}>Amžius nuo negali būti didesnis už amžių iki</span> : null}
                    </div>
                </form>
            </div>
        </div>
    )

}

GroupFormComponent.propTypes = {

    fromAge: Proptypes.string.isRequired,
    toAge: Proptypes.string.isRequired,
    fromAgeFieldValidation: Proptypes.string.isRequired,
    toAgeFieldValidation: Proptypes.string.isRequired,
    onFromAgeChange: Proptypes.func.isRequired,
    invalidInterval: Proptypes.bool.isRequired,
    onToAgeChange: Proptypes.func.isRequired,
    onSubmit: Proptypes.func.isRequired

}


export default GroupFormComponent;
