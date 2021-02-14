import React from "react";
import PropTypes from 'prop-types'
import KindergartenTableComponent from "../KindergartenTable/KindergartenTableComponent";
import { Link } from "react-router-dom";
import urls from '../../constants/urls'

const KindergartenAdministrationComponent = ({ kindergartens }) => {
  return (
    <div className="col-12 text-center">
      <Link to={`${urls.educationSpecialist.kindergartenBase}/new`} className="btn btn-yellow text-left" >
        Pridėti naują darželį
        </Link>
      {kindergartens.length > 0 &&
        <KindergartenTableComponent
          kindergartens={kindergartens}
        />}
    </div>
  );
};

KindergartenAdministrationComponent.propTypes = {
  kindergartens: PropTypes.array.isRequired
}

export default KindergartenAdministrationComponent;
