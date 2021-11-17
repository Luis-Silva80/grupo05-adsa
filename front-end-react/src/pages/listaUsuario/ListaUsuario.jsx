/* eslint-disable */
import React, { useEffect, useState } from 'react';
import './style.scss';
import icon from '../../assets/perfilIcon.png'
import closed from '../../assets/close.png'
import trash from '../../assets/trash.png'
import loupe from '../../assets/loupe.png'
import { Link } from 'react-router-dom';


// import api and links
import api from "../../services/api";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import Autentication from "../../services/autentication";
import AutenticationAdmin from "../../services/autenticationAdmin";

import usuarioImg from '../../assets/perfilIcon.png';
import Loading from '../../components/loading/Loading';
import PopupUser from '../../components/popupUser/PopupUser';
import closeButton from "../../assets/close.png";
import perfilIcon from '../../assets/perfilIcon.png';

function ListaUsuarios() {

    Autentication();
    AutenticationAdmin();

    //const userData = [];

    const [users, setUsers] = useState([]);
    const [userInfo, setUserInfo] = useState([]);

    useEffect(() => {
        api
            .get("/aluno")
            .then((response) => {
                setUsers(response.data);
                console.log(response.data);
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);

    function CallPopup(id) {
        console.log("id retornado aqui: ", id);
        api
            .get(`/aluno/${id}`)
            .then((response) => {
                setUserInfo(response.data);
                console.log("Usuário retornado:", response.data);
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });


    }
    // function findUser() {
    //     userData.forEach(element => {
    //         console.log( "Usuário aqui: " , element );
    //     });
    // }


    return (

        <div id="rootListaUsuario">
            <SideBar />
            {users.length === 0 ?
                <Loading /> :
                <main class="main">
                    <h1 class="main_title">Usuários cadastrados</h1>
                    <div class="main_nav">
                        <input class="main_nav_input" placeholder="Digite o nome do usuário" type="text" name="" id="" />
                        <button class="main_nav_btn" >Pesquisar</button>
                        {/* <button class="main_nav_btn" onClick={findUser}>Pesquisar</button> */}
                        <select class="main_nav_filter">
                            <option class="main_nav_filter_value" value="#">Filtrar por: </option>
                            <option class="main_nav_filter_value" value="pendencia">Pendência</option>
                            <option class="main_nav_filter_value" value="nomeDesc">Nome Desc</option>
                            <option class="main_nav_filter_value" value="nomeAsc">Nome Asc</option>
                        </select>
                    </div>

                    <table class="main_table" id="table">
                        <tr class="main_table_label">
                            <th></th>
                            <th class="main_table_label_element">Usuário</th>
                            <th class="main_table_label_element">Email</th>
                            <th class="main_table_label_element">status</th>
                            <th class="main_table_label_element">Pendência</th>
                            <th></th>
                            <th></th>
                        </tr>
                        {users.map(item => (

                            <tr class="main_table_user">

                                <td class="main_table_user_item frst"><img class="main_table_user_img" src={usuarioImg} alt="user img" /></td>
                                <td class="main_table_user_item name">{item.nome}</td>
                                <td class="main_table_user_item email">{item.email}</td>
                                {item.status === "Inativo"
                                    ? (<td class='main_table_user_item inactive'>{item.status}</td>)
                                    : (<td class='main_table_user_item'>{item.status}</td>)
                                }
                                {item.pendencia == null
                                    ? (<td class='main_table_user_item'>nenhuma</td>)
                                    : (<td class='main_table_user_item inactive'>{item.pendencia}</td>)
                                }
                                {/* <td class="main_table_user_item"  onClick={() => storeId(item.id)} > */}
                                <td class="main_table_user_item" >
                                    <button value={item.id} onClick={() => CallPopup(item.id)} ><img class="main_table_user_about" src={loupe} /></button>
                                </td>
                                <td class="main_table_user_item lst"><img class="main_table_user_trash" src={trash} /></td>
                            </tr>
                        ))}

                    </table>
                    <section id="popup" class="popup">
                        <img class="popup_img" src={perfilIcon} alt="user" />
                        <img class="popup_close" src={closeButton} alt="close popup" />
                        {/* <img class="popup_close" src={closeButton} onClick={CallPopup} alt="close popup"/> */}
                        <div class="popup_user">
                            <h2 class="popup_user_info name">{userInfo.nome}</h2>
                            <h4 class="popup_user_info email">Email: <b>{userInfo.email}</b></h4>
                            <h4 class="popup_user_info status">Status: <b>Ativo</b></h4>
                            <h4 class="popup_user_info bookName">Livro reservado: <b>css for babies</b></h4>
                            <h4 class="popup_user_info reserved">Reservado em: <b>10/02/2021</b></h4>
                            <h4 class="popup_user_info return">Devolver em: <b>20/02/2021</b></h4>
                            <div class="popup_user_box">
                                <button class="popup_user_box_btn">Enviar email</button>
                                <button class="popup_user_box_btn">Prorrogar</button>
                            </div>
                        </div>
                    </section>
                </main>
            }
            <Footer />
        </div>
    );
    function storeId(value) {
        localStorage.setItem('userId', value)
    }
}

export default ListaUsuarios;