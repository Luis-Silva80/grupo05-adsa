import React, { useEffect, useState, useCallback } from "react";
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
    const [ isSelect, setIsSelect ] = useState(true);

    const handleClick = useCallback(() => {
        setIsSelect(!isSelect);
    }, [isSelect])

    useEffect(() => {
        if (isSelect) {
            switch (window.location.pathname) {
                case "/perfilUsuario":
                    document.getElementById("perfilUsuario").classList.add('currentPage');
                    break;
                case "/listaLivros":
                    document.getElementById("listaLivros").classList.add('currentPage');
                    break;
                case "/contato":
                    document.getElementById("contato").classList.add('currentPage');
                    break;
                case "/faq":
                    document.getElementById("faq").classList.add('currentPage');
                    break;
                case "/listaUsuarios":
                    document.getElementById("listaUsuarios").classList.add('currentPage');
                    break;
                case "/cadastroLivro":
                    document.getElementById("cadastroLivro").classList.add('currentPage');
                    break;
                default:
                    break;
            }
        }
    }, [isSelect])

    function IconFunc() {
        const side = document.getElementById("rootSideBar");
        side.classList.add('sideOpen');
    }

    function IconFuncOut() {
        const side = document.getElementById("rootSideBar");
        side.classList.remove('sideOpen');
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
            <aside onMouseOver={IconFunc} onMouseOut={IconFuncOut} className="sideBar" id="rootSideBar">
                <div className="sideBar_containerCategory">
                    <Link onClick={handleClick} to="/perfilUsuario" className="sideBar_containerCategory_link" id="perfilUsuario">
                        <img src={userIcon} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Perfil Usuário</p>
                    </Link>
                    <Link onClick={handleClick} to="/listaLivros" className="sideBar_containerCategory_link" id="listaLivros">
                        <img src={bookIcon} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Pesquisar Livros</p>
                    </Link>
                    <Link onClick={handleClick} to="/contato" className="sideBar_containerCategory_link" id="contato">
                        <img src={email} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Contate-nos</p>
                    </Link>
                    <Link onClick={handleClick} to="/faq" className="sideBar_containerCategory_link" id="faq">
                        <img src={questionIcon} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Perguntas frequentes</p>
                    </Link>
                    <Link onClick={handleClick} to="/listaUsuarios" className="sideBar_containerCategory_link" id="listaUsuarios">
                        <img src={listUser} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Lista de Usuários</p>
                    </Link>
                    <Link onClick={handleClick} to="/cadastroLivro" className="sideBar_containerCategory_link" id="cadastroLivro">
                        <img src={addLivro} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Adicionar Livro</p>
                    </Link>
                </div>
            
                <div className="sideBar_containerOptions">
                    <a href="#" className="sideBar_containerOptions_link" onClick={LogOut}>
                        <img src={logoutIcon} className="sideBar_containerOptions_link_icon" />
                        <p className="sideBar_containerOptions_link_desc">Sair</p>
                    </a>
                </div>
            </aside>
        </>
    );
}

export default SideBarComponent;