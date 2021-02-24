import React, { useState } from 'react';
import FileInput from '../FileInput/FileInput';

const HealthFormListComponent = ({ children }) => {
    const [selectedChildId, setSelectedChildId] = useState("");
    return (
        <div>
            <div className="row">
                <div className="form-group col-4 text-center">
                    <select className="form-control" onChange={(e) => setSelectedChildId(e.target.value)} >
                        <option defaultValue>Pasirinkite vaiką</option>
                        {children.map(({ id, firstName, lastName }) => (
                            <option key={id} value={id}>{firstName} {lastName}</option>
                        ))}
                    </select>
                </div>
                <FileInput
                    selectedChildId={selectedChildId}
                />
            </div>
        </div>
    )
}

export default HealthFormListComponent;