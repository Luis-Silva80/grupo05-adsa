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
import ButtonLink from '../../components/button/Button';

//IMAGENS

import perfilIcon from '../../assets/perfilIcon.png';
import pencil from '../../assets/pencil.png';
import conversation from '../../assets/conversation.png';
import openBookPerfil from '../../assets/open-book-perfil.png';
import imageLivro from '../../assets/book.png';
import { Link } from 'react-router-dom';

function PerfilUsuario() {

    Autentication();

    const userId = parseInt(localStorage.getItem('userId'))
    const [userInfo, setUserInfo] = useState([]);

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
                            <ButtonLink path="/rankingUsuarios" content="Ver Ranking" />
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
                                    console.log("item aqui", item),
                                    <div class="book">
                                        <Link to="./livro" class="book_link">
                                            <img src={imageLivro} class="book_link_img" alt="book preview" />
                                        </Link>
                                        <div class="book_info">
                                            <span id="bookId" className="book_info_id">{item.id}</span>
                                            <h4 class="book_info_title">{item.titulo}</h4>
                                            {/* chamar a função do modal, e o endpoint de livro por id, passando item.id como param */}
                                            <button onClick={() => CallPopup(item.id)} class="book_info_btn">Ver Mais</button>
                                        </div>
                                    </div>
                                ))
                            }
                        </ul>
                    </div>
                </main>

            }

            <Footer />
        </div>
    );
}

export default PerfilUsuario;
