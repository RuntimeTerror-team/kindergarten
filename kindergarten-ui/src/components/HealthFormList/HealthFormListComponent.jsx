import React, { useState } from 'react';
import FileInput from '../FileInput/FileInput';

const HealthFormListComponent = ({ children, updateForms }) => {
    const [selectedChildId, setSelectedChildId] = useState("");
    return (
        <div className="offset-1 col-10">
            <p> Pasirinkite vaiką, kurio sveikatos pažymą norite išsaugoti, ir pasirinkite pdf formato failą.</p >
            <div className="row h-4">
                < div className="form-group col-4 text-center" >
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