import "./MediaCard.css";
import { Link } from "react-router-dom";
import { addUserMedia, updateUserMedia } from "../../api/userMediaApi";

const STATUSES = ["WATCHED", "IS_WATCHING", "WATCHLIST"];

export default function MediaCard({ media, userMedia = [], userId, onStatusChange }) {

    const entry = userMedia.find((um) => um.mediaId === media.id);
    const status = entry?.status || "";

    const updateStatus = async (newStatus) => {
        if (!userId) return;

        try {
            if (entry) {
                await updateUserMedia(entry.id, {
                    userId,
                    mediaId: media.id,
                    status: newStatus,
                    rating: entry.rating || 0,
                });
            } else {
                await addUserMedia({
                    userId,
                    mediaId: media.id,
                    status: newStatus,
                    rating: 0,
                });
            }

            if (onStatusChange) onStatusChange();
        } catch (err) {
            console.error("Failed to update status", err);
        }
    };

    return (
        <div className="media-card">
            <Link to={`/media/${media.id}`}>
                <img src={media.posterUrl || ""} alt={media.title} />

                <div className="media-card-info">
                    <h3>{media.title}</h3>
                    <p>
                        {media.releaseYear ?? ""}{" "}
                        {media.genre ? `· ${media.genre}` : ""}
                    </p>
                </div>
            </Link>

            <select
                value={status}
                onChange={(e) => updateStatus(e.target.value)}
                onClick={(e) => e.stopPropagation()}
            >
                <option value="">Add to MyList</option>
                {STATUSES.map((s) => (
                    <option key={s} value={s}>
                        {s}
                    </option>
                ))}
            </select>
        </div>
    );
}