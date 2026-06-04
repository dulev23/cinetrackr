import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getAllMedia } from '../api/mediaApi';
import MediaCard from '../components/media/MediaCard';
import './Home.css';

export default function HomePage() {
    const [media, setMedia] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        getAllMedia()
            .then(res => setMedia(res.data))
            .catch(() => setError('Could not load media. Is the backend running?'))
            .finally(() => setLoading(false));
    }, []);

    const movies = media.filter(m => m.type === 'MOVIES').length;
    const series = media.filter(m => m.type === 'SERIES').length;

    return (
        <div className="home">
            <div className="home-hero">
                <h1>CineTrackr</h1>
                <p>Track everything you watch. Movies, series, all in one place.</p>
                <Link to="/media" className="home-cta">Browse Library</Link>
            </div>

            <div className="home-stats">
                <div className="stat">
                    <span className="stat-number">{media.length}</span>
                    <span className="stat-label">Total</span>
                </div>
                <div className="stat">
                    <span className="stat-number">{movies}</span>
                    <span className="stat-label">Movies</span>
                </div>
                <div className="stat">
                    <span className="stat-number">{series}</span>
                    <span className="stat-label">Series</span>
                </div>
            </div>

            <div className="home-section">
                <div className="home-section-header">
                    <h2>Recently Added</h2>
                    <Link to="/media">View all</Link>
                </div>

                {loading && <p className="home-message">Loading...</p>}
                {error && <p className="home-message error">{error}</p>}

                {!loading && !error && media.length === 0 && (
                    <p className="home-message">No media yet. Add some from the backend.</p>
                )}

                <div className="home-grid">
                    {media.slice(0, 6).map(m => <MediaCard key={m.id} media={m} />)}
                </div>
            </div>
        </div>
    );
}