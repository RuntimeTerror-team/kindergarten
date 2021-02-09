import React from "react";

const KindergartenGroupCreationComponent = ({
  ageRanges,
  title,
  childrenCount,
  handleFormChange,
  handleGroupCreation,
}) => {
  return (
    <form className="mt-4" onSubmit={handleGroupCreation}>
      <div className="form-group row">
        <label htmlFor="title" className="col-3 pt-2 text-right">
          Pavadinimas
        </label>
        <input
          type="text"
          className="form-control col-9"
          id="title"
          name="title"
          value={title}
          onChange={handleFormChange}
        />
      </div>
      <div className="form-group row">
        <label htmlFor="ageRange" className="col-3 mt-2 text-right">
          Amžiaus grupės
        </label>
        <select className="form-control col-9" id="ageRange" name="ageRangeId" onChange={handleFormChange}>
          <option defaultValue>Pasirinkti...</option>
          {ageRanges.map(({ id, minAge, maxAge }) => {
            return (
              <option key={id} id={id} value={id}>
                {minAge} - {maxAge}
              </option>
            );
          })}
        </select>
      </div>
      <div className="form-group row">
        <label htmlFor="childrenCount" className="col-3 pt-2 text-right">
          Vaikų skaičius
        </label>
        <input
          type="number"
          className="form-control col-9"
          id="childrenCount"
          name="childrenCount"
          value={childrenCount}
          onChange={handleFormChange}
        />
      </div>
      <button type="submit" className="btn btn-green">
        Išsaugoti
      </button>
    </form>
  );
};

export default KindergartenGroupCreationComponent;
