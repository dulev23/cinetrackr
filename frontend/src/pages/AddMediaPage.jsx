import { useState } from "react";
import axiosInstance from "../api/axiosInstance";
import { useNavigate } from "react-router-dom";
import "./AddMediaPage.css";

export default function AddMediaPage() {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        title: "",
        type: "MOVIES",
        releaseYear: "",
        genre: "",
        description: "",
        posterUrl: ""
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await axiosInstance.post("/api/media", form);
        navigate("/");
    };

    return (
        <div className="add-page">
            <form onSubmit={handleSubmit}>
                <h1>Add Media</h1>

                <label>Title</label>
                <input name="title" onChange={handleChange} placeholder="Title" required />

                <label>Type</label>
                <select name="type" value={form.type} onChange={handleChange}>
                    <option value="MOVIES">Movies</option>
                    <option value="SERIES">Series</option>
                </select>

                <label>Release Year</label>
                <input name="releaseYear" onChange={handleChange} placeholder="Year" type="number" required />

                <label>Genre</label>
                <input name="genre" onChange={handleChange} placeholder="Genre" />

                <label>Description</label>
                <textarea name="description" onChange={handleChange} placeholder="Description" rows={4} />

                <label>Poster URL</label>
                <input name="posterUrl" onChange={handleChange} placeholder="https://..." />

                <button type="submit">Add Media</button>
            </form>
        </div>
    );
}