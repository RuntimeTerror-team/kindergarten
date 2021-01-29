import React from 'react'

const NoMatchComponent = (props) => {
    const goApp = () => props.history.push('/');
    return (
        <div className="container">
            <div className='m-5'>
                <h1>Tokio adreso nėra</h1>
                <button className='btn btn-primary ml-2 mt-3' onClick={goApp}>Eiti į prisijungimo puslapį</button>
            </div>
        </div>
    )
};

export default NoMatchComponent;