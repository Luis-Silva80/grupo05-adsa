from logging import info
from flask import Flask, json, request, jsonify
from flask_cors import CORS, cross_origin
from flask_api import status
from email.message import EmailMessage
import smtplib
import os



#S M T P - SIMPLE MAIL TRANSFER PROTOCOL

from email.mime.multipart import MIMEMultipart
from email.mime.text      import MIMEText

from flask.wrappers import Response

app = Flask(__name__)
CORS(app, support_credentials=True)

@cross_origin(supports_credentials=True)
@app.route(f'/sendEmail', methods=['POST'])
def send_user_validation_email():  # put application's code here
    
    conteudo = request.get_json()

    email_message =  MIMEMultipart()
  
    if(conteudo['tipo_operacao'] == 'validacaoEmail'):

        corpo  = f'''<p><b>Olá {conteudo['nome_usuario']}, tudo bem?</b></p><br>
         <p>Aqui está o seu código para validação da sua conta: </p> 
         <span style= "color:blue;"> {conteudo['info']} </span>'''
        email_message['To'] = conteudo['email_user']
        email_message['Subject'] = "Validação de novo usuário!"

    elif(conteudo['tipo_operacao'] == 'contatarEquipe'):

        
        corpo = f'''
        <p>O usuário <b>{conteudo['nome_usuario']}</b> está nos contatando!!</p>
        <p>{conteudo['info']}</p>
        <p>Telefone do usuário: {conteudo['telefone']}</p>
        <p>Email do usuário: {conteudo['email_user']}</p>
        '''
        email_message['Subject'] = conteudo['subject']
        email_message['To'] = conteudo['email']
       
        

    #  nome_usuario = "Hanan"

    #  numero_codigo = "12382"

    
    # 1 --> STARTAR O SERVIDOR SMTP

    host = "smtp.office365.com"

    port = "587"

    login = "212-3a-grupo4@bandtec.com.br"

    senha = "#Gfgrupo4"

    server = smtplib.SMTP(host,port)

    server.ehlo()
    server.starttls()
    server.ehlo()
    server.login(login,senha)

    # 2 --> CONSTRUIR UM EMAIL DO TIPO MIME

 

    
    email_message['From'] = login
  
  
    email_message.attach(MIMEText(corpo,'html'))

    # 3 --> ENVIAR O EMAIL DO TIPO MIME NO SERVIDOR SMTP

    server.sendmail(email_message['From'], email_message['To'], email_message.as_string())

    server.quit()

   # return app.response_class(response=json.dumps(conteudo), status=201)
    
    return app.response_class(status=201)


if __name__ == '__main__':
    app.run()
