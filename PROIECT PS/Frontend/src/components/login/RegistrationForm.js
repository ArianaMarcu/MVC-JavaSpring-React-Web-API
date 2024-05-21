import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Card from 'react-bootstrap/Card';
import 'bootstrap/dist/css/bootstrap.min.css';

const RegistrationForm = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        const userData = {
            username,
            email,
            password,
        };

        try {
            const response = await fetch(`http://localhost:8080/register?username=${encodeURIComponent(username)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            });

            if (response.ok) {
                console.log('Registration successful');
                navigate('/login');
            } else {
                const data = await response.json();
                setError(data.message || 'Registration failed');
            }
        } catch (error) {
            setError('An error occurred. Please try again.');
        }
    };

    return (
        <Container
            className="d-flex flex-column align-items-center vh-100"
            style={{
                backgroundImage: 'url("/registr.jpg")', 
                backgroundPosition: 'center', 
                backgroundSize: 'cover',
            }}
        >
            <Card
                style={{
                    width: '300px',
                    backgroundColor: 'rgba(255, 255, 255, 0.1)',
                    backdropFilter: 'blur(10px)',
                    color: 'white',
                    marginTop: '100px',
                }}
            >
                <Card.Body>
                    <h2 style={{ color: 'white', textAlign: 'center' }}>Register</h2>
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                    <Form onSubmit={handleSubmit}>
                        <Form.Group controlId="formUsername">
                            <Form.Label style={{ color: 'white' }}>Username</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                                size="sm"
                                style={{ color: 'black' }}
                            />
                        </Form.Group>
                        <Form.Group controlId="formEmail">
                            <Form.Label style={{ color: 'white' }}>Email address</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="Enter email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                                size="sm"
                                style={{ color: 'black' }}
                            />
                        </Form.Group>
                        <Form.Group controlId="formPassword">
                            <Form.Label style={{ color: 'white' }}>Password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                                size="sm"
                                style={{ color: 'black' }}
                            />
                        </Form.Group>
                        <Button variant="primary" type="submit" size="sm" className="mt-3">
                            Register
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </Container>
    );
    
};

export default RegistrationForm;
