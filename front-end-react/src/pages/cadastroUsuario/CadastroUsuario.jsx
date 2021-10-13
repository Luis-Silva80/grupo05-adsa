// import css, React and hooks
import React, { useEffect } from 'react';
import "./style.scss";

/* 

    return (
        <>
            Conteudo HTML(main)
        </>
    )
    
*/

// import components
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';

// import api and links
import api from "../../services/api";
import { Link } from 'react-router-dom';

function CadastroUsuario() {

    function Submit(event) {
  
        event.preventDefault();

        const formData = {
            nome: document.getElementById("nome").value,
            cpf: document.getElementById("cpf").value,
            email: document.getElementById("email").value,
            telefone: document.getElementById("telefone").value,
            senha: document.getElementById("senha").value
        };

        console.log(formData);

        api({
            method: 'post',
            url: '/aluno/1',
            data: formData,
        }).then(function (response) {
            console.log(response.data)
        });
      }

    useEffect(() => {
        api
          .get("/aluno")
          .then((response) => {console.log(response.data);})
          .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
          });
    }, []);

    return (
        <>
            <SideBar />
            <main className="main container" id="mainCadastroUsuario">
                <h1 className="main_title">Cadastro</h1>
                <p className="main_parag">Faça o seu cadastro na nossa plataforma para reservar o seus livros.</p>
                <form action="" className="main_form" id="form" onSubmit={Submit}> 
                    <input type="text" id="nome" name="nome" required className="main_form_input" placeholder="Nome: Lucas Alves Pereira " />
                    <input type="number" id="cpf" name="cpf" required className="main_form_input" placeholder="CPF: 12345678911" />
                    <input type="text" id="email" name="email" required className="main_form_input" placeholder="Email: usuario.exemplo@email.com" />
                    <input type="text" id="telefone" name="telefone" required className="main_form_input" placeholder="Telefone: (11)123456789" />
                    <input type="password" id="senha" name="senha" required className="main_form_input" placeholder="Senha: *************" />
                    <input type="submit" className="main_form_button" id="formBtn" value="Cadastrar" />
                </form>
                <div className="main_box">
                    <p className="main_box_parag">Já tem uma conta?</p>
                    {/* <a className="main_box_link" href="login.html">Logar</a> */}
                    <Link className="main_box_link" to="/login">Logar</Link>
                </div>
                <section id="resp" className="resp"></section>
            </main>
            <Footer />
        </>
    );
}

export default CadastroUsuario;