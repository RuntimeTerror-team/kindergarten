import React from "react";
import Proptypes from "prop-types";
import { Link } from "react-router-dom";
import urls from "../../constants/urls";

const GroupCreationFComponent = ({
    ageRanges,
    childrenCount,
    handleFormChange,
    handleGroupCreation,
    kindergartenId,
    message,
    messageStyle,
    duplicateMessage,
    duplicateMessageStyle,
    ageRangeValidation,
    childrenCountValidation,
    group
}) => {
    return (
        <form className="mt-4 col-8 mx-auto" onSubmit={handleGroupCreation}>
            {!group && <div className="form-group row">
                <label htmlFor="ageRange" className="col-3 mt-2 text-right">
                    Amžiaus grupės
                </label>
                <select
                    className={`form-control col-9 ${ageRangeValidation}`}
                    id="ageRange" name="ageRangeId"
                    onChange={handleFormChange}
                >
                    <option defaultValue>Pasirinkti...</option>
                    {ageRanges && ageRanges.map(({ id, minAge, maxAge }) => {
                        return (
                            <option key={id} id={id} value={id}>
                                {minAge} - {maxAge}
                            </option>
                        );
                    })}
                </select>
                <div className="invalid-feedback offset-3 col-9">
                    Šis laukas privalomas. Pasirinkite amžiaus grupę.
                </div>
            </div>}

            {group && <div className="form-group row">
                <label htmlFor="ageRange" className="col-3 mt-2 text-right">
                    Amžiaus grupės
                </label>
                <select
                    className={`form-control col-9`}
                    id="ageRange" name="ageRangeId"
                    disabled={group}
                >
                    <option defaultValue>{group.ageRange.ageMin} - {group.ageRange.ageMax}</option>
                </select>
            </div>}

            <div className="form-group row">
                <label htmlFor="childrenCount" className="col-3 pt-2 text-right">
                    Vaikų skaičius
                </label>
                <input
                    type="number"
                    className={`form-control col-9 ${childrenCountValidation}`}
                    id="childrenCount"
                    name="childrenCount"
                    value={childrenCount}
                    onChange={handleFormChange}
                />
                <div className="invalid-feedback offset-3 col-9">
                    Šis laukas privalomas. Įveskite vaikų skaičių 0-99.
                </div>
            </div>
            {<div className={`${messageStyle} offset-4 col-8 mt-4`}>
                {message}
            </div>}
            {<div className={`${duplicateMessageStyle} offset-4 col-8 mt-4`}>
                {duplicateMessage}
            </div>}
            <button type="submit" className="btn btn-green float-right">
                Išsaugoti
            </button>
            <Link to={`${urls.educationSpecialist.kindergartenBase}/${kindergartenId}/groups`} className="btn btn-yellow float-right mr-2">
                Grįžti į grupių sąrašą
      </Link>
        </form>
    );
};

GroupCreationFComponent.propTypes = {
    ageRanges: Proptypes.array.isRequired,
    childrenCount: Proptypes.string,
    handleFormChange: Proptypes.func.isRequired,
    handleGroupCreation: Proptypes.func.isRequired,
    kindergartenId: Proptypes.string.isRequired,
    message: Proptypes.string.isRequired,
    messageStyle: Proptypes.string.isRequired,
    ageRangeValidation: Proptypes.string.isRequired,
    childrenCountValidation: Proptypes.string.isRequired
}

export default GroupCreationFComponent;
