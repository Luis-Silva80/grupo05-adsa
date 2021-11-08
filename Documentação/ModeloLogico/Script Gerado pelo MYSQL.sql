CREATE SCHEMA IF NOT EXISTS `mydb`;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`tbInstituicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbInstituicao` (
  `tbInstituicao_id` INT NOT NULL,
  `tbInstituicao_razaoSocial` VARCHAR(45) NULL,
  `tbInstituicao_cep` VARCHAR(45) NULL,
  `tbInstituicao_email` VARCHAR(45) NULL,
  `tbInstituicao_telefone` VARCHAR(45) NULL,
  PRIMARY KEY (`tbInstituicao_id`)); 
-- -----------------------------------------------------
-- Table `mydb`.`tbPerfilUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbPerfilUsuario` (
  `tbPerfilUsuario_id` INT NOT NULL,
  `tbPerfilUsuario_nome` VARCHAR(45) NULL,
  `tbPerfilUsuario_cpf` VARCHAR(45) NULL,
  `tbPerfilUsuario_email` VARCHAR(45) NULL,
  `tbPerfilUsuario_telefone` VARCHAR(45) NULL,
  `tbPerfilUsuariocol_senha` VARCHAR(45) NULL,
  `tbPerfilUsuario_admin` BIT NULL,
  `tbPerfilUsuario_pontos` VARCHAR(45) NULL,
  `tbPerfilUsuario_qtdLivrosLidos` VARCHAR(45) NULL,
  `tbPerfilUsuario_qtdResenhas` VARCHAR(45) NULL,
  `tbInstituicao_tbInstituicao_id` INT NOT NULL,
  PRIMARY KEY (`tbPerfilUsuario_id`),
  INDEX `fk_tbPerfilUsuario_tbInstituicao1_idx` (`tbInstituicao_tbInstituicao_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbPerfilUsuario_tbInstituicao1`
    FOREIGN KEY (`tbInstituicao_tbInstituicao_id`)
    REFERENCES `mydb`.`tbInstituicao` (`tbInstituicao_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`tbBiblioteca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbBiblioteca` (
  `tbBiblioteca_id` INT NOT NULL,
  `tbBiblioteca_qtdLivros` VARCHAR(45) NULL,
  `tbPerfilUsuario_tbPerfilUsuario_id` INT NOT NULL,
  PRIMARY KEY (`tbBiblioteca_id`),
  INDEX `fk_tbBiblioteca_tbPerfilUsuario1_idx` (`tbPerfilUsuario_tbPerfilUsuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbBiblioteca_tbPerfilUsuario1`
    FOREIGN KEY (`tbPerfilUsuario_tbPerfilUsuario_id`)
    REFERENCES `mydb`.`tbPerfilUsuario` (`tbPerfilUsuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`tbCurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbCurso` (
  `tbCurso_id` INT NOT NULL,
  `tbCurso_nome` VARCHAR(45) NULL,
  `tbBiblioteca_tbBiblioteca_id` INT NOT NULL,
  PRIMARY KEY (`tbCurso_id`),
  INDEX `fk_tbCurso_tbBiblioteca1_idx` (`tbBiblioteca_tbBiblioteca_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbCurso_tbBiblioteca1`
    FOREIGN KEY (`tbBiblioteca_tbBiblioteca_id`)
    REFERENCES `mydb`.`tbBiblioteca` (`tbBiblioteca_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`tbLivros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbLivros` (
  `tbLivros_id` INT NOT NULL,
  `tbLivros_titulo` VARCHAR(45) NULL,
  `tbLivros_descricao` VARCHAR(45) NULL,
  `tbLivros_autor` VARCHAR(45) NULL,
  `tbLivros_edicao` VARCHAR(45) NULL,
  `tbLivros_editora` VARCHAR(45) NULL,
  `tbLivros_status` VARCHAR(45) NULL,
  `tbCurso_tbCurso_id` INT NOT NULL,
  `tbLivros_qtdResenhas` VARCHAR(45) NULL,
  `tbLivros_qtdReservas` VARCHAR(45) NULL,
  `tbBiblioteca_tbBiblioteca_id` INT NOT NULL,
  PRIMARY KEY (`tbLivros_id`),
  INDEX `fk_tbLivros_tbCurso1_idx` (`tbCurso_tbCurso_id` ASC) VISIBLE,
  INDEX `fk_tbLivros_tbBiblioteca1_idx` (`tbBiblioteca_tbBiblioteca_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbLivros_tbCurso1`
    FOREIGN KEY (`tbCurso_tbCurso_id`)
    REFERENCES `mydb`.`tbCurso` (`tbCurso_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbLivros_tbBiblioteca1`
    FOREIGN KEY (`tbBiblioteca_tbBiblioteca_id`)
    REFERENCES `mydb`.`tbBiblioteca` (`tbBiblioteca_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `mydb`.`tbResenha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbResenha` (
  `fk_tbResenha_tbLivros` INT NOT NULL,
  `fk_tbResenha_tbPerfilUsuario` INT NOT NULL,
  `tbResenha_dataPublicacao` DATETIME NULL,
  `tbResenha_conteudoPublicacao` VARCHAR(120) NULL,
  PRIMARY KEY (`fk_tbResenha_tbLivros`, `fk_tbResenha_tbPerfilUsuario`),
  INDEX `fk_tbLivros_has_tbPerfilUsuario_tbPerfilUsuario1_idx` (`fk_tbResenha_tbPerfilUsuario` ASC) VISIBLE,
  INDEX `fk_tbLivros_has_tbPerfilUsuario_tbLivros_idx` (`fk_tbResenha_tbLivros` ASC) VISIBLE,
  CONSTRAINT `fk_tbLivros_has_tbPerfilUsuario_tbLivros`
    FOREIGN KEY (`fk_tbResenha_tbLivros`)
    REFERENCES `mydb`.`tbLivros` (`tbLivros_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbLivros_has_tbPerfilUsuario_tbPerfilUsuario1`
    FOREIGN KEY (`fk_tbResenha_tbPerfilUsuario`)
    REFERENCES `mydb`.`tbPerfilUsuario` (`tbPerfilUsuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `mydb`.`tbCategoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbCategoria` (
  `tbCategoria_id` INT NOT NULL,
  `tbCategoria_nome` VARCHAR(45) NULL,
  `tbLivros_tbLivros_id` INT NOT NULL,
  PRIMARY KEY (`tbCategoria_id`),
  INDEX `fk_tbCategoria_tbLivros1_idx` (`tbLivros_tbLivros_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbCategoria_tbLivros1`
    FOREIGN KEY (`tbLivros_tbLivros_id`)
    REFERENCES `mydb`.`tbLivros` (`tbLivros_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `mydb`.`tbHistorico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbHistorico` (
  `fk_tbHistorico_tbLivros` INT NOT NULL,
  `fk_tbHistorico_tbPerfilUsuario` INT NOT NULL,
  `tbHistorico_nomeLivro` VARCHAR(45) NULL,
  `tbHistorico_nomeAluno` VARCHAR(45) NULL,
  `tbHistorico_data` VARCHAR(45) NULL,
  `tbHistorico_acao` VARCHAR(45) NULL,
  PRIMARY KEY (`fk_tbHistorico_tbLivros`, `fk_tbHistorico_tbPerfilUsuario`),
  INDEX `fk_tbLivros_has_tbPerfilUsuario_tbPerfilUsuario2_idx` (`fk_tbHistorico_tbPerfilUsuario` ASC) VISIBLE,
  INDEX `fk_tbLivros_has_tbPerfilUsuario_tbLivros1_idx` (`fk_tbHistorico_tbLivros` ASC) VISIBLE,
  CONSTRAINT `fk_tbLivros_has_tbPerfilUsuario_tbLivros1`
    FOREIGN KEY (`fk_tbHistorico_tbLivros`)
    REFERENCES `mydb`.`tbLivros` (`tbLivros_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbLivros_has_tbPerfilUsuario_tbPerfilUsuario2`
    FOREIGN KEY (`fk_tbHistorico_tbPerfilUsuario`)
    REFERENCES `mydb`.`tbPerfilUsuario` (`tbPerfilUsuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
