function addBook(event) {
    event.preventDefault()

    let formData = $("form").serializeArray();
    console.log(formData);
    let bookName = formData[0].value
    console.log(bookName);

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