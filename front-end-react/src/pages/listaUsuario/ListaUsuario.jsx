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
    const [render, setRender] = useState([users]);

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
        let popup = document.getElementById("popup")
        popup.classList.add("active")
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

    function ClosePopup() {
        let popup = document.getElementById("popup")
        popup.classList.remove("active")
    }

    function Pendentes() {
        api
            .get("historico/pendentes")
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.error(error)
            })
    }

    // function findUser() {
    //     userData.forEach(element => {
    //         console.log( "Usuário aqui: " , element );
    //     });
    // }

    function filtro() {
        console.log(filter_combo.value);
        switch (filter_combo.value) {
            case "nomeAsc":
                var userName = [];
                var userOrdenado = [];
                users.map(user => {
                    userName.push(user.nome)
                });
                userName.sort();
                userName.map(name => {
                    users.map(user => {
                        if (user.nome == name) {
                            userOrdenado.push(user)
                        }
                    })
                })
                setRender(userOrdenado)
                console.log(userOrdenado, "userOrdenado aqui");
                break;
            case "nomeDesc":
                setRender(users)
                console.log(render, "render aqui");
                break;
            default:
                break;
        }
    }


    return (

        <div id="rootListaUsuario">
            <SideBar />
            {users.length === 0 ?
                <Loading /> :
                <main className="main">
                    <h1 className="main_title">Usuários cadastrados</h1>
                    <div className="main_nav">
                        <input className="main_nav_input" placeholder="Digite o nome do usuário" type="text" name="" id="" />
                        <button className="main_nav_btn" >Pesquisar</button>
                        {/* <button className="main_nav_btn" onClick={findUser}>Pesquisar</button> */}
                        <select id="filter_combo" className="main_nav_filter">
                            <option className="main_nav_filter_value" value="#">Filtrar por: </option>
                            <option className="main_nav_filter_value" value="pendencia">Pendência</option>
                            <option className="main_nav_filter_value" value="nomeDesc">Nome Desc</option>
                            <option className="main_nav_filter_value" value="nomeAsc">Nome Asc</option>
                        </select>
                    </div>

                    <div className="main_excel">
                        <button onClick={() => Pendentes()} className="main_excel_button">Extrair excel</button>
                        <button onClick={() => filtro()} className="main_excel_button">Aplicar filtros</button>
                    </div>

                    <table className="main_table" id="table">
                        <tr className="main_table_label">
                            <th></th>
                            <th className="main_table_label_element">Usuário</th>
                            <th className="main_table_label_element">Email</th>
                            <th className="main_table_label_element">status</th>
                            <th className="main_table_label_element">Pendência</th>
                            <th></th>
                            <th></th>
                        </tr>
                        {render.map(item => (

                            <tr className="main_table_user">

                                <td className="main_table_user_item frst"><img className="main_table_user_img" src={usuarioImg} alt="user img" /></td>
                                <td className="main_table_user_item name">{item.nome}</td>
                                <td className="main_table_user_item email">{item.email}</td>
                                {item.status === "Inativo"
                                    ? (<td className='main_table_user_item inactive'>{item.status}</td>)
                                    : (<td className='main_table_user_item'>{item.status}</td>)
                                }
                                {item.pendencia == null
                                    ? (<td className='main_table_user_item'>nenhuma</td>)
                                    : (<td className='main_table_user_item inactive'>{item.pendencia}</td>)
                                }
                                {/* <td className="main_table_user_item"  onClick={() => storeId(item.id)} > */}
                                <td className="main_table_user_item" >
                                    <button value={item.id} onClick={() => CallPopup(item.id)} ><img className="main_table_user_about" src={loupe} /></button>
                                </td>
                                <td className="main_table_user_item lst"><img className="main_table_user_trash" src={trash} /></td>
                            </tr>
                        ))}

                    </table>
                    <section id="popup" className="popup">
                        <img className="popup_img" src={perfilIcon} alt="user" />
                        <img className="popup_close" onClick={() => ClosePopup()} src={closeButton} alt="close popup" />
                        <div className="popup_user">
                            <h2 className="popup_user_info name">{userInfo.nome}</h2>
                            <h4 className="popup_user_info email">Email: <b>{userInfo.email}</b></h4>
                            <h4 className="popup_user_info status">Status: <b>Ativo</b></h4>
                            <h4 className="popup_user_info bookName">Livro reservado: <b>css for babies</b></h4>
                            <h4 className="popup_user_info reserved">Reservado em: <b>10/02/2021</b></h4>
                            <h4 className="popup_user_info return">Devolver em: <b>20/02/2021</b></h4>
                            <div className="popup_user_box">
                                <button className="popup_user_box_btn">Enviar email</button>
                                <button className="popup_user_box_btn">Prorrogar</button>
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