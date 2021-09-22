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
            <a href="./usuarios.html" target="_blank" onclick="storeId('${element.id}')" class="book_btn">Ver mais</a>
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




