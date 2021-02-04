import React from 'react';

const KindergartenGroupCreationComponent = ({ageRanges}) => {
    return (
        <div className="form-group">
            <label htmlFor="exampleFormControlSelect2">Amžiaus grupės</label>
            <select className="form-control" id="exampleFormControlSelect2">
                <option defaultValue>Pasirinkti...</option>
                {ageRanges.map(({id, minAge, maxAge}) => {
                    return (
                        <option key={id} id={id}>{minAge} - {maxAge}</option>
                    )
                })}
            </select>
        </div>
    )
}

export default KindergartenGroupCreationComponent