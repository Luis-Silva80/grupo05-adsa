import React, { useEffect } from 'react';
import "./style.scss";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';

function ListaLivros() {
    return (
        <>
            <SideBar />
            <main class="main container">
                <h1 class="main_title">Livros cadastrados</h1>

                <div className="main_nav">
                    <input className="main_nav_input" placeholder="Digite o nome do livro" type="text" name="name" id="name" />
                    <button className="main_nav_btn" onclick="search()">Pesquisar</button>
                    <select className="main_nav_filter">
                        <option className="main_nav_filter_value" value="#">Filtrar por: </option>
                        <option className="main_nav_filter_value" value="pendencia">Categoria</option>
                        <option className="main_nav_filter_value" value="nomeDesc">Nome Desc</option>
                        <option className="main_nav_filter_value" value="nomeAsc">Nome Asc</option>
                    </select>
                </div>
                <h3 id="searchCallback" className="main_callback"></h3>
                <div id="resp" className="resp"></div>

            </main>
            <Footer />
        </>
    );
}

export default ListaLivros;