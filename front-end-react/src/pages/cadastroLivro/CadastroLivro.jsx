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
            title: document.getElementById("title").value,
            author: document.getElementById("author").value,
            editor: document.getElementById("editor").value,
            edition: document.getElementById("edition").value,
            desc: document.getElementById("desc").value,
            quantity: document.getElementById("quantity").value
        };

        console.log(formData);

        api({
            method: 'post',
            url: '/livros/1/5',
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