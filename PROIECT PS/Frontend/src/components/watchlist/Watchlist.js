import React, { useEffect, useState } from 'react';
import { jwtDecode } from 'jwt-decode';
import './Watchlist.css';

const Watchlist = () => {
    const [watchlist, setWatchlist] = useState([]);
    const [error, setError] = useState(null);
    const token = localStorage.getItem('token');
    const user = jwtDecode(token);
    const username = user.username;

    useEffect(() => {
        const fetchWatchlist = async () => {
            try {
                const credentials = 'iulia:7';
                const response = await fetch(`http://localhost:8080/home/myWatchlistNew/${username}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Basic ${btoa(credentials)}`
                    }
                });
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                setWatchlist(data);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchWatchlist();
    }, [username]);

    return (
        <div style={{ 
            backgroundImage: 'url("/watchlist.jpeg")', 
            backgroundPosition: 'center', 
            backgroundSize: 'cover', 
            display: 'flex', 
            justifyContent: 'flex-start', 
            alignItems: 'center', 
            height: '100vh', 
            paddingLeft: '20px'
        }}>
        <div className="watchlist-container">
            <h1 className="watchlist-title">My Watchlist</h1>
            {error && <p className="error-message">Error: {error}</p>}
            {watchlist.length === 0 && <p className="empty-watchlist">No movies in your watchlist.</p>}
            <ul className="movie-list">
                {watchlist.map((movie, index) => (
                    <li key={index} className="movie-item">
                        <div className="movie-details">
                            <h2 className="movie-title">{movie.title}</h2>
                            <p className="movie-info">IMDB id: {movie.imdb_Id}</p>
                            <p className="movie-info">Release Date: {movie.release_Date}</p>
                            <p className="movie-info">Genres: {movie.genres}</p>
                        </div>
                        <img src={movie.backdrops} alt={`${movie.title} Backdrop`} className="movie-backdrop" />
                    </li>
                ))}
            </ul>
        </div>
        </div>
    );
};

export default Watchlist;
