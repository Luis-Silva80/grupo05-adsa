import React, { useEffect } from 'react';
import "./style.scss";

import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';

import api from "../../services/api";
import { Link } from 'react-router-dom';


function Login() {
  function Submit(event) {
    //
    //    event.preventDefault();
    //
    //  const formData = {
    //        email: document.getElementById("email").value,
    //        password: document.getElementById("password").value
    //    };
    //
    //    console.log(formData);
    //
    //    api({
    //        method: 'get',
    //        url: '/aluno/1',
    //        data: formData,
    //    }).then(function (response) {
    //        console.log(response.data)
    //    });
  };


  return (
    <div id="rootLogin"y>
      <Header />
        <main className="main container">
          <h1 className="main_title">Login</h1>
          <p className="main_parag">Preencha os campos abaixo para acessar a sua conta.</p>
          <form action="" className="main_form" id="form" onsubmit={Submit}>
            <input type="text" id="email" name="email" required className="main_form_input" placeholder="Email: usuario.exemplo@email.com" />
            <input type="password" id="password" name="password" required className="main_form_input" placeholder="Senha: *************" />
            <input type="submit" className="main_form_button" id="formBtn" value="Entrar" />
          </form>
          <a className="main_forgot" href="#">Esqueci a minha senha</a>
          <div className="main_box">
            <p className="main_box_parag">Ainda não tem conta?</p>
            <Link to="/cadastroUsuario" className="main_box_link">Cadastrar</Link>
          </div>
          <section id="resp" className="resp"></section>
        </main>
      <Footer />
    </div>
  );
}
export default Login;