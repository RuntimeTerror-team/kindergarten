import React from 'react';
import logo from '../../images/logo.png';
const GuardianPrimaryInfoComponent = () => {
    return (
        <div className="templatemo-sidebar">
            <div className="logo-div">
                <img src={logo} alt="logo" className="logo mx-auto" />
            </div>
            <nav className="templatemo-left-nav">
                <div className="alert alert-warning mx-3" role="alert">
                    <h4 className="alert-heading">Sveiki prisijungę!</h4>
                    <p> Prašome suvesti registracijos duomenis, kad galėtumėte pildyti prašymus ir naudotis kitomis svetainės funkcijomis. Jūsų duomenis tvarkysime tik prašymų į darželius ir susijusiais klausimais.</p>
                    <hr />
                    <p className="mb-0">Jei vėliau pageidausite nepildyti prašymų ir/ar nesinaudoti svetainės funkcijomis, jūsų duomenys galės būti ištrinti.</p>
                </div>
            </nav>
        </div >
    )
}

export default GuardianPrimaryInfoComponent;