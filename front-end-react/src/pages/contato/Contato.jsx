import React, { useEffect } from 'react';
import "./style.scss";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import LinkButton from '../../components/button/Button';
import Autentication from "../../services/autentication";

// import img from '../../assets/';

function Contato() {

    Autentication();

    return (
        <>
            <SideBar />
            <main className="main container" id="rootContato">
                <h1 className="main_title">Contato</h1>
                <div className="main_text">
                    <p>
                        Sugestões de livros, melhorias, críticas...
                    </p>
                    <p>
                        Preencha o formulário abaixo para entrar em contato com
                    </p>
                    <p>
                        a nossa equipe de atendimento!
                    </p>
                </div>
                <form action="" className="main_form">
                    <input className="main_input" type="text" placeholder="Informe o assunto" />
                    <textarea className="main_textarea" placeholder="Descrição" name="" id="" cols="30" rows="6"></textarea>
                </form>
                <div className="main_box">
                    <label className="main_box_label">
                        O prazo de resposta é de até 48 horas!
                    </label>
                    <LinkButton content="Enviar" className="main_box_button" />
                </div>
            </main>
            <Footer />
        </>
    );
}

export default Contato;