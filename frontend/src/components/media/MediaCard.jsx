import './MediaCard.css';

export default function MediaCard({ media }) {
    return (
        <div className="media-card">
            <div className="media-card-poster">
                {media.posterUrl
                    ? <img src={media.posterUrl} alt={media.title} />
                    : <div className="media-card-placeholder">{media.title.charAt(0)}</div>
                }
                <span className="media-card-type">{media.type === 'SERIES' ? 'Series' : 'Movie'}</span>
            </div>
            <div className="media-card-info">
                <h3>{media.title}</h3>
                <p>{media.releaseYear}{media.genre ? ` · ${media.genre}` : ''}</p>
            </div>
        </div>
    );
}