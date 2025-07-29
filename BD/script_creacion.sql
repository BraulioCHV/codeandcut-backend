-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema codecut_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema codecut_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `codecut_DB` ;
USE `codecut_DB` ;

-- -----------------------------------------------------
-- Table `codecut_DB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(85) NOT NULL,
  `lastName` VARCHAR(85) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUser` ASC) VISIBLE,
  UNIQUE INDEX `Correo_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`products` (
  `idProducts` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DECIMAL NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `stock` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idProducts`),
  UNIQUE INDEX `idProductos_UNIQUE` (`idProducts` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`services` (
  `idServices` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `price` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idServices`),
  UNIQUE INDEX `idServicios_UNIQUE` (`idServices` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`employee` (
  `idEmployee` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `age` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE INDEX `idEmpleado_UNIQUE` (`idEmployee` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`order` (
  `idOrder` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(300) NOT NULL,
  `totalAmount` DECIMAL NOT NULL,
  `user_idUser` INT NOT NULL,
  PRIMARY KEY (`idOrder`),
  UNIQUE INDEX `idPedido_UNIQUE` (`idOrder` ASC) VISIBLE,
  INDEX `fk_order_user1_idx` (`user_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_idUser`)
    REFERENCES `codecut_DB`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`payment` (
  `idPayment` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `typePayment` INT UNSIGNED NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `order_idOrder` INT NOT NULL,
  PRIMARY KEY (`idPayment`, `order_idOrder`),
  UNIQUE INDEX `idPago_UNIQUE` (`idPayment` ASC) VISIBLE,
  INDEX `fk_payment_order1_idx` (`order_idOrder` ASC) VISIBLE,
  CONSTRAINT `fk_payment_order1`
    FOREIGN KEY (`order_idOrder`)
    REFERENCES `codecut_DB`.`order` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`appointment` (
  `idAppointment` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `DateHour` DATETIME NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  `user_idUser` INT NOT NULL,
  PRIMARY KEY (`idAppointment`),
  UNIQUE INDEX `idCita_UNIQUE` (`idAppointment` ASC) VISIBLE,
  INDEX `fk_appointment_user1_idx` (`user_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_appointment_user1`
    FOREIGN KEY (`user_idUser`)
    REFERENCES `codecut_DB`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`detailsOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`detailsOrder` (
  `idDetailsOrder` INT NOT NULL AUTO_INCREMENT,
  `qtyProduct` INT UNSIGNED NOT NULL,
  `products_idProducts` INT NOT NULL,
  `order_idOrder` INT NOT NULL,
  PRIMARY KEY (`idDetailsOrder`, `products_idProducts`, `order_idOrder`),
  UNIQUE INDEX `idDetallePedido_UNIQUE` (`idDetailsOrder` ASC) VISIBLE,
  INDEX `fk_detailsOrder_products1_idx` (`products_idProducts` ASC) VISIBLE,
  INDEX `fk_detailsOrder_order1_idx` (`order_idOrder` ASC) VISIBLE,
  CONSTRAINT `fk_detailsOrder_products1`
    FOREIGN KEY (`products_idProducts`)
    REFERENCES `codecut_DB`.`products` (`idProducts`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detailsOrder_order1`
    FOREIGN KEY (`order_idOrder`)
    REFERENCES `codecut_DB`.`order` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`appointment_has_services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`appointment_has_services` (
  `appointment_idAppointment` INT UNSIGNED NOT NULL,
  `services_idServices` INT NOT NULL,
  `employee_idEmployee` INT NOT NULL,
  PRIMARY KEY (`appointment_idAppointment`, `services_idServices`, `employee_idEmployee`),
  INDEX `fk_appointment_has_services_services1_idx` (`services_idServices` ASC) VISIBLE,
  INDEX `fk_appointment_has_services_appointment1_idx` (`appointment_idAppointment` ASC) VISIBLE,
  INDEX `fk_appointment_has_services_employee1_idx` (`employee_idEmployee` ASC) VISIBLE,
  CONSTRAINT `fk_appointment_has_services_appointment1`
    FOREIGN KEY (`appointment_idAppointment`)
    REFERENCES `codecut_DB`.`appointment` (`idAppointment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_has_services_services1`
    FOREIGN KEY (`services_idServices`)
    REFERENCES `codecut_DB`.`services` (`idServices`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_has_services_employee1`
    FOREIGN KEY (`employee_idEmployee`)
    REFERENCES `codecut_DB`.`employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- RENAME TABLE `order` TO `orders`;
DESCRIBE orders;