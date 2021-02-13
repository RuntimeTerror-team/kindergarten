import React from 'react'

const Error = (props) => {
    const goApp = () => props.history.goBack();
    return (
        <div className="container">
            <div className='m-5'>
                <h1>Klaida</h1>
                <button className='btn btn-primary mt-3' onClick={goApp}>Grįžti</button>
            </div>
        </div>
    )
};

export default Error;