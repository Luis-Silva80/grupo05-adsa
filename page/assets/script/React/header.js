var header = document.getElementById("header");


function HeaderComponent() {
    return (
        <React.Fragment>
            <div className="header_imageContainer">
                <a href="" className="header_imageContainer_link">
                    <img className="header_imageContainer_link_imageLogo" src="./assets/imgs/logoWhiteWhite.png" />
                </a>
            </div>
            <ul className="header_categories">
                <li className="header_categories_category"><a className="header_categories_category_link" href="">Home</a></li>
                <li className="header_categories_category"><a className="header_categories_category_link" href="">Quem somos</a></li>
                <li className="header_categories_category"><a className="header_categories_category_link" href="">O que fazemos</a></li>
                <li className="header_categories_category"><a className="header_categories_category_link" href="">Fale conosco</a></li>
            </ul>
            <div className="header_login">
                <button className="header_login_button">Login</button>
            </div>
        </React.Fragment>
    );
}

ReactDOM.render(React.createElement(HeaderComponent), header)