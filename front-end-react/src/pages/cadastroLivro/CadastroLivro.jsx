import React, { useEffect, useState } from 'react';
import "./style.scss";
import SideBar from '../../components/sideBar/SideBar';
import Footer from "../../components/footer/Footer";
import Autentication from "../../services/autentication";
import AutenticationAdmin from "../../services/autenticationAdmin";
import closeButton from "../../assets/close.png";
import Resp from '../../components/resp/Resp';

import api from "../../services/api";
import { Link } from 'react-router-dom';

function CadastroLivro() {
    const userId = parseInt(localStorage.getItem('userId'))
    const [respInfo, setRespInfo] = useState([]);


    Autentication();
    AutenticationAdmin();

    function Submit(event) {
        event.preventDefault();
        
        const resp = document.getElementById('respReserv');

        const formData = {
            titulo: document.getElementById("title").value,
            autor: document.getElementById("author").value,
            editora: document.getElementById("editor").value,
            edicao: document.getElementById("edition").value,
            descricao: document.getElementById("desc").value,
            qtdEstoque: document.getElementById("quantity").value,
            linguagem: document.getElementById("language").value
        };

        console.log(formData);

        api({
            method: 'post',
            url: `/biblioteca/${userId}`,
            data: formData,
        }).then(function (response) {
            console.log(response)
            setRespInfo({ titulo: "Sucesso", parag: "Livro cadastrado com sucesso, clique no botão abaixo para verificar", btn: "Lista dos livros", link:"/listaLivros" })
            resp.classList.add("active");
            resp.classList.add("success");
        })
        .catch(function (err) {
            console.error("ops! ocorreu um erro" + err);
            setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
            resp.classList.add("active");
            resp.classList.add("error");
        });
    }

    function multRegister() {
        const userFile = document.getElementById("files").value;
        console.log("Cadastrando", userFile);

        api
        .patch(`/biblioteca/upload`, {
            data: userFile
        })
        .then((response) => {
            if (response.status === 200) {
                alert("Livros cadastrados com sucesso")
            }
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });

    }

    function openPopup() {
        document.querySelector(".main_registerBox").classList.add("active");
    }

    function closePopup() {
        document.querySelector(".main_registerBox").classList.remove("active");
    }

    // <td className="main_table_user_item" value={item.id} onClick={() =>localStorage.setItem('userId', item.id)} ><img className="main_table_user_about" src={loupe}/></td>

    return (
        <div id="rootCadastroLivro">
            <SideBar />
                <main className="main">
                    <h1 className="main_title">Adicionar Livro</h1>
                    <p className="main_parag">Preencha os campos abaixo para adicionar um novo livro ao sistema.</p>
                    <form action="" className="main_form" id="form" onSubmit={Submit}>
                        <input id="title" type="text" name="title" required className="main_form_input" placeholder="Título" />
                        <input id="author" type="text" name="author" required className="main_form_input" placeholder="Autor" />
                        <input id="editor" type="text" name="editor" required className="main_form_input" placeholder="Editora" />
                        <input id="edition" type="text" name="edition" required className="main_form_input" placeholder="Edição" />
                        <textarea id="desc" name="desc" required rows="5" className="main_form_textArea" placeholder="Descrição"></textarea>
                        <select id="language" name="language" className="main_form_input" >
                            <option value="">IDIOMA</option>
                            <option value="portugues">PT/BR</option>
                            <option value="ingles">ENG</option>
                        </select>
                        <input id="quantity" type="number" name="quantity" required className="main_form_input quant" placeholder="quantidade" />
                        <input type="submit" className="main_form_button" id="formBtn" value="Cadastrar livro" />
                        <button type="button" onClick={openPopup} className="main_form_button">Cadastrar multiplos livros</button>
                    </form>

                    <div className="main_registerBox">
                        <div className="main_registerBox_content">
                            <h1 className="main_registerBox_content_title">Cadastrar multiplos livros</h1>
                            <p className="main_registerBox_content_parag">Escolha um arquivo .txt(bloco de notas) no padrão do arquivo de layout para cadastrar</p>
                            <input className="main_registerBox_content_input" type="file" id="files" name="files" multiple />
                            <button className="main_registerBox_content_button" onClick={multRegister}>Cadastrar</button>
                            <button onClick={closePopup} className="main_registerBox_content_close"><img src={closeButton} /></button>
                        </div>
                    </div>

                    <Resp  titulo={respInfo.titulo} parag={respInfo.parag} btn={respInfo.btn} link={respInfo.link} /> 
                </main>
            <Footer />
        </div>
    )
}
export default CadastroLivro;