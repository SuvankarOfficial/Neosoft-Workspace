import React, { useState } from "react";
import people from "./data";
import { FaQuoteRight } from "react-icons/fa";

const Review = () => {
  const [index, setIndex] = useState(0);
  const { id, name, job, image, text } = people[index];
  // function getIndex(index) {

  //   else
  //   setIndex(index);
  // }
  function nextPerson() {
    if (index + 1 > people.length - 1) setIndex(0);
    else setIndex(index + 1);
  }
  function previousPerson() {
    if (index - 1 < 0) setIndex(people.length - 1);
    else setIndex(index - 1);
  }

  function getCaptialize(word) {
    let words = word.split(" ");
    for (var i = 0; i < words.length; i++) {
      words[i] = words[i].charAt(0).toUpperCase() + words[i].slice(1);
    }
    return words.join(" ");
  }

  function randomPerson() {
    let randomIndex = Math.floor(Math.random(people.length) * people.length);
    if (randomIndex > people.length) {
      if (randomIndex === index && randomIndex - people.length - 1 === index)
        setIndex(randomIndex - 1);
      else setIndex(randomIndex - people.length - 1);
    } else setIndex(randomIndex);
  }
  return (
    <div className="review-card">
      <div>
        <div className="review-image-background">
          <img src={image} alt={id} className="review-image" />
          <span className="quote-icon">
            <FaQuoteRight />
          </span>
        </div>
      </div>
      <div className="review-name">{getCaptialize(name)}</div>
      <div className="review-job">{getCaptialize(job)}</div>
      <div className="review-text">{text}</div>
      <div className="review-button-area mt-2 mb-2">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="30"
          fill="currentColor"
          className="bi bi-caret-left"
          viewBox="0 0 16 16"
          onClick={previousPerson}
        >
          <path d="M10 12.796V3.204L4.519 8 10 12.796zm-.659.753-5.48-4.796a1 1 0 0 1 0-1.506l5.48-4.796A1 1 0 0 1 11 3.204v9.592a1 1 0 0 1-1.659.753z" />{" "}
        </svg>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="30"
          fill="currentColor"
          className="bi bi-caret-right"
          viewBox="0 0 16 16"
          onClick={nextPerson}
        >
          {" "}
          <path d="M6 12.796V3.204L11.481 8 6 12.796zm.659.753 5.48-4.796a1 1 0 0 0 0-1.506L6.66 2.451C6.011 1.885 5 2.345 5 3.204v9.592a1 1 0 0 0 1.659.753z" />{" "}
        </svg>
      </div>
      <div>
        <button
          type="button"
          className="btn btn-outline-danger"
          onClick={randomPerson}
        >
          Suprise Me
        </button>
      </div>
    </div>
  );
};

export default Review;
