import React from "react";

const Categories = ({ categories, setMenuAccordingToCategory }) => {
  return (
    <div className="category-title">
      {categories.map((category,index) => {
        return <a key={index} className="category" onClick={()=>setMenuAccordingToCategory(category)}>{category}</a>;
      })}
    </div>
  );
};

export default Categories;
