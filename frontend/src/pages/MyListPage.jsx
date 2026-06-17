import { useEffect, useState, useCallback } from 'react';
import { useAuth } from '../context/AuthContext';
import { getUserMedia } from '../api/userMediaApi';
import MediaCard from '../components/media/MediaCard';
import './MyListPage.css';

const TABS = [
    { label: 'Watching', value: 'IS_WATCHING' },
    { label: 'Watchlist', value: 'WATCHLIST' },
    { label: 'Watched', value: 'WATCHED' },
];

export default function MyListPage() {
    const { user } = useAuth();
    const [activeTab, setActiveTab] = useState('IS_WATCHING');
    const [entries, setEntries] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchEntries = useCallback(() => {
        if (!user) return;
        setLoading(true);
        getUserMedia(user.id, activeTab)
            .then(res => setEntries(res.data))
            .catch(() => setError('Could not load your list.'))
            .finally(() => setLoading(false));
    }, [user, activeTab]);

    useEffect(() => {
        fetchEntries();
    }, [fetchEntries]);

    if (!user) {
        return (
            <div className="mylist-page">
                <p className="mylist-message">Log in to see your list.</p>
            </div>
        );
    }

    return (
        <div className="mylist-page">
            <h1>My List</h1>

            <div className="mylist-tabs">
                {TABS.map(tab => (
                    <button
                        key={tab.value}
                        className={activeTab === tab.value ? 'active' : ''}
                        onClick={() => setActiveTab(tab.value)}
                    >
                        {tab.label}
                    </button>
                ))}
            </div>

            {loading && <p className="mylist-message">Loading...</p>}
            {error && <p className="mylist-message error">{error}</p>}

            {!loading && !error && entries.length === 0 && (
                <p className="mylist-message">Nothing here yet.</p>
            )}

            <div className="mylist-grid">
                {entries.map(entry => (
                    <MediaCard
                        key={entry.id}
                        media={{
                            id: entry.mediaId,
                            title: entry.mediaTitle,
                            posterUrl: entry.mediaPosterUrl,
                            type: entry.mediaType,
                        }}
                        userMedia={entries}
                        userId={user.id}
                        onStatusChange={fetchEntries}
                    />
                ))}
            </div>
        </div>
    );
}