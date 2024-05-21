import './Hero.css';
import Carousel from 'react-material-ui-carousel';
import { Paper } from '@mui/material';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlay, faStar } from '@fortawesome/free-solid-svg-icons';
import { Link, useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button';

const Hero = ({ movies }) => {
    const navigate = useNavigate();

    function reviews(movieId) {
        navigate(`/Reviews/${movieId}`);
        localStorage.setItem("selectedMovieID", movieId);
    }

    return (
        <div className='movie-carousel-container'>
            <Carousel>
                {movies &&
                    movies.map((movie) => (
                        <Paper key={movie.id}>
                            <div className='movie-card-container'>
                                <div className="movie-card" style={{ "--img": `url(${movie.backdrops})` }}>
                                    <div className="movie-detail">
                                        <div className="movie-poster">
                                            <img src={movie.poster} alt="" />
                                        </div>
                                        <div className="movie-title1">
                                            {movie.title}
                                        </div>
                                        <div className="movie-buttons-container">
                                            <Link to={`/Trailer/${movie.trailer_Link.substring(movie.trailer_Link.length - 11)}`}>
                                                <div className="play-button-icon-container">
                                                    <FontAwesomeIcon className="play-button-icon" icon={faCirclePlay} />
                                                </div>
                                            </Link>
                                            
                                            <div className="movie-review-button-container">
                                                <Button variant="info" onClick={() => reviews(movie.id)}>
                                                    <FontAwesomeIcon icon={faStar} className="review-icon" /> Reviews
                                                </Button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </Paper>
                    ))}
            </Carousel>
        </div>
    );
};

export default Hero;
