import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getAllMedia } from "../api/mediaApi";
import MediaCard from "../components/media/MediaCard";
import axiosInstance from "../api/axiosInstance";
import { useAuth } from "../context/AuthContext";
import "./Home.css";

export default function Home() {
    const [media, setMedia] = useState([]);
    const [userMedia, setUserMedia] = useState([]);

    const { user } = useAuth();

    useEffect(() => {
        getAllMedia()
            .then((res) => setMedia(res.data))
    }, []);

    useEffect(() => {
        if (!user?.id) return;

        axiosInstance
            .get(`/api/user-media/user/${user.id}`)
            .then((res) => setUserMedia(res.data || []))
            .catch(() => setUserMedia([]));
    }, [user?.id]);

    const movies = media.filter((m) => m.type === "MOVIES").length;
    const series = media.filter((m) => m.type === "SERIES").length;

    return (
        <div className="home">
            <div className="home-hero">
                <h1>CineTrackr</h1>
                <p>Track everything you watch.</p>
                <Link to="/media" className="home-cta">
                    Browse Library
                </Link>
            </div>

            <div className="home-stats">
                <div className="stat">
                    <span>{media.length}</span>
                    <span>Total</span>
                </div>
                <div className="stat">
                    <span>{movies}</span>
                    <span>Movies</span>
                </div>
                <div className="stat">
                    <span>{series}</span>
                    <span>Series</span>
                </div>
            </div>

            <div className="home-grid">
                {media.slice(0, 5).map((m) => (
                    <MediaCard
                        key={m.id}
                        media={m}
                        userMedia={userMedia}
                        userId={user?.id}
                    />
                ))}
            </div>
        </div>
    );
}