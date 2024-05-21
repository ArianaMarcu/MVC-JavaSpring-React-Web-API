import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFilm, faSignInAlt, faUserPlus } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { NavLink } from "react-router-dom";
import React, { useState, useEffect } from 'react';

const Header = () => {
    const buttonStyle = {
        color: 'white', 
        borderColor: 'white'
    };

    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const isAuth = localStorage.getItem('isAuthenticated') === 'true';
            setIsAuthenticated(isAuth);
        }
    }, []); 

    return (
        <Navbar bg="dark" variant="dark" expand="lg">
            <Container fluid>
                <Navbar.Brand as={NavLink} to="/" style={{ fontSize: '30px', textDecoration: 'none', color: 'white', fontFamily: 'Satisfy, cursive' }}>
                    <FontAwesomeIcon icon={faFilm} className="me-2" />  MovieIdeas
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav className="me-auto my-2 my-lg-0" style={{ maxHeight: '100px' }} navbarScroll>
                    {isAuthenticated && <NavLink className="nav-link" to="/watchList">Watch List</NavLink>}
                    </Nav>
                    <Button variant="outline-info" className="me-2" as={NavLink} to="/login" style={buttonStyle}>
                        <FontAwesomeIcon icon={faSignInAlt} className="me-1" />Login
                    </Button>
                    <Button variant="outline-info" as={NavLink} to="/register" style={buttonStyle}>
                        <FontAwesomeIcon icon={faUserPlus} className="me-1" />Register
                    </Button>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;
