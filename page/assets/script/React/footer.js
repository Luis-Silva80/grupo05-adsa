var footer = document.getElementById("footer");


function FooterComponent() {
    return (
        <React.Fragment>
            <div className="footer_imageContainer">
                <a href="footer_imageContainer_link">
                    <img className="footer_imageContainer_link_imageLogo" src="./assets/imgs/logoWhiteWhite.png" />
                </a>
                </div>
                
                <div className="footer_textContainer">
                <p className="footer_textContainer_text">
                    Desenvolvido por TOTH LIBS © 2021
                </p>
            </div>
        
            <div className="footer_icons">
                <a href="https://github.com/Luis-Silva80/grupo05-adsa" className="footer_icons_link">
                    <img src="./assets/imgs/github.png" className="footer_icons_link_image" />
                </a>
                <a href="#" className="footer_icons_link">
                    <img src="./assets/imgs/linkedin.png" className="footer_icons_link_image" />
                </a>
                <a href="#" className="footer_icons_link">
                    <img src="./assets/imgs/whatsapp.png" className="footer_icons_link_image" />
                </a>
                <a href="#" className="footer_icons_link">
                    <img src="./assets/imgs/youtube.png" className="footer_icons_link_image" />
                </a>
            </div>
        </React.Fragment>
    );
}

ReactDOM.render(React.createElement(FooterComponent), footer)