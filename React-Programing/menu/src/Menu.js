import React from "react";

const Menu = ({ menu }) => {
  return (
    <div className="container menu-cards">
      {menu.map((item,index) => {
        return (
          <div className="menu-card" key={index}>
            <img className="menu-image" src={item.img} />
            <div style={{width : "100%"}}>
              <header className="card-title">
                <div style={{textTransform : "capitalize"}}>{item.title}</div>
                <div>&#36;{item.price}</div>
              </header>
              {/* <hr/> */}
              <div style={{textTransform : "capitalize"}}>
                  {item.desc}
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default Menu;
