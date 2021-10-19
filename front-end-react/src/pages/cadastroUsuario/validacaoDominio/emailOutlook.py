import smtplib
import ssl
from   email.mime.multipart import MIMEMultipart
from   email.mime.text      import MIMEText


# 1 - AQUI É FEITO O START DO SERVIDOR SMTP


smtp_server = "smtp-mail.outlook.com"
port   = 587
email_address = "212-3a-grupo4@bandtec.com.br"
email_password = "#Gfgrupo4"

server = smtplib.SMTP(smtp_server, port)

server.ehlo()
server.starttls()

server.login(email_address, email_password)

#2 - CONSTRUINDO O EMAIL --> DEFININDO UM CORPO

body = "<p><b style = color: blue; font-size: 40px; ><i>Olá!</i></b></p>"

email_msg = MIMEMultipart()

email_msg['From']    = email_address
email_msg['To']      = "hanan.spinola@bandtec.com.br"
email_msg['Subject'] = "Tentativa 2"
email_msg.attach(MIMEText(body, 'html')) 

#3 - ENVIAR O EMAIL APÓS MONTADO --> 

server.sendmail(email_msg['From'], email_msg['To'], email_msg.as_string())

server.quit();
