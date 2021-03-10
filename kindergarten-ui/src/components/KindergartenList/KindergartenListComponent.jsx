import React from "react";
import PropTypes from 'prop-types'
import KindergartenTableComponent from "../KindergartenTable/KindergartenTableComponent";
import { Link } from "react-router-dom";
import urls from '../../constants/urls';

const KindergartenListComponent = ({ kindergartens }) => {
  return (
    <div>
      <Link to={`${urls.educationSpecialist.kindergartenBase}/new`} className="templatemo-blue-button" >
        Pridėti naują darželį</Link>
      {kindergartens.length > 0 &&
        <KindergartenTableComponent
          kindergartens={kindergartens}
        />}
    </div>
  );
};

KindergartenListComponent.propTypes = {
  kindergartens: PropTypes.array.isRequired
}

export default KindergartenListComponent;
