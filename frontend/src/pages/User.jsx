import { useEffect, useState } from "react";
import { getUsers, createUser } from "../api/userApi.js";

export default function User() {
    const [users, setUsers] = useState([]);
    const [form, setForm] = useState({
        username: "",
        email: "",
        password: ""
    });

    const loadUsers = async () => {
        const res = await getUsers();
        setUsers(res.data);
    };

    useEffect(() => {
        loadUsers();
    }, []);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await createUser(form);
        setForm({ username: "", email: "", password: "" });
        loadUsers();
    };

    return (
        <div style={{ padding: "20px", maxWidth: "600px", margin: "0 auto" }}>
            <h2>User Management</h2>

            {error && (
                <p style={{ color: "red" }}>{error}</p>
            )}

            {/* FORM */}
            <form
                onSubmit={handleSubmit}
                style={{
                    display: "flex",
                    flexDirection: "column",
                    gap: "10px",
                    marginBottom: "20px"
                }}
            >
                <input
                    name="username"
                    placeholder="Username"
                    value={form.username}
                    onChange={handleChange}
                />

                <input
                    name="email"
                    placeholder="Email"
                    value={form.email}
                    onChange={handleChange}
                />

                <input
                    name="password"
                    placeholder="Password"
                    type="password"
                    value={form.password}
                    onChange={handleChange}
                />

                <button type="submit">
                    Create User
                </button>
            </form>

            {/* LIST */}
            <h3>Users</h3>

            {loading ? (
                <p>Loading...</p>
            ) : (
                <ul>
                    {users.map((u) => (
                        <li key={u.id}>
                            <strong>{u.username}</strong> — {u.email}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}