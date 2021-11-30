import React, { useEffect, useState } from 'react';
import "./style.scss";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import apiPython from '../../services/apiPython';
import api from "../../services/api";

// import img from '../../assets/';



function Contato() {


    const [ nome, setNome ]         = useState("");
    const [subject, setSubject]     = useState("");
    const [info, setInfo]           = useState("");
    const [ email, setEmail ]       = useState("");
    const [ telefone, setTelefone ] = useState("");

    function submit(event) {
        event.preventDefault();
        apiPython
        .post("/sendEmail", {
            nome_usuario  : nome,
            email_user    : email,
            telefone      : telefone,
            email         : "212-3a-grupo4@bandtec.com.br",
            subject       : subject,
            info          : info,
            tipo_operacao : "contatarEquipe" 
        })
        .then(response => {
            if (response.status === 201) {
                console.log("Deu certo!")               
            }
        })
        .catch(err => {
            console.error(err);
        })

    }

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
                <form onSubmit={submit} action="" className="main_form">
                    <input required onChange={e => setSubject(e.target.value)} className="main_input" type="text" placeholder="Informe o assunto" />
                    <input required onChange={e => setNome(e.target.value)} className="main_input" type="text" placeholder="Informe o seu nome" />
                    <input required onChange={e => setTelefone(e.target.value)} className="main_input" type="text" placeholder="Informe o seu telefone" />
                    <input required onChange={e => setEmail(e.target.value)} className="main_input" type="text" placeholder="Informe o seu email" />
                    <textarea required onChange={e => setInfo(e.target.value)} className="main_textarea" placeholder="Descrição" name="" id="" cols="30" rows="6"></textarea>
                    <input type="submit" content="Enviar" className="main_box_button" />
                </form>
                <div className="main_box">
                    <label className="main_box_label">
                        O prazo de resposta é de até 48 horas!
                    </label>
                </div>
            </main>
            <Footer />
        </>
    );
    }

export default Contato;