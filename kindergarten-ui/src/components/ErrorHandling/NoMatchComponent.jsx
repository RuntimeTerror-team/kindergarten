import React from 'react'

const NoMatchComponent = (props) => {
    const goApp = () => props.history.goBack();
    return (
        <div className="container">
            <div className='m-5'>
                <h1>Puslapis neegzistuoja</h1>
                <button className='btn btn-primary mt-3' onClick={goApp}>Grįžti</button>
            </div>
        </div>
    )
};

export default NoMatchComponent;