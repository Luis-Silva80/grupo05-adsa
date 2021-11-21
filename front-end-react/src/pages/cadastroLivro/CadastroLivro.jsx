import React, { useEffect, useState } from 'react';
import "./style.scss";
import SideBar from '../../components/sideBar/SideBar';
import Footer from "../../components/footer/Footer";
import Autentication from "../../services/autentication";
import AutenticationAdmin from "../../services/autenticationAdmin";
import Resp from '../../components/resp/Resp';

import api from "../../services/api";
import { Link } from 'react-router-dom';

function CadastroLivro() {
    const userId = parseInt(localStorage.getItem('userId'))
    const [respInfo, setRespInfo] = useState([]);

    Autentication();
    AutenticationAdmin();

    function Submit(event) {
        event.preventDefault();
        
        const resp = document.getElementById('respReserv');

        const formData = {
            titulo: document.getElementById("title").value,
            autor: document.getElementById("author").value,
            editora: document.getElementById("editor").value,
            edicao: document.getElementById("edition").value,
            descricao: document.getElementById("desc").value,
            qtdEstoque: document.getElementById("quantity").value
        };

        console.log(formData);

        api({
            method: 'post',
            url: `/biblioteca/${userId}`,
            data: formData,
        }).then(function (response) {
            console.log(response)
            setRespInfo({ titulo: "Sucesso", parag: "Livro cadastrado com sucesso, clique no botão abaixo para verificar", btn: "Lista dos livros", link:"/listaLivros" })
            resp.classList.add("active");
            resp.classList.add("success");
        })
        .catch(function (err) {
            console.error("ops! ocorreu um erro" + err);
            setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
            resp.classList.add("active");
            resp.classList.add("error");
        });
    }

    // <td class="main_table_user_item" value={item.id} onClick={() =>localStorage.setItem('userId', item.id)} ><img class="main_table_user_about" src={loupe}/></td>

    return (
        <div id="rootCadastroLivro">
            <SideBar />
                <main class="main">
                    <h1 class="main_title">Adicionar Livro</h1>
                    <p class="main_parag">Preencha os campos abaixo para adicionar um novo livro ao sistema.</p>
                    <form action="" class="main_form" id="form" onSubmit={Submit}>
                        <input id="title" type="text" name="title" required class="main_form_input" placeholder="Título" />
                        <input id="author" type="text" name="author" required class="main_form_input" placeholder="Autor" />
                        <input id="editor" type="text" name="editor" required class="main_form_input" placeholder="Editora" />
                        <input id="edition" type="text" name="edition" required class="main_form_input" placeholder="Edição" />
                        <textarea id="desc" name="desc" required rows="5" class="main_form_textArea" placeholder="Descrição"></textarea>
                        <input id="quantity" type="number" name="quantity" required class="main_form_input quant" placeholder="quantidade" />
                        <input type="submit" class="main_form_button" id="formBtn" value="Cadastrar livro" />
                    </form>
                    <Resp  titulo={respInfo.titulo} parag={respInfo.parag} btn={respInfo.btn} link={respInfo.link} /> 
                </main>
            <Footer />
        </div>
    )
}
export default CadastroLivro;