import React, { useEffect } from "react";
import "./style.scss";
import { Link } from 'react-router-dom';
import api from "../../services/api";
import { useHistory } from 'react-router-dom';

import userIcon from "../../assets/user.png";
import bookIcon from "../../assets/book-shelf.png";
import questionIcon from "../../assets/question.png";
import logoutIcon from "../../assets/logout.png";
import addLivro from "../../assets/addLivro.png";
import listUser from "../../assets/group.png";
import email from "../../assets/email.png";


function SideBarComponent() {

    const history = useHistory();

    function userIconFunc() {
        const icon = document.querySelector(".sideBar_containerCategory_link_icon");
        console.log(icon);
        icon.style.className="sideBar_containerCategory_link_icon active"
    }

    function LogOut() {
        api
        .delete(`/autenticacao/` + localStorage.getItem('userId'))
        .then((response) => {
            console.log(response.data);
                if(response.status === 200) {
                    localStorage.setItem('userId', '')
                    history.push("/");
                }    
            })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });
    }

    return (
        <>
            <aside className="sideBar" id="rootSideBar">
                <div className="sideBar_containerCategory">
                    <Link onClick={userIconFunc} to="/perfilUsuario" className="sideBar_containerCategory_link">
                        <img src={userIcon} className="sideBar_containerCategory_link_icon" />
                    </Link>
                    <Link to="/listaLivros" className="sideBar_containerCategory_link">
                        <img src={bookIcon} className="sideBar_containerCategory_link_icon" />
                    </Link>
                    <Link to="/contato" className="sideBar_containerCategory_link">
                        <img src={email} className="sideBar_containerCategory_link_icon" />
                    </Link>
                    <Link to="/faq" className="sideBar_containerCategory_link">
                        <img src={questionIcon} className="sideBar_containerCategory_link_icon" />
                    </Link>
                    <Link to="/listaUsuarios" className="sideBar_containerCategory_link">
                        <img src={listUser} className="sideBar_containerCategory_link_icon" />
                    </Link>
                    <Link to="/cadastroLivro" className="sideBar_containerCategory_link">
                        <img src={addLivro} className="sideBar_containerCategory_link_icon" />
                    </Link>
                </div>
            
                <div className="sideBar_containerOptions">
                    <a href="#" className="sideBar_containerOptions_link" onClick={LogOut}>
                        <img src={logoutIcon} className="sideBar_containerOptions_link_icon" />
                    </a>
                </div>
            </aside>
        </>
    );
}

export default SideBarComponent;