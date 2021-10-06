
// function getStoreId() {
//   let teste = localStorage.getItem('productId')
//   console.log("id aqui", teste);
// }
// window.onload = getStoreId()
var usersArr =  [
  { "id": 1, "img": "./assets/imgs/perfilIcon.png", "name": "Lucas gasfa gagasg dgasasf ", "email": "lucas.emailgrandeihauser@email.com", "status": "Ativo", "pendencia": null },
  { "id": 2, "img": "./assets/imgs/perfilIcon2.png", "name": "Matheus", "email": "matheus.user@email.com", "status": "Ativo", "pendencia": "01/01/01" },
  { "id": 3, "img": "./assets/imgs/perfilIcon.png", "name": "Rafael", "email": "rafael.user@email.com", "status": "Ativo", "pendencia": null },
  { "id": 4, "img": "./assets/imgs/perfilIcon2.png", "name": "Dudu", "email": "dudu.user@email.com", "status": "Inativo", "pendencia": "05/45/45" },
]

function loadUsers() {
  let table = document.getElementById("table");
  usersArr.forEach(element => {
    table.innerHTML += 
    `
      <tr class="main_table_user">
        <td class="main_table_user_item frst"><img class="main_table_user_img" src="${element.img}" alt="user img"></td>
        <td class="main_table_user_item name">${element.name}</td>
        <td class="main_table_user_item email">${element.email}</td>
        ${(element.status == "Inativo" ? `<td class='main_table_user_item inactive'>${element.status}</td>` : `<td class='main_table_user_item'>${element.status}</td>`)}
        ${(element.pendencia == null ? `<td class='main_table_user_item'>nenhuma</td>` : `<td class='main_table_user_item inactive'>${element.pendencia}</td>`)}
        <td class="main_table_user_item"><img class="main_table_user_about" onclick="callUser(${element.id})" src="./assets/imgs/loupe.png"></td>
        <td class="main_table_user_item lst"><img class="main_table_user_trash" onclick="deleteUser(${element.id})" src="./assets/imgs/trash.png"></td>
      </tr>
    `
  });
  console.log(usersArr);
}


function callUser(id) {
  let popup = document.getElementById("popup")
  let user = document.getElementById("user")

  popup.classList.toggle("active")

  let userArr = usersArr[id -1];

  user.innerHTML =
  `
    <h3 class="main_popup_user_name">${userArr.name}</h3>
    <p class="main_popup_user_email">Email: ${userArr.email}</p>
    <p class="main_popup_user_book">Livro reservado: alskdakj</p>
    <p class="main_popup_user_reserv">Reservado em: ioajsdas</p>
    <p class="main_popup_user_devolution">Devolver em: askdlas</p>
    <div class="main_popup_user_box">
      <button class="main_popup_user_box_btn">Enviar email</button>
      <button class="main_popup_user_box_btn">Prorrogar</button>
    </div>  
  `
}

function deleteUser(id) {
  let popup = document.getElementById("popup")
  let user = document.getElementById("user")

  popup.classList.toggle("active")

  let userArr = usersArr[id -1];
  user.innerHTML =
  `
    <h3 class="main_popup_user_name">Você tem certeza que deseja excluir</h3>
    <h3 class="main_popup_user_name">${userArr.name}?</h3>
    <p class="main_popup_user_email">Email: ${userArr.email}</p>
    <div class="main_popup_user_box">
      <button class="main_popup_user_box_btn delete">Excluir</button>
    </div>  
  `
}

window.onload = loadUsers();