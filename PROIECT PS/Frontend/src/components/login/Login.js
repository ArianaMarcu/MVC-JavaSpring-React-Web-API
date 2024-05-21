import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const navigate = useNavigate();
    const credentials = `${username}:${password}`;
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedUser = jwtDecode(token);
            setUser(decodedUser);
            setIsAuthenticated(true);
        }
    }, []);

    const handleLogin = async (e) => {
        e.preventDefault();
        const response = await fetch(`http://localhost:8080/login?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`, 
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Basic ${btoa(credentials)}`
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });
        
        if (response.ok) {
            const token = await response.text();
            const decodedUser = jwtDecode(token);
            setUser(decodedUser);
            localStorage.setItem('token', token);
            localStorage.setItem('isAuthenticated', 'true');
            setIsAuthenticated(true);
            navigate('/');
            //setIsAuthenticated(true);
        } else {
            setError('Invalid username or password');
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        setUser(null);
        localStorage.removeItem('isAuthenticated');
        setIsAuthenticated(false);
        navigate('/login');
    };

    return (
        <div style={{ 
            backgroundImage: 'url("/movietime.jpg")', 
            backgroundPosition: 'center', 
            backgroundSize: 'cover', 
            display: 'flex', 
            justifyContent: 'flex-start', 
            alignItems: 'center', 
            height: '100vh', 
            paddingLeft: '150px'
        }}>
            <div style={{ 
                maxWidth: '400px', 
                width: '100%', 
                padding: '20px', 
                border: '1px solid #ccc', 
                borderRadius: '4px', 
                backgroundColor: 'rgba(255, 255, 255, 0.1)', 
                backdropFilter: 'blur(10px)' 
            }}>
                {isAuthenticated ? (
                    <div>
                        <h2>Welcome, {user.username}!</h2>
                        <button onClick={handleLogout} className="btn btn-primary">Logout</button>
                    </div>
                ) : (
                    <form onSubmit={handleLogin}>
                        <h2>Login</h2>
                        {error && <p style={{ color: 'red' }}>{error}</p>}
                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">Username</label>
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                name="username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                                size="sm"
                                style={{ color: 'black' }}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">Password</label>
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                name="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                                size="sm"
                                style={{ color: 'black' }}
                            />
                        </div>
                        <button type="submit" className="btn btn-primary">Login</button>
                    </form>
                )}
            </div>
        </div>
    );
};

export default Login;
