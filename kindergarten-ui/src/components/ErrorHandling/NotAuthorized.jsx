import React from 'react'

const NotAuthorized = (props) => {
    //401
    const goBack = () => props.history.push("/");
    return (
        <div className="container">
            <div className='m-5'>
                <h1>Nėra įgaliojimų</h1>
                <p>Jūs neturite teisės patekti į šį puslapį. Prašom prisijungti.</p>
                <button className='btn btn-primary mt-3' onClick={goBack}>Prisijungti</button>
            </div>
        </div>
    )
};

export default NotAuthorized;