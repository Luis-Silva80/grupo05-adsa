// import css, React and hooks
import React, { useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import "./style.scss";

// import components
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';

// import api and links
import api from "../../services/api";
import { Link } from 'react-router-dom';

function CadastroUsuario() {

    const history = useHistory();

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
            url: '/aluno',
            data: formData,
        }).then(response => {
            console.log(response)
            history.push('/login');
        }).catch(erro => {
            console.error(erro);
        })
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
        <div id="rootCadastroUsuario">
            <Header />
            <main className="main container">
                <h1 className="main_title">Cadastro</h1>
                <p className="main_parag">Faça o seu cadastro na nossa plataforma para reservar o seus livros.</p>
                <form action="" className="main_form" id="form" onSubmit={Submit}> 
                    <input type="text" id="nome" name="nome" required className="main_form_input" placeholder="Ex: Nome Sobrenome " />
                    <input type="number" id="cpf" name="cpf" required className="main_form_input" placeholder="Ex: 12345678911" />
                    <input type="text" id="email" name="email" required className="main_form_input" placeholder="Ex: exemplo.exemplo@email.com" />
                    <input type="text" id="telefone" name="telefone" required className="main_form_input" placeholder="Ex: (11)123456789" />
                    <input type="password" id="senha" name="senha" required className="main_form_input" placeholder="*************" />
                    <input type="submit" className="main_form_button" id="formBtn" value="Cadastrar" />
                </form>
                <div className="main_box">
                    <p className="main_box_parag">Já tem uma conta?</p>
                    <Link className="main_box_link" to="/login">Logar</Link>
                </div>
                <section id="resp" className="resp"></section>
            </main>
            <Footer />
        </div>
    );
}

export default CadastroUsuario;