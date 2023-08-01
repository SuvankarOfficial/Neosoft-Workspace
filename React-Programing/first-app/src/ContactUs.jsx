import './ContactUs.css';
import ContactUsForm from './ContactUsForm';
import logo from './image/DBAdmin.jpg';

export default function ContactUs(){
    return (
      <div className="ContactPage">
        <div className="header">
        <h1>Contact Us</h1>
        <h3>Fill out the form and let us know what are you problem and we will try to reach you in 24 hrs.</h3>
        </div>
        <div className="OutSideBox">
            <div className="InsideLeft"> <img className='LeftSideImage'  src={logo} alt="DBAdmin Logo" /></div>
            <div className="InsideRight">
                <ContactUsForm/>
            </div>
        </div>
      </div>  
    );
}