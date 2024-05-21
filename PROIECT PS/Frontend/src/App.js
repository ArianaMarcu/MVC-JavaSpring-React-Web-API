import './App.css';
import { useState, useEffect } from 'react';
import Layout from './components/Layout';
import { Routes, Route } from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import Trailer from './components/trailer/Trailer';
import Reviews from './components/reviews/Reviews';
import NotFound from './components/notFound/NotFound';
import Login from './components/login/Login';
import RegistrationForm from './components/login/RegistrationForm';
import Watchlist from './components/watchlist/Watchlist';

function App() {
  const [movies, setMovies] = useState([]);
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState([]);

  const getMovies = async () => {
    try 
    {
      const response = await fetch('http://localhost:8080/admin/getMovies', 
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      const data1 = await response.json();
      setMovies(data1);
    } 
    catch (err) 
    {
      console.log(err);
    }
  }
  
  const getReviews = async (movieId) => {
    try 
    {
      const response = await fetch(`http://localhost:8080/admin/movie/${movieId}/reviews`,
        {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });
      const data2 = await response.json();
      setReviews(data2);
    } 
    catch (error) 
    {
      console.error(error);
    }
  }
  
  useEffect(() => {
    getMovies();
  }, [])
  

  return (
    <div className="App">
      <Header/>
      <Routes>
          <Route path="/" element={<Layout/>}>
            <Route path="/" element={<Home movies={movies} />} ></Route>
            <Route path="/Trailer/:ytTrailerId" element={<Trailer/>}></Route>
            <Route path="/Reviews/:movieId" element ={<Reviews/>}></Route>
            <Route path="/login" element={<Login/>}></Route>
            <Route path="/register" element={<RegistrationForm/>}></Route>
            <Route path="/watchList" element={<Watchlist/>}></Route>
            <Route path="*" element = {<NotFound/>}></Route>
          </Route>
      </Routes>

    </div>
  );
}

export default App;