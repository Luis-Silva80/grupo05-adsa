import React from "react";
import "./style.scss";

import userIcon from "../../assets/user.png";
import bookIcon from "../../assets/book-shelf.png";
import suggestionIcon from "../../assets/suggestion.png";
import questionIcon from "../../assets/question.png";
import settingsIcon from "../../assets/settings.png";
import logoutIcon from "../../assets/logout.png";

function sideBarComponent() {
    return (
        <>
            <aside className="sideBar" id="rootSideBar">
                <div className="sideBar_containerCategory">
                    <a href="./perfilUsuario.html" className="sideBar_containerCategory_link">
                        <img src={userIcon} className="sideBar_containerCategory_link_icon" />
                    </a>
                    <a href="./listaLivros.html" className="sideBar_containerCategory_link">
                        <img src={bookIcon} className="sideBar_containerCategory_link_icon" />
                    </a>
                    <a href="#" className="sideBar_containerCategory_link">
                        <img src={suggestionIcon} className="sideBar_containerCategory_link_icon" />
                    </a>
                    <a href="#" className="sideBar_containerCategory_link">
                        <img src={questionIcon} className="sideBar_containerCategory_link_icon" />
                    </a>
                </div>
            
                <div className="sideBar_containerOptions">
                    <a href="#" className="sideBar_containerOptions_link">
                        <img src={settingsIcon} className="sideBar_containerOptions_link_icon" />
                    </a>
                    <a href="#" className="sideBar_containerOptions_link">
                        <img src={logoutIcon} className="sideBar_containerOptions_link_icon" />
                    </a>
                </div>
            </aside>
        </>
    );
}

export default sideBarComponent;