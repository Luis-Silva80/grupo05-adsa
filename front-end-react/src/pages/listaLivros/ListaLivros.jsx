import React, { useEffect } from 'react';
import "./style.scss";
import { Link } from 'react-router-dom';

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';

// function productShelf() {
//     return(
//         <>
//             <div class="book">
//                 <img class="book_img" src="${element.volumeInfo.imageLinks.thumbnail}" />
//                 <h3 class="book_title">${element.volumeInfo.title}</h3>
//                 <Link to="/cadastroUsuario" class="book_btn">Ver mais</Link>
//             </div>
//         </>
//     );
// }

function ListaLivros() {

    function search() {
        let bookName = document.getElementById('name').value
        let searchCallback = document.getElementById('searchCallback')
        let resp = document.getElementById('resp')
        // let alert = document.getElementById('alert')
      
        if (bookName.length < 3) {
          // alert.classList.add('show')
        } else {
          // alert.classList.remove('show')
      
          const options = {
            method: 'GET',
            headers: { 'Content-Type': 'application/json', Accept: 'application/vnd.vtex.ds.v10+json' }
          };
          resp.innerHTML = ""
          fetch(`https://codeby-cors.integrationby.com.br/https://www.googleapis.com/books/v1/volumes?q=${bookName}&maxResults=5`, options)
          .then(response => response.text())
          .then(result => {
            let data = JSON.parse(result)
            console.log(data);
      
            searchCallback.innerHTML = `<h3>Exibindo ${data.totalItems} resultados encontrados para: ${bookName}</h3>`
      
            data.items.forEach(element => {
      
              resp.innerHTML +=
                `
                <div class="book">
                  <img class="book_img" src="${element.volumeInfo.imageLinks.thumbnail}">
                  <h3 class="book_title">${element.volumeInfo.title}</h3>
                  <a href="/cadastroUsuario" class="book_btn">Ver mais</a>
                  </div>
                  `
                });
          })
          .catch(err => console.error(err));
        }
    }
      
    function storeId(value) {
        localStorage.setItem('productId', value)
    }

    return (
        <>
            <SideBar />
            <main class="main container" id="rootListaLivro">
                <h1 class="main_title">Livros cadastrados</h1>

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

            </main>
            <Footer />
        </>
    );
}

export default ListaLivros;