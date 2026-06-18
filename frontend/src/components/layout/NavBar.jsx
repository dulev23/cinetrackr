import { Link, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import "./NavBar.css";

export default function Navbar() {
    const { user, logout } = useAuth();
    const location = useLocation();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/");
    };

    const isActive = (path) => location.pathname === path;

    return (
        <nav className="navbar">
            <div className="nav-left">
                <Link to="/" className="logo">CineTrackr</Link>
                <Link to="/" className={isActive("/") ? "active" : ""}>Home</Link>
                <Link to="/media" className={isActive("/media") ? "active" : ""}>Browse</Link>
                {user && (
                    <Link to="/my-list" className={isActive("/my-list") ? "active" : ""}>My List</Link>
                )}
                {user && (
                    <Link to="/add-media" className={isActive("/add-media") ? "active" : ""}>Add Media</Link>
                )}
            </div>

            <div className="nav-right">
                {user ? (
                    <>
                        <Link to="/profile" className="username">{user.username}</Link>
                        <button className="logout-btn" onClick={handleLogout}>Log out</button>
                    </>
                ) : (
                    <>
                        <Link to="/login" className={isActive("/login") ? "active" : ""}>Login</Link>
                        <Link to="/register" className={isActive("/register") ? "active" : ""}>Register</Link>
                    </>
                )}
            </div>
        </nav>
    );
}