import React from 'react';
import './style.scss';

// import components-
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import { Link } from 'react-router-dom';


import notFoundImg from '../../assets/erro404.gif';

function NotFound() {

  return (
    <div id="rootNotFound">
      <Header />
        <main className="main container">
          <h1 className="main_title">Ops! Parece que a página não foi encontrada.</h1>
          <img className="" src={notFoundImg} alt="Page not found"/>
          <p className="main_parag">Caso algo não pareça certo, entre em <Link to="/contato">contato</Link></p>
          <Link to="/" className="main_btn">Voltar</Link>
        </main>
      <Footer />
    </div>
  );
}

export default NotFound;