import { useEffect, useState, useCallback } from 'react';
import { getAllMedia } from '../api/mediaApi';
import { getUserMedia } from '../api/userMediaApi';
import { useAuth } from '../context/AuthContext';
import MediaCard from '../components/media/MediaCard';
import './Media.css';

const FILTERS = [
    { label: 'All', value: 'ALL' },
    { label: 'Movies', value: 'MOVIES' },
    { label: 'Series', value: 'SERIES' },
];

export default function MediaPage() {
    const { user } = useAuth();
    const [media, setMedia] = useState([]);
    const [userMedia, setUserMedia] = useState([]);
    const [filter, setFilter] = useState('ALL');
    const [search, setSearch] = useState('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        getAllMedia()
            .then(res => setMedia(res.data))
            .catch(() => setError('Could not load media. Is the backend running?'))
            .finally(() => setLoading(false));
    }, []);

    const fetchUserMedia = useCallback(() => {
        if (!user) {
            setUserMedia([]);
            return;
        }
        getUserMedia(user.id)
            .then(res => setUserMedia(res.data))
            .catch(() => {});
    }, [user]);

    useEffect(() => {
        fetchUserMedia();
    }, [fetchUserMedia]);

    const filtered = media
        .filter(m => filter === 'ALL' || m.type === filter)
        .filter(m => m.title.toLowerCase().includes(search.toLowerCase()));

    return (
        <div className="media-page">
            <div className="media-page-header">
                <h1>Browse</h1>
                <div className="media-page-controls">
                    <input
                        type="text"
                        placeholder="Search..."
                        value={search}
                        onChange={e => setSearch(e.target.value)}
                        className="media-search"
                    />
                    <div className="media-filters">
                        {FILTERS.map(f => (
                            <button
                                key={f.value}
                                className={filter === f.value ? 'active' : ''}
                                onClick={() => setFilter(f.value)}
                            >
                                {f.label}
                            </button>
                        ))}
                    </div>
                </div>
            </div>

            {loading && <p className="media-message">Loading...</p>}
            {error && <p className="media-message error">{error}</p>}

            {!loading && !error && filtered.length === 0 && (
                <p className="media-message">No results found.</p>
            )}

            <div className="media-grid">
                {filtered.map(m => (
                    <MediaCard
                        key={m.id}
                        media={m}
                        userMedia={userMedia}
                        userId={user?.id}
                        onStatusChange={fetchUserMedia}
                    />
                ))}
            </div>
        </div>
    );
}