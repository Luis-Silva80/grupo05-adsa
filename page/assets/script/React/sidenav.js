var sidenav = document.getElementById("sidenav");


function SidenavComponent() {
    return (
        <React.Fragment>
            <div className="sidenav_containerCategory">
                <a href="./perfilUsuario.html" className="sidenav_containerCategory_link">
                    <img src="./assets/imgs/user.png" className="sidenav_containerCategory_link_icon" />
                </a>
                <a href="./listaLivros.html" className="sidenav_containerCategory_link">
                    <img src="./assets/imgs/book-shelf.png" className="sidenav_containerCategory_link_icon" />
                </a>
                <a href="#" className="sidenav_containerCategory_link">
                    <img src="./assets/imgs/suggestion.png" className="sidenav_containerCategory_link_icon" />
                </a>
                <a href="#" className="sidenav_containerCategory_link">
                    <img src="./assets/imgs/question.png" className="sidenav_containerCategory_link_icon" />
                </a>
            </div>
        
            <div className="sidenav_containerOptions">
                <a href="#" className="sidenav_containerOptions_link">
                    <img src="./assets/imgs/settings.png" className="sidenav_containerOptions_link_icon" />
                </a>
                <a href="#" className="sidenav_containerOptions_link">
                    <img src="./assets/imgs/logout.png" className="sidenav_containerOptions_link_icon" />
                </a>
            </div>
        </React.Fragment>
    );
}

ReactDOM.render(React.createElement(SidenavComponent), sidenav)