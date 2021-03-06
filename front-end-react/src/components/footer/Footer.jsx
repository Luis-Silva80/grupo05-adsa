import React from 'react';
import "./style.scss";
import { Link } from 'react-router-dom';

import logoImg from "../../assets/logoWhiteWhite.png";
import logoGit from "../../assets/github.png";
import logoLinkedin from "../../assets/linkedin.png";
import logoWhats from "../../assets/whatsapp.png";
import logoYouTube from "../../assets/youtube.png";

function Footer() {
    return (
        <>
            <footer className="footer container" id="rootFooter">
                <div className="footer_imageContainer">
                    <Link to="/" className="footer_imageContainer_link">
                        <img className="footer_imageContainer_link_imageLogo" src={logoImg} />
                    </Link>
                    </div>
                    
                    <div className="footer_textContainer">
                    <p className="footer_textContainer_text">
                        Desenvolvido por TOTH LIBS © 2021
                    </p>
                </div>
            
                <div className="footer_icons">
                    <a href="https://github.com/Luis-Silva80/grupo05-adsa" target="_blank" className="footer_icons_link">
                        <img src={logoGit} className="footer_icons_link_image" />
                    </a>
                    <a href="#" className="footer_icons_link">
                        <img src={logoLinkedin} className="footer_icons_link_image" />
                    </a>
                    <a href="#" className="footer_icons_link">
                        <img src={logoWhats} className="footer_icons_link_image" />
                    </a>
                    <a href="#" className="footer_icons_link">
                        <img src={logoYouTube} className="footer_icons_link_image" />
                    </a>
                </div>
            </footer>
        </>
    );
}

export default Footer;