function submitForm(event) {
    // Prevent the form from submitting.
    event.preventDefault()
    // Set url for submission and collect data.
    const url = "https://example.com/..."
    const formData = new FormData(event.target)
    // Submit the data.
    const request = new XMLHttpRequest()
    request.open("POST", url)
    request.send(formData)
    // Log the data
    const data = {}
    formData.forEach((value, key) => (data[key] = value))
    console.log(data)
}

function addBook(event) {
    event.preventDefault()
    // let form = $("form").serializeArray();
    // console.log("primeiro log",form);

    var formData = new FormData(document.getElementById('form'));
    // const data = {}
    // formData.forEach((value, key) => (data[key] = value))
    // console.log("teste",data)
    
    fetch("/login", {
      method: "POST",
      body: formData
    });

    // fetch(`https://codeby-cors.integrationby.com.br/https://www.googleapis.com/books/v1/volumes?q=${bookName}&maxResults=40`, options)
    

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