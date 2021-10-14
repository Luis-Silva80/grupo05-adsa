import React, { useEffect } from 'react';
import './style.scss';
import icon from '../../assets/perfilIcon.png'
import closed from '../../assets/close.png'

// import api and links
import api from "../../services/api";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';

function ListaUsuarios() {

    useEffect(() => {
        api
            .get("/aluno")
            .then((response) => { console.log(response.data); })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);

    return (
        <>
            <SideBar />
            <section id="rootListaUsuario">
                <main class="main container">
                    <h1 class="main_title">Usuários cadastrados</h1>

                    <div class="main_nav">
                        <input class="main_nav_input" placeholder="Digite o nome do usuário" type="text" name="" id="" />
                        <button class="main_nav_btn">Pesquisar</button>
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

                    </table>
                    <section id="popup" class="main_popup">
                        <img class="main_popup_img" src={icon} />
                        <img class="main_popup_close" src={closed} onclick="callUser()" />
                        <div class="main_popup_user" id="user"></div>
                    </section>
                </main>
            </section>
            <Footer />
        </>
    );
}

export default ListaUsuarios;