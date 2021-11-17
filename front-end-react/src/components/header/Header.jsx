import "./style.scss";
import logoImg from "../../assets/logoWhiteWhite.png";
import { Link } from 'react-router-dom';
import { Link as Scroll } from 'react-scroll';


function Header() {

    var prevScrollpos = window.pageYOffset;
    window.onscroll = function() {
        var currentScrollPos = window.pageYOffset;
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("rootHeader").style.top = "0";
        } else {
            document.getElementById("rootHeader").style.top = "-75px";
        }
        prevScrollpos = currentScrollPos;
    }

    return (
        <>
            <header className="header container" id="rootHeader">
                <div className="header_imageContainer">
                    <Link to="/" className="header_imageContainer_link">
                        <img className="header_imageContainer_link_imageLogo" src={logoImg} />
                    </Link>
                </div>
                <ul className="header_categories">
                    <li className="header_categories_category"><Scroll activeClass="active" to="main_section" spy={true} smooth={true}><Link to="/" className="header_categories_category_link">Home</Link></Scroll></li>
                    <li className="header_categories_category"><Scroll activeClass="active" to="about_section" spy={true} smooth={true}><Link to="/" className="header_categories_category_link">Quem somos</Link></Scroll></li>
                    <li className="header_categories_category"><Scroll activeClass="active" to="weDo_section" spy={true} smooth={true}><Link to="/" className="header_categories_category_link">O que fazemos</Link></Scroll></li>
                    <li className="header_categories_category"><Scroll activeClass="active" to="benefit_section" spy={true} smooth={true}><Link to="/" className="header_categories_category_link">Beneficios</Link></Scroll></li>
                    <li className="header_categories_category"><Scroll activeClass="active" to="contactUs_section" spy={true} smooth={true}><Link to="/" className="header_categories_category_link">Fale conosco</Link></Scroll></li>
                </ul>
                <div className="header_login">
                    <Link to="/login" className="header_login_button">Login</Link>
                </div>
            </header>
        </>
    );
}

export default Header;