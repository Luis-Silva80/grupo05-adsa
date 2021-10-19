import React, { useEffect } from 'react';
import Header from "../../components/header/Header";
import Footer from "../../components/footer/Footer";
import "./style.scss";

function CadastroLivro() {

      

    // function Submit(event) {
    
    // event.preventDefault();

    // const formData = {
    //     nome: document.getElementById("nome").value,
    //     cpf: document.getElementById("cpf").value,
    //     email: document.getElementById("email").value,
    //     telefone: document.getElementById("telefone").value,
    //     senha: document.getElementById("senha").value
    // };

    // console.log(formData);

    // api({
    //     method: 'post',
    //     url: '/aluno/1',
    //     data: formData,
    // }).then(function (response) {
    //     console.log(response.data)
    // });
    // }

    // useEffect(() => {
    // api
    //     .get("/aluno")
    //     .then((response) => {console.log(response.data);})
    //     .catch((err) => {
    //     console.error("ops! ocorreu um erro" + err);
    // });
    // }, []);

    return(
        <>
        <Header/>
        <main class="main container">
        <h1 class="main_title">Adicionar Livro</h1>
        <p class="main_parag">Preencha os campos abaixo para adicionar um novo livro ao sistema.</p>
        <form action="" class="main_form" id="form" onsubmit={"Submit"}>
        <input type="text" name="title" required class="main_form_input" placeholder="Título"/>
        <input type="text" name="author" required class="main_form_input" placeholder="Autor"/>
        <input type="text" name="editor" required class="main_form_input" placeholder="Editora"/>
        <input type="text" name="edition" required class="main_form_input" placeholder="Edição"/>
        <textarea name="desc" required rows="5" class="main_form_textArea" placeholder="Descrição"></textarea>
        <input type="number" name="quantity" required class="main_form_input quant" placeholder="quantidade"/>
        <input type="submit" class="main_form_button" id="formBtn" value="Cadastrar livro"/>
        </form>
        <section id="resp" class="resp"></section>
        </main>


        <Footer/>
            </>

        )


}




export default CadastroLivro;