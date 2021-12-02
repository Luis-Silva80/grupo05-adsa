// desativa o carai do eslint
/* eslint-disable */


import React, { useEffect, useState } from 'react';
import api from "../../services/api";
import Autentication from "../../services/autentication";
import Gamification from "../../services/gamification";
import { useHistory } from 'react-router-dom';



//Importanto componentes e o css
import './style.scss';
import SideBar from '../../components/sideBar/SideBar';
import Footer from '../../components/footer/Footer';
import BookCard from '../../components/bookCard/BookCard';
import Loading from '../../components/loading/Loading';
import ButtonLink from '../../components/button/Button';
import Resp from '../../components/resp/Resp';

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
    const [respInfo, setRespInfo] = useState([]);
    const [registroInfo, setRegistroInfo] = useState();
    const [userName, setUserName] = useState();
    const [historicoLivro, setHistoricoLivro] = useState();

    useEffect(() => {
        api
            .get(`/aluno/${userId}`)
            .then((response) => {
                setUserInfo(response.data);
                setUserName(response.data.nome);
                Gamification(response.data.pontos);
                console.log("User data:", response.data);
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);

    function WithdrawBook() {
        let reservaId = localStorage.getItem("idReserva")
        const resp = document.getElementById('respReserv');
        
        api
        .put(`/biblioteca/retirar/${Number(reservaId)}/${userInfo.id}`)
        .then((response) => {

            if (response.status === 200) {
                localStorage.setItem("idReserva", response.data);
                setRespInfo({ titulo: "Sucesso", parag: "Retirada feita com sucesso" })
                resp.classList.add("active");
                resp.classList.remove("error");
                resp.classList.add("success");
            } else {
                setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
                resp.classList.add("active");
                resp.classList.remove("success");
                resp.classList.add("error");
            }
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
            setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
            resp.classList.add("active");
            resp.classList.remove("success");
            resp.classList.add("error");
        });
    }

    function RenewBook() {
        let reservaId = localStorage.getItem("idReserva")
        const resp = document.getElementById('respReserv');
        
        api
        .put(`/biblioteca/renovar/${Number(reservaId)}/${userInfo.id}`)
        .then((response) => {

            if (response.status === 200) {
                localStorage.setItem("idReserva", response.data);
                setRespInfo({ titulo: "Sucesso", parag: "Renovação feita com sucesso, verifique a data no seu perfil" })
                resp.classList.add("active");
                resp.classList.remove("error");
                resp.classList.add("success");
            } else {
                setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
                resp.classList.add("active");
                resp.classList.remove("success");
                resp.classList.add("error");
            }
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
            setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
            resp.classList.add("active");
            resp.classList.remove("success");
            resp.classList.add("error");
        });
    }

    function ReturnBook() {
        const resp = document.getElementById('respReserv');
        let idReserva = localStorage.getItem("idReserva");
        
        api
        .put(`/biblioteca/devolver/${Number(idReserva)}/${userInfo.id}`)
        .then((response) => {

            if (response.status === 200) {
                setRespInfo({ titulo: "Sucesso", parag: "Devolução feita com sucesso" })
                resp.classList.add("active");
                resp.classList.remove("error");
                resp.classList.add("success");
            } else {
                setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
                resp.classList.add("active");
                resp.classList.remove("success");
                resp.classList.add("error");
            }
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
            setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
            resp.classList.add("active");
            resp.classList.remove("success");
            resp.classList.add("error");
        });

    }
    
    function CallPopup(id) {
        let popup = document.getElementById("popup")
        popup.classList.add("active")
        api
        .get(`/biblioteca/${id}`)
        .then((response) => {
            setBookInfo(response.data);
            console.log(response.data, "bookInfo aqui");
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });

        
        api
        .get(`/historico`)
        .then((response) => {
            response.data.map(registro => {
                if (registro.fkTbLivros == bookInfo.id) {
                    if (registro.acao == "Retirada" || registro.acao == "Renovacao") {
                        if (userInfo.id == registro.fkTbPerfilUsuario) {
                            setRegistroInfo(registro);
                            console.log("registro aquii", registro);
                        }
                    }
                }
            })
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        })
    }

    function GetHistoricoDoLivro() {
        api
        .get(`/historico`)
        .then((response) => {
            let count = 0;
            let infoHistoricos = []
            response.data.map(registro => {
                count++;
                console.log("count aqui", count);
                if (registro.fkTbLivros == bookInfo.id) {
                    if (userInfo.id == registro.fkTbPerfilUsuario) {
                        console.log("response.data.length aqui", response.data.length);
                        // if (count == response.data.length) {
                            infoHistoricos.push(registro)
                            console.log("infoHistoricos aqui",infoHistoricos);
                            setHistoricoLivro(infoHistoricos[0]);
                            console.log("registro histórico aquii", infoHistoricos[0]);
                        // }
                    }
                }
            })
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        })
    }

    function ClosePopup() {
        let popup = document.getElementById("popup")
        popup.classList.remove("active")
    }

    function editName() {
        let nameElement = document.getElementById("perfilName").value;
        api
        .put(`/aluno/alterar-nome/${userId}/${nameElement}`)
        .then(response => {
            console.log("response do put aqui", response);
        })
        .catch(err => {
            console.error("ops! ocorreu um erro" + err);
        })
        console.log("nameElement", nameElement);
    }


    return (
        <div id="rootPerfilUsuario">
            <SideBar />

            {userInfo.length === 0 ?
                <Loading /> :
                <main className="main" >
                    <div className="main_perfilInfo">
                        <div className="main_perfilInfo_box">
                            <img src={`https://thothlibs.azurewebsites.net/aluno/foto/${userId}`} className="main_perfilInfo_box_icon" />
                            <div className="main_perfilInfo_box_content">
                                <div>
                                    <input id="perfilName" value={userName} onChange={e => setUserName(e.target.value)} className="main_perfilInfo_box_content_name"/><img src={pencil} onClick={() => editName()} className="main_perfilInfo_box_content_name_icon" />
                                </div>
                                <p className="main_perfilInfo_box_content_email">{userInfo.email}</p>
                                <div className="main_perfilInfo_box_content_box">
                                    <p className="main_perfilInfo_box_content_box_ra">RA: 01202084</p>
                                    <p className="main_perfilInfo_box_content_box_number">TEL: {userInfo.telefone}</p>
                                </div>
                            </div>
                        </div>

                        <div className="main_perfilInfo_perfilGame">
                            <p className="main_perfilInfo_perfilGame_level">Usuário Nível<span className="main_perfilInfo_perfilGame_level_number" id="perfilLevel">10</span></p>

                            <div className="main_perfilInfo_perfilGame_progressBarGrey"><div className="main_perfilInfo_perfilGame_progressBarBlue" id="barLevel"></div></div>

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
                                    <div key={item.id} className="book">
                                        <Link to="./livro" className="book_link">
                                            <img src={`https://thothlibs.azurewebsites.net/aluno/foto/${item.id}`} className="book_link_img" alt="book preview" />
                                        </Link>
                                        <div className="book_infos_info">
                                            <span id="bookId" className="book_infos_info_id">{item.id}</span>
                                            <h4 className="book_infos_info_title">{item.titulo}</h4>
                                            {/* chamar a função do modal, e o endpoint de livro por id, passando item.id como param */}
                                            <button onClick={() => (GetHistoricoDoLivro(), CallPopup(item.id))} className="book_infos_info_btn">Ver Mais</button>
                                        </div>
                                    </div>
                                ))
                            }
                        </ul>
                    </div>
                    <section id="popup" className="popup" >
                        <img className="popup_img" src={perfilIcon} alt="user" />
                        <img className="popup_close"  onClick={() => ClosePopup()}  src={closeButton} alt="close popup" />
                        <div className="popup_user">
                            <h2 className="popup_user_info name">{bookInfo.titulo}</h2>
                            <h4 className="popup_user_info email">Status: 
                                {
                                    historicoLivro?.acao.toLowerCase() == "reserva" ?
                                    <b>Reservado</b> : historicoLivro?.acao.toLowerCase() == "retirada" ?
                                    <b>Retirado</b> : historicoLivro?.acao.toLowerCase() == "renovacao" ?
                                    <b>Renovado</b> : historicoLivro?.acao.toLowerCase() == "devolucao" ?
                                    <b>Devolvido</b> : "Nada"
                                }
                            </h4>
                            <h4 className="popup_user_info return">Devolver até: <b>{registroInfo?.dataDevolucao}</b></h4>
                            <h4 className="popup_user_info reserved">Reservado em: <b>{registroInfo?.dataLivroHistorico}</b></h4>
                            <div className="popup_user_box">
                                <button className="popup_user_box_btn" onClick={() => WithdrawBook()}>Retirar</button>
                                <button className="popup_user_box_btn" onClick={() => RenewBook()}>Prorrogar</button>
                                <button className="popup_user_box_btn" onClick={() => ReturnBook()}>Devolver</button>
                            </div>
                        </div>
                    </section>
                    <Resp titulo={respInfo.titulo} parag={respInfo.parag} btn={respInfo.btn} link={respInfo.link} /> 
                </main>

            }

            <Footer />
        </div>
    );
}

export default PerfilUsuario;
