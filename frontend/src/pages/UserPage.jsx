import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { getUserMedia } from "../api/userMediaApi";
import { Link } from "react-router-dom";
import "./UserPage.css";

export default function UserPage() {
    const { user } = useAuth();
    const [entries, setEntries] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!user) return;
        getUserMedia(user.id)
            .then(res => setEntries(res.data))
            .catch(() => setError("Could not load your stats."))
            .finally(() => setLoading(false));
    }, [user]);

    if (!user) {
        return (
            <div className="profile-page">
                <p className="profile-message">
                    <Link to="/login">Log in</Link> to view your profile.
                </p>
            </div>
        );
    }

    const watched = entries.filter(e => e.status === "WATCHED").length;
    const watching = entries.filter(e => e.status === "IS_WATCHING").length;
    const watchlist = entries.filter(e => e.status === "WATCHLIST").length;
    const movies = entries.filter(e => e.mediaType === "MOVIES").length;
    const series = entries.filter(e => e.mediaType === "SERIES").length;

    return (
        <div className="profile-page">
            <div className="profile-header">
                <div className="profile-avatar">{user.username.charAt(0).toUpperCase()}</div>
                <div>
                    <h1>{user.username}</h1>
                    <p>{user.email}</p>
                </div>
            </div>

            {loading && <p className="profile-message">Loading stats...</p>}
            {error && <p className="profile-message error">{error}</p>}

            {!loading && !error && (
                <>
                    <div className="profile-stats">
                        <div className="stat-card">
                            <span className="stat-number">{entries.length}</span>
                            <span className="stat-label">Total Tracked</span>
                        </div>
                        <div className="stat-card">
                            <span className="stat-number">{watched}</span>
                            <span className="stat-label">Watched</span>
                        </div>
                        <div className="stat-card">
                            <span className="stat-number">{watching}</span>
                            <span className="stat-label">Watching</span>
                        </div>
                        <div className="stat-card">
                            <span className="stat-number">{watchlist}</span>
                            <span className="stat-label">Watchlist</span>
                        </div>
                    </div>

                    <div className="profile-breakdown">
                        <div className="breakdown-row">
                            <span>Movies</span>
                            <div className="breakdown-bar">
                                <div
                                    className="breakdown-fill"
                                    style={{ width: entries.length ? `${(movies / entries.length) * 100}%` : "0%" }}
                                />
                            </div>
                            <span>{movies}</span>
                        </div>
                        <div className="breakdown-row">
                            <span>Series</span>
                            <div className="breakdown-bar">
                                <div
                                    className="breakdown-fill"
                                    style={{ width: entries.length ? `${(series / entries.length) * 100}%` : "0%" }}
                                />
                            </div>
                            <span>{series}</span>
                        </div>
                    </div>
                </>
            )}
        </div>
    );
}