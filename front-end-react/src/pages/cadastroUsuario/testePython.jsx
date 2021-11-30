import apiPython from "../../services/apiPython"

function CadastroUsuario() {

   function teste(){

    const numero_aleatorio = Math.random(1_000_000, 9_999_999)

        apiPython.post("/sendEmail",
        {
            nome_usuario : "Hanan",
            email : "212-3a-grupo4@bandtec.com.br",
            numero_codigo : "13823"

        }).then((resposta) => {
            if (resposta.status === 201) {

                alert(resposta.data)

            }
        }).catch((erro) => {
            console.log(erro);
        })

   }


    return (
      
        <button onClick = {teste}>Testar</button>
        
    );
}

export default CadastroUsuario