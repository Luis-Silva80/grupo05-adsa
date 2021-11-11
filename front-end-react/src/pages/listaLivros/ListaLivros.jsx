import React from 'react';
import "./style.scss";
import Autentication from "../../services/autentication";
// import { Link } from 'react-router-dom';

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import indisponivel from "../../assets/fotoIndisponivel.png"

function ListaLivros() {

  Autentication();

  const options = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json', Accept: 'application/vnd.vtex.ds.v10+json' }
  };
  
  let index = 0
  function search() {
    // let alert = document.getElementById('alert')
    let searchCallback = document.getElementById('searchCallback')
    let resp = document.getElementById('resp')
    let bookName = document.getElementById('name').value
    if (bookName.length < 3) {
      // alert.classList.add('show')
    } else {
      // alert.classList.remove('show')


      resp.innerHTML = ""
      // alterar os valores do index para implementar o scroll infinito    &startIndex=1
      fetch(`https://codeby-cors.integrationby.com.br/https://www.googleapis.com/books/v1/volumes?q=intitle:${bookName}&maxResults=40&startIndex=${index}`, options)
        .then(response => response.text())
        .then(result => {
          let data = JSON.parse(result)
          // index += 40
          console.log("data: ",data);

          searchCallback.innerHTML = `<h3>Exibindo ${data.totalItems} resultados encontrados para: ${bookName}</h3>`

          data.items.forEach(element => {

            resp.innerHTML +=
              `
                <div class="resp_book">
                <img class="resp_book_img" src="${element.volumeInfo.imageLinks ? element.volumeInfo.imageLinks.thumbnail : `${indisponivel}`}">
                ${element.volumeInfo.previewLink ? `<a class="resp_book_tag" target="_blank" href='${element.volumeInfo.previewLink}'>Preview</a>` : ""}
                <h3 class="resp_book_title">${element.volumeInfo.title}</h3>
                <a  class="resp_book_btn" onClick={storeId(${element.id})}>Ver mais</a>
                </div>
              `
          });
        })
        .catch(err => console.error(err));
    }
  }
  
  // function infiniteSearch() {
  //   // alterar os valores do index para implementar o scroll infinito    &startIndex=1
  //   let resp = document.getElementById('resp')
  //   let bookName = document.getElementById('name').value

  //   console.log(window.scrollY);

  //   if (window.scrollY > 6000) {
      
  //     let index = 41;
  //     fetch(`https://codeby-cors.integrationby.com.br/https://www.googleapis.com/books/v1/volumes?q=intitle:${bookName}&maxResults=40&startIndex=${index}`, options)
  //       .then(response => response.text())
  //       .then(result => {
  //         let data = JSON.parse(result)
  //         data.items.forEach(element => {

  //           resp.innerHTML +=
  //             `
  //             <div class="resp_book">
  //             <img class="resp_book_img" src="${element.volumeInfo.imageLinks ? element.volumeInfo.imageLinks.thumbnail : `${indisponivel}`}">
  //             ${element.volumeInfo.previewLink ? `<a class="resp_book_tag" target="_blank" href='${element.volumeInfo.previewLink}'>Preview</a>` : ""}
  //             <h3 class="resp_book_title">${element.volumeInfo.title}</h3>
  //             <a href="/cadastroUsuario" class="resp_book_btn">Ver mais</a>
  //             </div>
  //           `
  //         });
  //       })
  //       .catch(err => console.error(err));

  //   }
  // }
  function storeId(value) {
    localStorage.setItem('productId', value)
  }

  return (
    <div id="rootListaLivro">
      <SideBar />
      <main className="main container" >
        <h1 className="main_title">Livros cadastrados</h1>

        <div className="main_nav">
          <input className="main_nav_input" placeholder="Digite o nome do livro" type="text" name="name" id="name" />
          <button className="main_nav_btn" onClick={search}>Pesquisar</button>
          <select className="main_nav_filter">
            <option className="main_nav_filter_value" value="#">Filtrar por: </option>
            <option className="main_nav_filter_value" value="pendencia">Categoria</option>
            <option className="main_nav_filter_value" value="nomeDesc">Nome Desc</option>
            <option className="main_nav_filter_value" value="nomeAsc">Nome Asc</option>
          </select>
        </div>
        <h3 id="searchCallback" className="main_callback"></h3>
        <div id="resp" className="resp"></div>
        {/* <button className="main_nav_btn" onClick={search}>Ver mais</button> */}
        
        {/* <div id="resp" className="resp" onScroll={infiniteSearch}></div> */}
        {/* <span className='main_alert'>Digite ao menos 3 letras</span> */}
      </main>
      <Footer />
    </div>
  );
}

export default ListaLivros;