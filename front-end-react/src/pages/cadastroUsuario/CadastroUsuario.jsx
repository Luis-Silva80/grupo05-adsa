// import css, React and hooks
import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import "./style.scss";

// import components
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import Resp from '../../components/resp/Resp';

// import api and links
import api from "../../services/api";
import { Link } from 'react-router-dom';
import apiPython from "../../services/apiPython"
import closeButton from "../../assets/close.png";

function CadastroUsuario() {

    const history = useHistory();
    const [ nome, setNome ] = useState("");
    const [ cpf, setCpf ] = useState("");
    const [ email, setEmail ] = useState("");
    const [ telefone, setTelefone ] = useState("");
    const [ senha, setSenha ] = useState("");
    const [ respInfo, setRespInfo ] = useState([]);

    let numero_aleatorio;


    function submit(event) {
        event.preventDefault();
        api
        .post("/aluno", {
            nome: nome,
            cpf: cpf,
            email: email,
            telefone: telefone,
            senha: senha

        })
        .then(response => {
            if (response.status === 201) {
                history.push("/login");
            }
        })
        .catch(err => {
            console.error(err);
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

    function sendEmail(){
    
        apiPython
        .post("/sendEmail", {
            nome_usuario  : nome,
            email_user    : email,
            telefone      : telefone,
            email         : "212-3a-grupo4@bandtec.com.br",
            subject       : "Teste",
            info          : numero_aleatorio,
            tipo_operacao : "validacaoEmail" 
        })
        .then(response => {
            if (response.status === 201) {
                document.getElementById("submitButton").style.display = "block";
            }
        })
        .catch(err => {
            console.error(err);
        })
    }

    function verifyConfirmation() {

        const resp = document.getElementById('respReserv');

        if (document.getElementById("confirmationValue").value == numero_aleatorio) {
            setRespInfo({ titulo: "Sucesso", parag: "Validação feita com sucesso" })
            resp.classList.add("active");
            resp.classList.remove("error");
            resp.classList.add("success");
            document.getElementById("submitButton").classList.add("active");
            document.getElementById("validationButton").classList.add("noActive");
        } else {
            document.querySelector(".main_confirmationPopup_content_response").classList.add("active");
        }

    }

    function openPopup() {

        numero_aleatorio = (Math.random(1000000, 9999999) * 1000000).toFixed(0);

        sendEmail();

        document.querySelector(".main_confirmationPopup").classList.add("active");
    }

    function closePopup() {
        document.querySelector(".main_confirmationPopup").classList.remove("active");
    }

    return (
        <div id="rootCadastroUsuario">
            <Header />
            <main className="main container">
                <h1 className="main_title">Cadastro</h1>
                <form action="" className="main_form" id="form" onSubmit={submit}> 
                    <label className="main_form_label">Nome:</label>
                    <input type="text" required onChange={e => setNome(e.target.value)} className="main_form_input" placeholder="Nome Sobrenome " />
                    
                    <label className="main_form_label">CPF:</label>
                    <input type="number" required onChange={e => setCpf(e.target.value)} className="main_form_input" placeholder="123.456.789.11" />
                    
                    <label className="main_form_label">Email:</label>
                    <input type="text" required onChange={e => setEmail(e.target.value)} className="main_form_input" placeholder="exemplo.exemplo@email.com" />
                    
                    <label className="main_form_label">Numero de telefone:</label>
                    <input type="text" required onChange={e => setTelefone(e.target.value)} className="main_form_input" placeholder="(11)123456789" />
                    
                    <label className="main_form_label">Senha:</label>
                    <input type="password" required onChange={e => setSenha(e.target.value)} className="main_form_input" placeholder="*************" />
                    
                    <button type="button" id="validationButton" onClick={openPopup} className="main_form_button">Confirmar e-mail</button>
                    <input type="submit" className="main_form_button" id="submitButton" value="Cadastrar" />
                </form>
                <div className="main_box">
                    <p className="main_box_parag">Já tem uma conta?</p>
                    <Link className="main_box_link" to="/login">Logar</Link>
                </div>
                <div className="main_confirmationPopup">
                    <div className="main_confirmationPopup_content">
                        <h3 className="main_confirmationPopup_content_title" >Confirmação de e-mail</h3>
                        <p className="main_confirmationPopup_content_parag">Enviamos um e-mail para o e-mail informado anteriormente, coloque o código enviado para confirmar</p>
                        <input className="main_confirmationPopup_content_input" id="confirmationValue" type="text" />

                        <p className="main_confirmationPopup_content_response">Número informado incorreto</p>

                        <div className="main_confirmationPopup_content_box">
                            <button className="main_confirmationPopup_content_box_button" onClick={verifyConfirmation}>Confirmar</button>
                            <button className="main_confirmationPopup_content_box_button" onClick={sendEmail}>Enviar e-amail novamente</button>
                        </div>
                        <button onClick={closePopup} className="main_confirmationPopup_content_close"><img src={closeButton} /></button>
                    </div>
                </div>
                <Resp titulo={respInfo.titulo} parag={respInfo.parag} btn={respInfo.btn} link={respInfo.link} /> 
            </main>
            <Footer />
        </div>
    );
}

export default CadastroUsuario;