import { Link, useLocation } from 'react-router-dom';
import './Navbar.css';

export default function Navbar() {
    const location = useLocation();

    return (
        <nav className="navbar">
            <Link to="/" className="navbar-brand">CineTrackr</Link>
            <div className="navbar-links">
                <Link to="/" className={location.pathname === '/' ? 'active' : ''}>Home</Link>
                <Link to="/media" className={location.pathname === '/media' ? 'active' : ''}>Browse</Link>
            </div>
        </nav>
    );
}