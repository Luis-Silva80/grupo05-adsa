create database dbThothLib;
-- drop database dbThothLib;
-- drop table tbPerfilUsuario;
-- delete from tbInstituicao where id = 2
use dbThothLib;

select * from tbInstituicao;
select * from tbPerfilUsuario;
select * from tbBiblioteca;
select * from tbLivros;
select * from tbCategoria;
select * from tbHistorico;
select * from tbResenha;

create table  tbInstituicao (
id int primary key auto_increment,
razao_social varchar(70),
cep  char(08),
email varchar(70),
telefone varchar(15)
);

create table tbPerfilUsuario (
id int primary key auto_increment,
nome varchar(70),
cpf  char(11),
email varchar(70),
telefone varchar(14),
senha varchar(35),
usuario_admin boolean,
pontos bigint,
qtd_livros_lidos int,
qtd_resenhas int,
fk_tb_instituicao int,
livros_reservados int,
status_ativo boolean,
data_inativacao date,
foto varchar(20000000000),
foreign key (fk_tb_instituicao) references tbInstituicao(id)
);

create table tbBiblioteca (
id int primary key auto_increment,
qtd_livros int,
fk_tb_perfil_usuario int,
foreign key (fk_tb_perfil_usuario) references tbPerfilUsuario(id)
);

create table tbLivros (
id int primary key auto_increment,
titulo varchar(45),
descricao varchar(120),
autor varchar(45),
edicao varchar(45),
editora varchar(45),
linguagem varchar(45),
cor_etiqueta varchar(45),
info_prateleira varchar(45),
qtd_resenhas int,
qtd_reservas int,
qtd_disponiveis int,
qtd_estoque int,
fk_tb_biblioteca int,
foreign key (fk_tb_biblioteca) references tbBiblioteca(id)
);

create table tbCategoria (
id int primary key auto_increment,
nome varchar(70),
fk_tb_livros int,
foreign key (fk_tb_livros) references tbLivros(id)
);


create table tbHistorico (
id int primary key auto_increment,
nome_livro varchar(70),
tombo varchar(60),
nr_exemplar int,
acao varchar(15),
nome_aluno varchar(70),
data_registro date,
data_prev_devolucao date,
data_devolvido date,
fk_tb_exemplar int,
fk_tb_perfil_usuario int,
foreign key(fk_tb_exemplar) references tbExemplar(id),
foreign key(fk_tb_perfil_usuario) references tbPerfilUsuario(id)
);

create table tbExemplar(
	id int primary key auto_increment,
    nr_exemplar int,
    tombo varchar(60),
    reservado boolean,
    retirado boolean,
    devolvido boolean,
    fk_tb_livro int,
    foreign key(fk_tb_livro) references tbLivros(id)
);


create table tbResenha(
id int primary key auto_increment,
data_publicacao datetime,
conteudo_publicacao varchar(120),
fk_tb_livros int,
fk_tb_perfil_usuario int,
foreign key (fk_tb_livros) references tbLivros(id),
foreign key (fk_tb_perfil_usuario) references tbPerfilUsuario(id)
);


-- select * from tbInstituicao;

-- insert into tbInstituicao(tbInstituicao_razaoSocial, tbInstituicao_cep, tbInstituicao_email, tbInstituicao_telefone) values
-- ("Empresa do Dudu", "09836444", "empresaDoDudu@gmail.com.br", "11400289220");

-- select * from tbPerfilUsuario;

-- insert into tbPerfilUsuario(tbPerfilUsuario_nome, tbPerfilUsuario_cpf, tbPerfilUsuario_email, tbPerfilUsuario_telefone,
-- tbPerfilUsuario_senha, tbPerfilUsuario_admin, tbPerfilUsuario_pontos, tbPerfilUsuario_qtdLivrosLidos,
-- tbPerfilUsuario_qtdResenhas, fk_tbPerfilUsuario_tbInstituicao) values
-- ("Hanan Ortiz", "11092878899" , "hanan.ortiz@email.com", "11978236659", "123456", true, 12455, 4, 10, 1);

-- update tbPerfilUsuario set tbPerfilUsuario_admin = 1 where fk_tbPerfilUsuario_tbInstituicao = 1;


