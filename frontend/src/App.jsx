import {BrowserRouter, Routes, Route} from 'react-router-dom';
import {AuthProvider} from './context/AuthContext';
import Navbar from './components/layout/Navbar';
import Home from './pages/Home';
import Media from './pages/Media';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import MyListPage from './pages/MyListPage';
import AddMediaPage from "./pages/AddMediaPage.jsx";
import MediaDetailsPage from "./components/media/MediaDetailsPage.jsx";
import UserPage from "./pages/UserPage.jsx";
import './styles/global.css';

export default function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Navbar/>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/media" element={<Media/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegisterPage/>}/>
                    <Route path="/my-list" element={<MyListPage/>}/>
                    <Route path="/add-media" element={<AddMediaPage />} />
                    <Route path="/media/:id" element={<MediaDetailsPage />} />
                    <Route path="/profile" element={<UserPage />} />
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}