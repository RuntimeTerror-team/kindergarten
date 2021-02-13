import React from 'react'

const Forbidden = (props) => {
    //403
    const goBack = () => props.history.goBack();
    return (
        <div className="container">
            <div className='m-5'>
                <h1>Prieiga uždrausta</h1>
                <p> Neturite leidimo šiai užklausai.</p>
                <button className='btn btn-primary mt-3' onClick={goBack}>Grįžti</button>
            </div>
        </div>
    )
};

export default Forbidden;