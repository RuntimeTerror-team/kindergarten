import React from 'react';

const GuardianPrimaryInfoComponent = () => {
    return (
        <div className="col-4 mt-5">
            <div className="alert alert-warning" role="alert">
                <h4 className="alert-heading">Sveiki prisijungę!</h4>
                <p> Prašome suvesti registracijos duomenis, kad galėtumėte pildyti prašymus ir naudotis kitomis svetainės funkcijomis. Jūsų duomenis tvarkysime tik prašymų į darželius ir susijusiais klausimais.</p>
                <hr />
                <p className="mb-0">Jei vėliau pageidausite nepildyti prašymų ir/ar nesinaudoti svetainės funkcijomis, jūsų duomenys galės būti ištrinti.</p>
            </div>
        </div>
    )
}

export default GuardianPrimaryInfoComponent;