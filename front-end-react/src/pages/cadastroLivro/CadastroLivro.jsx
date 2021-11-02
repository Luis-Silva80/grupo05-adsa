import React, { useEffect } from 'react';
import "./style.scss";
import SideBar from '../../components/sideBar/SideBar';
import Footer from "../../components/footer/Footer";

import api from "../../services/api";
import { Link } from 'react-router-dom';

function CadastroLivro() {
    function Submit(event) {

        event.preventDefault();

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
            url: '/livros/1',
            data: formData,
        }).then(function (response) {
            console.log(response.data)
        });
    }

    useEffect(() => {
        api
            .get("/livros")
            .then((response) => { console.log(response.data); })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);

    // <td class="main_table_user_item" value={item.id} onClick={() =>localStorage.setItem('userId', item.id)} ><img class="main_table_user_about" src={loupe}/></td>

    return (
        <>
            <SideBar />
            <section id="rootCadastroLivro">
                <main class="main container">
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
                    <section id="resp" class="resp"></section>
                </main>
            </section>
            <Footer />
        </>
    )
}
export default CadastroLivro;