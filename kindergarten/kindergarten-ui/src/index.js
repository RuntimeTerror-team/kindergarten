import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from 'react-router-dom';
import App from "./App";

import ServicesContext from './context/ServicesContext'
import UserService from './services/UserService'

import 'bootstrap/dist/css/bootstrap.min.css';

import './styles/styles.css'

document.title = "Kindergarten App";

const userService = new UserService();

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter basename={process.env.PUBLIC_URL}>
      <ServicesContext.Provider value={{ userService }}>
        <App />
      </ServicesContext.Provider>
    </BrowserRouter>
  </React.StrictMode >,
  document.getElementById("root")
);
