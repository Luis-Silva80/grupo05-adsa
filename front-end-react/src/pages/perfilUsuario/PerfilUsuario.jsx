// desativa o carai do eslint
/* eslint-disable */


import React, { useEffect, useState } from 'react';
import api from "../../services/api";
import Autentication from "../../services/autentication";
import { useHistory } from 'react-router-dom';


//Importanto componentes e o css
import './style.scss';
import SideBar from '../../components/sideBar/SideBar';
import Footer from '../../components/footer/Footer';
import BookCard from '../../components/bookCard/BookCard';
import Loading from '../../components/loading/Loading';

//IMAGENS

import perfilIcon from '../../assets/perfilIcon.png';
import pencil from '../../assets/pencil.png';
import conversation from '../../assets/conversation.png';
import openBookPerfil from '../../assets/open-book-perfil.png';
import imageLivro from '../../assets/book.png';
import closeButton from "../../assets/close.png";
import { Link } from 'react-router-dom';

function PerfilUsuario() {

    Autentication();

    const userId = parseInt(localStorage.getItem('userId'))
    const [userInfo, setUserInfo] = useState([]);
    const [bookInfo, setBookInfo] = useState([]);

    useEffect(async () => {

        await api
            .get(`/aluno/${userId}`)
            .then((response) => {
                setUserInfo(response.data);
                console.log("User data:", response.data);
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);

    function CallPopup(id) {
        console.log("id retornado aqui: ", id);
        let popup = document.getElementById("popup")
        popup.classList.add("active")
        api
        .get(`/biblioteca/${id}`)
        .then((response) => {
            setBookInfo(response.data);
            console.log("livro retornado:", response.data);
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });


    }
    function ClosePopup() {
        let popup = document.getElementById("popup")
        popup.classList.remove("active")
    }

    function ReturnBook(bookId, userId) {
        console.log("id do livro", bookId);
        console.log("id do usuário", userId);

        api
        .put(`/biblioteca/devolver/${bookId}/${userId}`)
        .then((response) => {
            // setBookInfo(response.data);
            console.log("devolvi aqui: ", response.data);
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });

    }

    
    function storeId(value) {
        localStorage.setItem('bookId', parseInt(value))
    }


    return (
        <div id="rootPerfilUsuario">
            <SideBar />

            {userInfo.length === 0 ?
                <Loading /> :
                <main className="main" >
                    <div className="main_perfilInfo">
                        <div className="main_perfilInfo_box">
                            <img src={perfilIcon} className="main_perfilInfo_box_icon" />
                            <div className="main_perfilInfo_box_content">
                                <h3 className="main_perfilInfo_box_content_name">{userInfo.nome}<img src={pencil} className="main_perfilInfo_box_content_name_icon" /></h3>
                                <p className="main_perfilInfo_box_content_email">{userInfo.email}</p>
                                <div className="main_perfilInfo_box_content_box">
                                    <p className="main_perfilInfo_box_content_box_ra">RA: 01202084</p>
                                    <p className="main_perfilInfo_box_content_box_number">TEL: {userInfo.telefone}</p>
                                </div>
                            </div>
                        </div>

                        <div className="main_perfilInfo_perfilGame">
                            <p className="main_perfilInfo_perfilGame_level">Usuário Nível<span className="main_perfilInfo_perfilGame_level_number" id="perfilLevel">10</span></p>

                            <div className="main_perfilInfo_perfilGame_progressBarGrey"><div className="main_perfilInfo_perfilGame_progressBarBlue"></div></div>

                            <div className="main_perfilInfo_perfilGame_status">
                                <div className="main_perfilInfo_perfilGame_status_box">
                                    <img src={openBookPerfil} className="main_perfilInfo_perfilGame_status_box_image" />
                                    <p className="main_perfilInfo_perfilGame_status_box_content"><span className="main_perfilInfo_perfilGame_status_box_content_number" id="numberBooksReaded">{userInfo.qtdLivrosLidos}</span> livros lidos</p>
                                </div>
                                <div className="main_perfilInfo_perfilGame_status_box">
                                    <img src={conversation} className="main_perfilInfo_perfilGame_status_box_image" />
                                    <p className="main_perfilInfo_perfilGame_status_box_content"><span className="main_perfilInfo_perfilGame_status_box_content_number" id="numberBooksReview">{userInfo.qtdResenhas}</span> resenhas</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="main_listaLivros">
                        <div className="main_listaLivros_title">
                            <select className="main_listaLivros_title_filter">
                                <option className="main_listaLivros_title_filter_value" value="#">Filtrar por:</option>
                                <option className="main_listaLivros_title_filter_value" value="pendencia">Categoria</option>
                                <option className="main_listaLivros_title_filter_value" value="nomeDesc">Nome Desc</option>
                                <option className="main_listaLivros_title_filter_value" value="nomeAsc">Nome Asc</option>
                            </select>

                            <h2 className="main_listaLivros_title_text">Livros reservados</h2>
                        </div>

                        <ul className="main_listaLivros_lista">
                            {
                                userInfo.livrosLidos.length === 0 ?
                                <div className="empty">
                                    <h3 className="empty_title">Você ainda não reservou nenhum livro!</h3>
                                    <Link className="empty_btn" to="/listaLivros">Reservar Agora</Link>
                                </div>
                                :
                                userInfo.livrosLidos.map(item => (
                                    <div class="book">
                                        <Link to="./livro" class="book_link">
                                            <img src={imageLivro} class="book_link_img" alt="book preview" />
                                        </Link>
                                        <div class="book_infos_info">
                                            <span id="bookId" className="book_infos_info_id">{item.id}</span>
                                            <h4 class="book_infos_info_title">{item.titulo}</h4>
                                            {/* chamar a função do modal, e o endpoint de livro por id, passando item.id como param */}
                                            <button onClick={() => CallPopup(item.id)} class="book_infos_info_btn">Ver Mais</button>
                                        </div>
                                    </div>
                                ))
                            }
                        </ul>
                    </div>
                    <section id="popup" class="popup" >
                        <img class="popup_img" src={perfilIcon} alt="user" />
                        <img class="popup_close"  onClick={() => ClosePopup()}  src={closeButton} alt="close popup" />
                        <div class="popup_user">
                            <h2 class="popup_user_info name">{bookInfo.titulo}</h2>
                            <h4 class="popup_user_info email">Status: <b>{bookInfo.statusLivro}</b></h4>
                            <h4 class="popup_user_info return">Devolver até: <b>20/02/2021</b></h4>
                            <h4 class="popup_user_info reserved">Reservado em: <b>10/02/2021</b></h4>
                            <div class="popup_user_box">
                                <button class="popup_user_box_btn" onClick={() => ReturnBook(bookInfo.id, userInfo.id)}>Devolver</button>
                                <button class="popup_user_box_btn">Prorrogar</button>
                            </div>
                        </div>
                    </section>
                </main>

            }

            <Footer />
        </div>
    );
}

export default PerfilUsuario;
