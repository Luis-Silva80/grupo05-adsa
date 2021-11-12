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


function ListaUsuarios() {

    Autentication();
    AutenticationAdmin();

    //const userData = [];

    const [userData, setUserData] = useState([]);

    useEffect(() => {
        api
            .get("/aluno")
            .then((response) => {
                setUserData(response.data);
                console.log(response.data);
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);


    // function findUser() {
    //     userData.forEach(element => {
    //         console.log( "Usuário aqui: " , element );
    //     });
    // }


    return (

        <div id="rootListaUsuario">
            <SideBar />
            {userData.length === 0 ?
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
                        {userData.map(item => (

                            <tr class="main_table_user">

                                <td class="main_table_user_item frst"><img class="main_table_user_img" src={usuarioImg} alt="user img" /></td>
                                <td class="main_table_user_item name">{item.nome}</td>
                                <td class="main_table_user_item email">{item.email}</td>
                                {item.status == "Inativo"
                                    ? (<td class='main_table_user_item inactive'>{item.status}</td>)
                                    : (<td class='main_table_user_item'>{item.status}</td>)}
                                {item.pendencia == null
                                    ? (<td class='main_table_user_item'>nenhuma</td>)
                                    : (<td class='main_table_user_item inactive'>{item.pendencia}</td>)}
                                <td class="main_table_user_item" value={item.id} onClick={() => storeId(item.id)} >

                                    <Link to="/perfilUsuario"><img class="main_table_user_about" src={loupe} /></Link>
                                </td>


                                <td class="main_table_user_item lst"><img class="main_table_user_trash" src={trash} /></td>


                            </tr>
                        ))}

                    </table>
                    <section id="popup" class="main_popup">
                        <img class="main_popup_img" src={icon} />
                        <img class="main_popup_close" src={closed} onClick="callUser()" />
                        <div class="main_popup_user" id="user"></div>
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