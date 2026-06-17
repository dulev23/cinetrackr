import { useEffect, useState, useCallback } from "react";
import { useParams, Link } from "react-router-dom";
import { getMediaById } from "../../api/mediaApi";
import { getUserMedia, addUserMedia, updateUserMedia } from "../../api/userMediaApi";
import { useAuth } from "../../context/AuthContext";
import "./MediaDetailsPage.css";

const STATUS_OPTIONS = [
    { label: "Watchlist", value: "WATCHLIST" },
    { label: "Watching", value: "IS_WATCHING" },
    { label: "Watched", value: "WATCHED" },
];

export default function MediaDetailPage() {
    const { id } = useParams();
    const { user } = useAuth();
    const mediaId = Number(id);

    const [media, setMedia] = useState(null);
    const [entry, setEntry] = useState(null);
    const [loading, setLoading] = useState(true);
    const [saving, setSaving] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!mediaId || isNaN(mediaId)) return;
        getMediaById(mediaId)
            .then(res => setMedia(res.data))
            .catch(() => setError("Could not load this title."))
            .finally(() => setLoading(false));
    }, [mediaId]);

    const fetchEntry = useCallback(() => {
        if (!user) {
            setEntry(null);
            return;
        }
        getUserMedia(user.id)
            .then(res => {
                const found = res.data.find(um => um.mediaId === mediaId);
                setEntry(found || null);
            })
            .catch(() => {});
    }, [user, mediaId]);

    useEffect(() => {
        fetchEntry();
    }, [fetchEntry]);

    const setStatus = async (status) => {
        if (!user) return;
        setSaving(true);
        try {
            if (entry) {
                await updateUserMedia(entry.id, {
                    userId: user.id,
                    mediaId,
                    status,
                    rating: entry.rating || 0,
                });
            } else {
                await addUserMedia({
                    userId: user.id,
                    mediaId,
                    status,
                    rating: 0,
                });
            }
            fetchEntry();
        } catch {
            setError("Could not update status.");
        } finally {
            setSaving(false);
        }
    };

    if (loading) return <p className="media-detail-message">Loading...</p>;
    if (error) return <p className="media-detail-message error">{error}</p>;
    if (!media) return <p className="media-detail-message">Title not found.</p>;

    return (
        <div className="media-detail">
            <Link to="/media" className="media-detail-back">← Back to Browse</Link>

            <div className="media-detail-content">
                <div className="media-detail-poster">
                    {media.posterUrl ? (
                        <img src={media.posterUrl} alt={media.title} />
                    ) : (
                        <div className="media-detail-placeholder">{media.title.charAt(0)}</div>
                    )}
                </div>

                <div className="media-detail-info">
                    <span className="media-detail-type">
                        {media.type === "SERIES" ? "TV Series" : "Movie"}
                    </span>
                    <h1>{media.title}</h1>
                    <p className="media-detail-meta">
                        {media.releaseYear}{media.genre ? ` · ${media.genre}` : ""}
                    </p>
                    <p className="media-detail-description">{media.description}</p>

                    {user ? (
                        <div className="media-detail-actions">
                            <span className="media-detail-actions-label">Your status</span>
                            <div className="media-detail-status-group">
                                {STATUS_OPTIONS.map(opt => (
                                    <button
                                        key={opt.value}
                                        className={entry?.status === opt.value ? "active" : ""}
                                        disabled={saving}
                                        onClick={() => setStatus(opt.value)}
                                    >
                                        {opt.label}
                                    </button>
                                ))}
                            </div>
                        </div>
                    ) : (
                        <p className="media-detail-login-hint">
                            <Link to="/login">Log in</Link> to add this to your list.
                        </p>
                    )}
                </div>
            </div>
        </div>
    );
}