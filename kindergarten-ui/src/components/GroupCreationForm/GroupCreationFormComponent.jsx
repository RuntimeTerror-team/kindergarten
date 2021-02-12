import React from "react";
import { Link } from "react-router-dom";

const GroupCreationFComponent = ({
    ageRanges,
    title,
    childrenCount,
    handleFormChange,
    handleGroupCreation,
    kindergartenId,
    message,
    messageStyle,
    titleValidation,
    ageRangeValidation,
    childrenCountValidation
}) => {
    return (
        <form className="mt-4" onSubmit={handleGroupCreation}>
            <div className="form-group row">
                <label htmlFor="title" className="col-3 pt-2 text-right">
                    Pavadinimas
                </label>
                <input
                    type="text"
                    className={`form-control col-9 ${titleValidation}`}
                    id="title"
                    name="title"
                    value={title}
                    onChange={handleFormChange}
                />
                <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Pavadinimo ilgis turi būti 5-20 ženklų.</div>
            </div>
            <div className="form-group row">
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
            </div>
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
            <button type="submit" className="btn btn-green float-right">
                Išsaugoti
            </button>
            <Link to={`/education-specialist/kindergartens/${kindergartenId}/groups`} className="btn btn-yellow float-right mr-2">
                Grįžti į grupių sąrašą
      </Link>
        </form>
    );
};

export default GroupCreationFComponent;
