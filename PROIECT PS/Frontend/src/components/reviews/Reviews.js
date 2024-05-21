import { useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import ReviewForm from '../reviewForm/ReviewForm';
import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStar } from '@fortawesome/free-solid-svg-icons';

const Reviews = () => {
    const [reviews, setReviews] = React.useState([]);
    const [movie, setMovie] = React.useState({});
    const revText = useRef();
    let params = useParams();
    const movieId = params.movieId;

    useEffect(() => {
        const id = localStorage.getItem("selectedMovieID");
        
        fetch(`http://localhost:8080/admin/movie/${movieId}/reviews?id=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => setReviews(data))
        .catch(err => console.error(err));

        fetch(`http://localhost:8080/admin/movie${id}`)
        .then(response => response.json())
        .then(data => setMovie(data))
        .catch(err => console.error(err));
    }, []);

    const addReview = async (e) => {
        e.preventDefault();

        const rev = revText.current;

        try 
        {
            const response = await fetch(`http://localhost:8080/admin/addReview/${movieId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ body: rev.value, id: movieId })
            });
        
            const newReview = await response.json();
          
            const updatedReviews = [newReview, ...reviews];

            rev.value = "";

            setReviews(updatedReviews);
        } catch (err) {
            console.error(err);
        }
    }

    return (
        <Container>
            <Row>
                <Col>
                    <h3><FontAwesomeIcon icon={faStar} className="review-icon" />  Reviews</h3>
                </Col>
            </Row>
            <Row className="mt-2">
                <Col>
                    <img src={movie?.poster} alt="" />
                </Col>
                <Col>
                    <>
                        <Row>
                            <Col>
                                <ReviewForm handleSubmit={addReview} revText={revText} labelText="What did you think of this movie?" />
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <hr />
                            </Col>
                        </Row>
                    </>
                    {reviews?.map((r, index) => (
                        <div key={index}>
                            <Row>
                                <Col>{r.body}</Col>
                            </Row>
                            <Row>
                                <Col>
                                    <hr />
                                </Col>
                            </Row>
                        </div>
                    ))}
                </Col>
            </Row>
            <Row>
                <Col>
                    <hr />
                </Col>
            </Row>
        </Container>
    );
}

export default Reviews;
