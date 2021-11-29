// import css, React and hooks
import React, { useEffect, useState } from 'react';
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
    const [ nome, setNome ] = useState("");
    const [ cpf, setCpf ] = useState("");
    const [ email, setEmail ] = useState("");
    const [ telefone, setTelefone ] = useState("");
    const [ senha, setSenha ] = useState("");


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

    return (
        <div id="rootCadastroUsuario">
            <Header />
            <main className="main container">
                <h1 className="main_title">Cadastro</h1>
                <p className="main_parag">Faça o seu cadastro na nossa plataforma para reservar o seus livros.</p>
                <form action="" className="main_form" id="form" onSubmit={submit}> 
                    <input type="text" required onChange={e => setNome(e.target.value)} className="main_form_input" placeholder="Ex: Nome Sobrenome " />
                    <input type="number" required onChange={e => setCpf(e.target.value)} className="main_form_input" placeholder="Ex: 12345678911" />
                    <input type="text" required onChange={e => setEmail(e.target.value)} className="main_form_input" placeholder="Ex: exemplo.exemplo@email.com" />
                    <input type="text" required onChange={e => setTelefone(e.target.value)} className="main_form_input" placeholder="Ex: (11)123456789" />
                    <input type="password" required onChange={e => setSenha(e.target.value)} className="main_form_input" placeholder="*************" />      
                    <input type="submit" className="main_form_button" value="Cadastrar" />
                    
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