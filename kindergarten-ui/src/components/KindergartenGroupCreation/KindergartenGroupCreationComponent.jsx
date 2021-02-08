import React from 'react';

const KindergartenGroupCreationComponent = ({ ageRanges, title, childrenCount, handleFormChange, handleGroupCreation, titleValidation, ageRangeValidation, childrenCountValidation }) => {
    return (
        <form className="mt-4 clearfix" onSubmit={handleGroupCreation}>
            <div className="form-group row">
                <label htmlFor="title" className="col-3 pt-2 text-right">Pavadinimas <span className="mandatory">*</span></label>
                <input type="text" className={`form-control col-9 ${titleValidation}`} id="title" name="title" value={title} onChange={handleFormChange} />
                <div className="col-3"></div>
                <div className="invalid-feedback col-9">
                    Šis laukas privalomas. Pavadinimo ilgis turi būti 5-20 ženklų.
                </div>
            </div>
            <div className="form-group row">
                <label htmlFor="ageRange" className="col-3 mt-2 text-right">Amžiaus grupės <span className="mandatory">*</span></label>
                <select className={`form-control col-9 ${ageRangeValidation}`} id="ageRange" name="ageRangeId" onChange={handleFormChange}>
                    <option defaultValue>Pasirinkti...</option>
                    {ageRanges.map(({ id, minAge, maxAge }) => {
                        return (
                            <option key={id} id={id} value={id}>{minAge} - {maxAge}</option>
                        )
                    })}
                </select>
                <div className="col-3"></div>
                <div className="invalid-feedback col-9">
                    Šis laukas privalomas. Pasirinkite amžiaus grupę.
                </div>
            </div>
            <div className="form-group row mb-0">
                <label htmlFor="childrenCount" className="col-3 pt-2 text-right">Vaikų skaičius <span className="mandatory">*</span></label>
                <input type="number" min="0"  className={`form-control col-9 ${childrenCountValidation}`} id="childrenCount" name="childrenCount" value={childrenCount} onChange={handleFormChange} />
                <div className="col-3"></div>
                <div className="invalid-feedback col-9">
                    Šis laukas privalomas. Įveskite skaičių (gali būti 0).
                </div>
            </div>
            <div className="col-3"></div>
            <div className="col-9"><span className="mandatory">*</span> pažymėti laukai privalomi.</div>
            <button type="submit" className="btn btn-info mb-4 float-right">Išsaugoti</button>
        </form>

    )
}

export default KindergartenGroupCreationComponent