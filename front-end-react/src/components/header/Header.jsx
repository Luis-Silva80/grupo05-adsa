import "./style.scss";
import logoImg from "../../assets/logoWhiteWhite.png";


function Navbar() {
        return (
            <>
                <header className="header container">
                    <div className="header_imageContainer">
                        <a href="" className="header_imageContainer_link">
                            <img className="header_imageContainer_link_imageLogo" src={logoImg} />
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
                </header>
            </>
        );
}

export default Navbar;