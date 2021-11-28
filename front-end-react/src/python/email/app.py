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

    print(conteudo)

    #  nome_usuario = "Hanan"

    #  numero_codigo = "12382"

    
    # 1 --> STARTAR O SERVIDOR SMTP

    host = "smtp.office365.com"

    port = "587"

    login = "212-3a-grupo4@bandtec.com.br"

    email_user = conteudo['email']

    senha = "#Gfgrupo4"

    server = smtplib.SMTP(host,port)

    server.ehlo()
    server.starttls()
    server.ehlo()
    server.login(login,senha)

    # 2 --> CONSTRUIR UM EMAIL DO TIPO MIME

    corpo  = f'''<p><b>Olá {conteudo['nome_usuario']}, tudo bem?</b></p><br>
    <p>Aqui está o seu código para validação da sua conta: </p> 
    <span style= "color:blue;"> {conteudo['numero_codigo']} </span>'''

    email_message =  MIMEMultipart()
    email_message['From'] = login
    email_message['To'] = email_user
    email_message['Subject'] = "Validação de novo usuário!"
    email_message.attach(MIMEText(corpo,'html'))

    # 3 --> ENVIAR O EMAIL DO TIPO MIME NO SERVIDOR SMTP

    server.sendmail(email_message['From'], email_message['To'], email_message.as_string())

    server.quit()

    return app.response_class(response=json.dumps(conteudo), status=201)


if __name__ == '__main__':
    app.run()
