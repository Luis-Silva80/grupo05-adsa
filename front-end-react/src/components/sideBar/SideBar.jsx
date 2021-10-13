import React from "react";
import "./style.scss";

import userIcon from "../../assets/user.png";
import bookIcon from "../../assets/book-shelf.png";
import suggestionIcon from "../../assets/suggestion.png";
import questionIcon from "../../assets/question.png";
import settingsIcon from "../../assets/settings.png";
import logoutIcon from "../../assets/logout.png";

function SidenavComponent() {
    return (
        <>
            <aside className="sidenav">
                <div className="sidenav_containerCategory">
                    <a href="./perfilUsuario.html" className="sidenav_containerCategory_link">
                        <img src={userIcon} className="sidenav_containerCategory_link_icon" />
                    </a>
                    <a href="./listaLivros.html" className="sidenav_containerCategory_link">
                        <img src={bookIcon} className="sidenav_containerCategory_link_icon" />
                    </a>
                    <a href="#" className="sidenav_containerCategory_link">
                        <img src={suggestionIcon} className="sidenav_containerCategory_link_icon" />
                    </a>
                    <a href="#" className="sidenav_containerCategory_link">
                        <img src={questionIcon} className="sidenav_containerCategory_link_icon" />
                    </a>
                </div>
            
                <div className="sidenav_containerOptions">
                    <a href="#" className="sidenav_containerOptions_link">
                        <img src={settingsIcon} className="sidenav_containerOptions_link_icon" />
                    </a>
                    <a href="#" className="sidenav_containerOptions_link">
                        <img src={logoutIcon} className="sidenav_containerOptions_link_icon" />
                    </a>
                </div>
            </aside>
        </>
    );
}

export default SidenavComponent;