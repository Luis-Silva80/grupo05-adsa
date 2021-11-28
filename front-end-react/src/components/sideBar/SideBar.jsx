/* eslint-disable */
import React, { useEffect, useState, useCallback } from "react";
import "./style.scss";
import { Link } from 'react-router-dom';
import api from "../../services/api";
import { useHistory } from 'react-router-dom';

import userIcon from "../../assets/user.png";
import bookIcon from "../../assets/knowledge.png";
import questionIcon from "../../assets/question.png";
import logoutIcon from "../../assets/logout.png";
import addLivro from "../../assets/addLivro.png";
import listUser from "../../assets/group.png";
import email from "../../assets/email.png";
import ranking from "../../assets/trophy.png";
import hamburgerMenu from "../../assets/hamburgerMenu.png";
import logoImg from "../../assets/logoWhiteWhite.png";


function SideBarComponent() {

    const history = useHistory();
    const [ headerIsSelect, setHeaderIsSelect ] = useState(window.screen.width < 767 ? false : true);
    const [ isAdmin, setIsAdmin ] = useState(false);
    const userId = parseInt(localStorage.getItem('userId'))
    const [ isAluno, setIsAluno ] = useState(false);

    useEffect(async () => {
        await api
            .get(`/aluno/`)
            .then((response) => {
                response.data.map(user => {
                    if (userId == user.id) {
                        setIsAluno(true);
                    }
                })
                if (!isAluno) {
                    setIsAdmin(true);
                }
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);
    
    useEffect(() => {
        switch (window.location.pathname) {
            case "/perfilUsuario":
                document.getElementById("perfilUsuario")?.classList.add('currentPage');
                break;
            case "/listaLivros":
                document.getElementById("listaLivros")?.classList.add('currentPage');
                break;
            case "/contato":
                document.getElementById("contato")?.classList.add('currentPage');
                break;
            case "/faq":
                document.getElementById("faq")?.classList.add('currentPage');
                break;
            case "/listaUsuarios":
                document.getElementById("listaUsuarios")?.classList.add('currentPage');
                break;
            case "/cadastroLivro":
                document.getElementById("cadastroLivro")?.classList.add('currentPage');
                break;
            default:
                break;
        }
    })
    
    const handleClickHeader = useCallback(() => {
        setHeaderIsSelect(!headerIsSelect);
    }, [headerIsSelect])

    useEffect(() => {
        if (headerIsSelect) {
            document.getElementById("rootSideBar").style.left = "0";
            if (window.screen.width < 767) {
                IconFunc();
            }
        } else {
            document.getElementById("rootSideBar").style.left = "-300px";
        }
    }, [headerIsSelect])

    function IconFunc() {
        const side = document.getElementById("rootSideBar");
        side.classList.add('sideOpen');
    }

    function IconFuncOut() {
        if (window.screen.width > 767) {
            const side = document.getElementById("rootSideBar");
            side.classList.remove('sideOpen');
        }
    }

    function LogOut() {
        api
        .delete(`/autenticacao/` + localStorage.getItem('userId'))
        .then((response) => {
            console.log(response.data);
                if(response.status === 204) {
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
            <header className="headerMobile" id="headerMobileSide" >
                <img onClick={handleClickHeader} className="headerMobile_hamburger" src={hamburgerMenu} />
                <img className="headerMobile_logo" src={logoImg} />
            </header>
            <aside onMouseOver={IconFunc} onMouseOut={IconFuncOut} className="sideBar" id="rootSideBar">
                <div className="sideBar_containerCategory">
                    <Link to="/perfilUsuario" className="sideBar_containerCategory_link" id="perfilUsuario">
                        <img src={userIcon} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Perfil Usuário</p>
                    </Link>
                    <Link to="/listaLivros" className="sideBar_containerCategory_link" id="listaLivros">
                        <img src={bookIcon} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Pesquisar Livros</p>
                    </Link>
                    <Link to="/contato" className="sideBar_containerCategory_link" id="contato">
                        <img src={email} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Contate-nos</p>
                    </Link>
                    <Link to="/faq" className="sideBar_containerCategory_link" id="faq">
                        <img src={questionIcon} className="sideBar_containerCategory_link_icon" />
                        <p className="sideBar_containerCategory_link_desc">Perguntas frequentes</p>
                    </Link>
                    {
                        isAdmin
                        ? 
                        <Link to="/listaUsuarios" className="sideBar_containerCategory_link" id="listaUsuarios">
                            <img src={listUser} className="sideBar_containerCategory_link_icon" />
                            <p className="sideBar_containerCategory_link_desc">Lista de Usuários</p>
                        </Link>
                        :
                        ""
                    }
                    {
                        isAdmin
                        ? 
                        <Link to="/cadastroLivro" className="sideBar_containerCategory_link" id="cadastroLivro">
                            <img src={addLivro} className="sideBar_containerCategory_link_icon" />
                            <p className="sideBar_containerCategory_link_desc">Adicionar Livro</p>
                        </Link>
                        :
                        ""
                    }
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