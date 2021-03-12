import React, { useState } from 'react';
import FileInput from './FileInput';

const HealthFormListComponent = ({ children, updateForms }) => {
    const [selectedChildId, setSelectedChildId] = useState("");
    return (
        <div className="templatemo-content-widget white-bg my-4 col-6 mx-auto">
            <h3 className="margin-bottom-10">Įkelti sveikatos pažymą</h3>
            <div className="row">
                < div className="form-group col-12 text-center" >
                    <select className="form-control" onChange={(e) => setSelectedChildId(e.target.value)} >
                        <option defaultValue>Pasirinkti vaiką</option>
                        {children.map(({ id, firstName, lastName }) => (
                            <option key={id} value={id}>{firstName} {lastName}</option>
                        ))}
                    </select>
                </div >
                <FileInput
                    selectedChildId={selectedChildId}
                    updateForms={updateForms}
                />
            </div >
        </div >
    )
}

export default HealthFormListComponent;