import { NavLink } from "react-router-dom";

const HomePage = () => (
    <div>
        <NavLink className="nav-link" to="/login">
            <h1>Administratorius</h1>
        </NavLink>
    </div>
)

export default HomePage;