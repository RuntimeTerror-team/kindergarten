import React from 'react';

const Input = ({ name, value, labelStyle, label, mandatory, type, inputStyle, error, placeholder, onChange, invalidStyle, errorMessage, min }) => {
    return (
        <div className="form-group row">
            <label htmlFor={name} className={labelStyle}>{label}{mandatory ? <span className="mandatory"> *</span> : ""}</label>
            <input
                type={type}
                className={`form-control ${inputStyle} ${error}`}
                placeholder={placeholder}
                id={name}
                value={value}
                name={name}
                onChange={onChange}
                min={min}
            />
            <div className={`invalid-feedback ${invalidStyle}`}>{errorMessage}</div>
        </div>
    )
}

export default Input;