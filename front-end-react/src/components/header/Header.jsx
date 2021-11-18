import React, { useEffect, useState, useCallback } from "react";
import "./style.scss";
import logoImg from "../../assets/logoWhiteWhite.png";
import hamburgerMenu from "../../assets/hamburgerMenu.png";
import { Link } from 'react-router-dom';
import { Link as Scroll } from 'react-scroll';


function Header() {

    const [ isSelect, setIsSelect ] = useState(false);

    const handleClick = useCallback(() => {
        setIsSelect(!isSelect);
    }, [isSelect])

    var prevScrollpos = window.pageYOffset;
    window.onscroll = function() {
        console.log(prevScrollpos, "prevScrollpos aqui");
        var currentScrollPos = window.pageYOffset;
        console.log(currentScrollPos, "currentScrollPos aqui");
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("rootHeader").style.top = "0";
            document.getElementById("rootHeaderMobile").style.top = "0";
        } else {
            document.getElementById("rootHeader").style.top = "-75px";
            document.getElementById("rootHeaderMobile").style.top = "-75px";
            document.querySelector(".headerMobile_open").style.top = "-8px";
            setIsSelect(false);
        }
        prevScrollpos = currentScrollPos;
    }

    useEffect(() => {
        if (isSelect) {
            console.log("Cliquei");
            document.querySelector(".headerMobile_open").style.top = "69px";
        } else {
            console.log("Não cliquei");
            document.querySelector(".headerMobile_open").style.top = "-8px";
        }
    }, [isSelect]);


    // Verificando o width da tela em js caso precise ->
    
    // if (window.screen.width < 767) {
    //     console.log(window.screen.width, "is mobile");
    // } else {
    //     console.log(window.screen.width, "is desktop");
    // }

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

            <header className="headerMobile container" id="rootHeaderMobile">
                <div className="headerMobile_topo">
                    <img className="headerMobile_topo_hamburger" src={hamburgerMenu} onClick={handleClick} />
                    <Link to="/" className="headerMobile_topo_link">
                        <img src={logoImg}/>
                    </Link>
                    <div className="headerMobile_topo_login">
                        <Link to="/login" className="headerMobile_topo_login_button">Login</Link>
                    </div>
                </div>
                <div className="headerMobile_open">
                    <ul className="headerMobile_open_categories">
                        <li className="headerMobile_open_categories_category"><Scroll activeClass="active" to="about_section" spy={true} smooth={true}><Link to="/" className="headerMobile_open_categories_category_link">Quem somos</Link></Scroll></li>
                        <li className="headerMobile_open_categories_category"><Scroll activeClass="active" to="weDo_section" spy={true} smooth={true}><Link to="/" className="headerMobile_open_categories_category_link">O que fazemos</Link></Scroll></li>
                        <li className="headerMobile_open_categories_category"><Scroll activeClass="active" to="benefit_section" spy={true} smooth={true}><Link to="/" className="headerMobile_open_categories_category_link">Beneficios</Link></Scroll></li>
                        <li className="headerMobile_open_categories_category"><Scroll activeClass="active" to="contactUs_section" spy={true} smooth={true}><Link to="/" className="headerMobile_open_categories_category_link">Fale conosco</Link></Scroll></li>
                    </ul>
                </div>
            </header>
        </>
    );
}

export default Header;