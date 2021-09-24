function addBook(event) {
    event.preventDefault()

    let form = $("form").serializeArray();
    console.log(form);
    let bookName = form[0].value
    console.log(bookName);

    let formId =  document.getElementById("form")

    let formData = new FormData();
    formData.append(formId);



    console.log(formData);

    // formData.append('name', 'John');
    // formData.append('password', 'John123');
    
    // fetch("localhost:8080",{
    //     body: formData,
    //     method: "post"
    // });


    // const options = {
    //     method: 'POST',
    //     headers: { 'Content-Type': 'application/json', Accept: 'application/vnd.vtex.ds.v10+json' }
    // };
    // fetch(`https://codeby-cors.integrationby.com.br/https://www.googleapis.com/books/v1/volumes?q=${bookName}&maxResults=40`, options)
    // .then(response => response.text())
    // .then(result => {
    //     let data = JSON.parse(result)
    //     console.log(data);



    // })
    // .catch(err => console.error(err));




    // }


    exibeResp(false)
    function exibeResp(validation) {
        let resp = document.getElementById("resp")
        resp.classList.add("active");
        
        // aqui a lógica é receber um boolean do backend informando se foi cadastrado ou não
        // caso seja, gera um link para a página do livro, para que o admin consulte
        if (validation) {
            resp.innerHTML =   
            `
                <h3 class="resp_title">Livro cadastrado com sucesso!</h3>
                <p class="resp_text">Você pode consultar em:</p>
                <a class="resp_link"> link com nome: ${bookName}</a>
                <img class="resp_img" src="./assets/imgs/close.png" onclick="closeResp()">
            `
        }else{
            resp.innerHTML =   
            `
                <h3 class="resp_title">O livro não pôde ser cadastrado!</h3>
                <p class="resp_text">Por favor, tente novamente, se o problema persistir, entre em contato com o nosso suporte!</p>
                <a class="resp_ctto"> Contato</a>
                <img class="resp_img" src="./assets/imgs/close.png" onclick="closeResp()">
            `
        }
    }
}
function closeResp(){
    let resp = document.getElementById("resp")
    resp.classList.remove("active");
}