import React, { useState } from 'react';
import "./style.scss";

import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';

import api from "../../services/api";
import { Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom';


function Login() {

  const history = useHistory();
  const [ email, setEmail ] = useState();
  const [ password, setPassword ] = useState();
  
  function Submit(event) {
    
    event.preventDefault();
    
    api({
      method: 'post',
      url: '/autenticacao/' + email + '/' + password
    }).then(response => {
      if (response.status === 200) {
        localStorage.setItem('userId', response.data);
        history.push("/perfilUsuario");
      }
    })
    .catch((erro) => {
      console.log(erro);
    });
  };

  return (
    <>
      <Header />

      <section id="rootLogin">
        <main className="main container">
          <h1 className="main_title">Login</h1>
          <p className="main_parag">Preencha os campos abaixo para acessar a sua conta.</p>
          <form action="" className="main_form" id="form" onSubmit={Submit}>
            <input onChange={e => setEmail(e.target.value)} type="text" id="email" name="email" required className="main_form_input" placeholder="Email: usuario.exemplo@email.com" />
            <input onChange={e => setPassword(e.target.value)} type="password" id="password" name="password" required className="main_form_input" placeholder="Senha: *************" />
            <input type="submit" className="main_form_button" id="formBtn" value="Entrar" />
          </form>
          <a className="main_forgot" href="#">Esqueci a minha senha</a>
          <div className="main_box">
            <p className="main_box_parag">Ainda não tem conta?</p>
            <Link to="/cadastroUsuario" className="main_box_link">Cadastrar</Link>
          </div>
          <section id="resp" className="resp"></section>
        </main>
      </section>

      <Footer />
    </>
  );
}
export default Login;