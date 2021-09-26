
function getStoreId() {
  let teste = localStorage.getItem('productId')
  console.log("id aqui", teste);
}
window.onload = getStoreId()

function callUser() {
  let popup = document.getElementById("popup")
  let user = document.getElementById("user")

  popup.classList.toggle("active")

  user.innerHTML +=
  `
  <h3 class="main_popup_user_name">Usuário x</h3>
  <p class="main_popup_user_email">Email: email aslaks</p>
  <p class="main_popup_user_book">Livro reservado: alskdakj</p>
  <p class="main_popup_user_reserv">Reservado em: ioajsdas</p>
  <p class="main_popup_user_devolution">Devolver em: askdlas</p>
  <div class="main_popup_user_box">
    <button class="main_popup_user_box_btn">Enviar email</button>
    <button class="main_popup_user_box_btn">Prorrogar</button>
  </div>  
  `
}

// travado em como pegar as informações dos usuários e exibir usando uma função só