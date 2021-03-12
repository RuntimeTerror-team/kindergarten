import React from 'react';

const Input = ({ name, value, labelStyle, label, mandatory, type, inputStyle, error, placeholder, onChange, invalidStyle, errorMessage, min, disabled, formGroupStyle }) => {
    return (
        <div className={`form-group row ${formGroupStyle}`}>
            <label htmlFor={name} className={labelStyle}>{label}{mandatory && !disabled ? <span className="mandatory"> *</span> : ""}</label>
            <input
                type={type}
                className={`form-control ${inputStyle} ${error}`}
                placeholder={placeholder}
                id={name}
                value={value}
                name={name}
                onChange={onChange}
                min={min}
                disabled={disabled}
            />
            <div className={`invalid-feedback ${invalidStyle}`}>{errorMessage}</div>
        </div>
    )
}

export default Input;