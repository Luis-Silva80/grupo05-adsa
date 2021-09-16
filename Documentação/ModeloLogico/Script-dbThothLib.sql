create database dbThothLib;
use dbThothLib;

create table  tbInstituicao (
tbInstituicao_id int primary key auto_increment,
tbInstituicao_razaoSocial varchar(70),
tbInstituicao_cep  char(08),
tbInstituicao_email varchar(70),
tbInstituicao_telefone varchar(15)
) auto_increment = 0;

create table tbPerfilUsuario (

tbPerfilUsuario_id int primary key auto_increment,
tbPerfilUsuario_nome varchar(70),
tbPerfilUsuario_cpf  char(11),
tbPerfilUsuario_email varchar(70),
tbPerfilUsuario_telefone varchar(14),
tbPerfilUsuario_senha varchar(35),
tbPerfilUsuario_admin boolean,
tbPerfilUsuario_pontos bigint,
tbPerfilUsuario_qtdLivrosLidos int,
tbPerfilUsuario_qtdResenhas int,
fk_tbPerfilUsuario_tbInstituicao int,
foreign key (fk_tbPerfilUsuario_tbInstituicao) references tbInstituicao(tbInstituicao_id)
);


create table tbBiblioteca (

tbBiblioteca_id int primary key auto_increment,
tbBiblioteca_qtdLivros int,
fk_tbBiblioteca_tbPerfilUsuario int,
foreign key (fk_tbBiblioteca_tbPerfilUsuario) references tbPerfilUsuario(tbPerfilUsuario_id)
);

create table tbLivros (

tbLivros_id int primary key auto_increment,
tbLivros_titulo varchar(45),
tbLivros_descricao varchar(120),
tbLivros_autor varchar(45),
tbLivros_edicao varchar(45),
tbLivros_editora varchar(45),
tbLivros_status varchar(15),
tbLivros_qtdResenhas int,
tbLivros_qtdReservas int,
tbLivros_qtdEstoque int,
tbLivros_qtdReservadoAgora int,
fk_tbLivros_tbBiblioteca int,
foreign key (fk_tbLivros_tbBiblioteca) references tbBiblioteca(tbBiblioteca_id)
);

create table tbCategoria (

tbCategoria_id int primary key auto_increment,
tbCategoria_nome varchar(70),
fk_tbCategoria_tbLivros int,
foreign key (fk_tbCategoria_tbLivros) references tbLivros(tbLivros_id)
);

create table tbHistorico (

fk_tbHistorico_tbLivros int,
fk_tbHistorico_tbPerfilUsuario int,
tbHistorico_data datetime,
tbHistorico_nomeLivro varchar(70),
tbHistorico_nomePerfilUsuario varchar(70),
tbHistorico_acao varchar(15),
primary key(fk_tbHistorico_tbLivros, fk_tbHistorico_tbPerfilUsuario, tbHistorico_data),
foreign key(fk_tbHistorico_tbLivros) references tbLivros(tbLivros_id),
foreign key(fk_tbHistorico_tbPerfilUsuario) references tbPerfilUsuario(tbPerfilUsuario_id)
);

create table tbResenha(

tbResenha_dataPublicacao datetime,
tbResenha_conteudoPublicacao varchar(120),
fk_tbResenha_tbLivros int,
fk_tbResenha_tbPerfilUsuario int,
primary key(fk_tbResenha_tbLivros, fk_tbResenha_tbPerfilUsuario,tbResenha_dataPublicacao),
foreign key (fk_tbResenha_tbLivros) references tbLivros(tbLivros_id),
foreign key (fk_tbResenha_tbPerfilUsuario) references tbPerfilUsuario(tbPerfilUsuario_id)
);

select * from tbInstituicao;

insert into tbInstituicao(tbInstituicao_razaoSocial, tbInstituicao_cep, tbInstituicao_email, tbInstituicao_telefone) values
("Empresa do Dudu", "09836444", "empresaDoDudu@gmail.com.br", "11400289220");

select * from tbPerfilUsuario;

insert into tbPerfilUsuario(tbPerfilUsuario_nome, tbPerfilUsuario_cpf, tbPerfilUsuario_email, tbPerfilUsuario_telefone,
 tbPerfilUsuario_senha, tbPerfilUsuario_admin, tbPerfilUsuario_pontos, tbPerfilUsuario_qtdLivrosLidos,
 tbPerfilUsuario_qtdResenhas, fk_tbPerfilUsuario_tbInstituicao) values
("Hanan Ortiz", "11092878899" , "hanan.ortiz@email.com", "11978236659", "123456", true, 12455, 4, 10, 1);

update tbPerfilUsuario set tbPerfilUsuario_admin = 1 where fk_tbPerfilUsuario_tbInstituicao = 1;


