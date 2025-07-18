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
-- Table `codecut_DB`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(85) NOT NULL,
  `Apellido` VARCHAR(85) NOT NULL,
  `Correo` VARCHAR(80) NOT NULL,
  `Contrasena` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE,
  UNIQUE INDEX `Correo_UNIQUE` (`Correo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`Productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Productos` (
  `idProductos` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `precio` DECIMAL NOT NULL,
  `descripcion` VARCHAR(200) NOT NULL,
  `stock` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idProductos`),
  UNIQUE INDEX `idProductos_UNIQUE` (`idProductos` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`Servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Servicios` (
  `idServicios` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(200) NOT NULL,
  `precio` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idServicios`),
  UNIQUE INDEX `idServicios_UNIQUE` (`idServicios` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Empleado` (
  `idEmpleado` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(150) NOT NULL,
  `apellido` VARCHAR(100) NOT NULL,
  `edad` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  UNIQUE INDEX `idEmpleado_UNIQUE` (`idEmpleado` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `direccion` VARCHAR(300) NOT NULL,
  `montoTotal` DECIMAL NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE INDEX `idPedido_UNIQUE` (`idPedido` ASC) VISIBLE,
  INDEX `fk_Pedido_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `codecut_DB`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Pago` (
  `idPago` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `MetodoPago` INT UNSIGNED NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  `Pedido_idPedido` INT NOT NULL,
  PRIMARY KEY (`idPago`, `Pedido_idPedido`),
  UNIQUE INDEX `idPago_UNIQUE` (`idPago` ASC) VISIBLE,
  INDEX `fk_Pago_Pedido1_idx` (`Pedido_idPedido` ASC) VISIBLE,
  CONSTRAINT `fk_Pago_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `codecut_DB`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`Cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Cita` (
  `idCita` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `FechaHora` DATETIME NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idCita`),
  UNIQUE INDEX `idCita_UNIQUE` (`idCita` ASC) VISIBLE,
  INDEX `fk_Cita_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Cita_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `codecut_DB`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`DetallePedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`DetallePedido` (
  `idDetallePedido` INT NOT NULL AUTO_INCREMENT,
  `cantidadProducto` INT UNSIGNED NOT NULL,
  `Pedido_idPedido` INT NOT NULL,
  `Productos_idProductos` INT NOT NULL,
  PRIMARY KEY (`idDetallePedido`, `Pedido_idPedido`, `Productos_idProductos`),
  UNIQUE INDEX `idDetallePedido_UNIQUE` (`idDetallePedido` ASC) VISIBLE,
  INDEX `fk_DetallePedido_Pedido_idx` (`Pedido_idPedido` ASC) VISIBLE,
  INDEX `fk_DetallePedido_Productos1_idx` (`Productos_idProductos` ASC) VISIBLE,
  CONSTRAINT `fk_DetallePedido_Pedido`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `codecut_DB`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetallePedido_Productos1`
    FOREIGN KEY (`Productos_idProductos`)
    REFERENCES `codecut_DB`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codecut_DB`.`Servicios_has_Cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `codecut_DB`.`Servicios_has_Cita` (
  `Servicios_idServicios` INT NOT NULL,
  `Cita_idCita` INT UNSIGNED NOT NULL,
  `Empleado_idEmpleado` INT NOT NULL,
  PRIMARY KEY (`Servicios_idServicios`, `Cita_idCita`, `Empleado_idEmpleado`),
  INDEX `fk_Servicios_has_Cita_Cita1_idx` (`Cita_idCita` ASC) VISIBLE,
  INDEX `fk_Servicios_has_Cita_Servicios1_idx` (`Servicios_idServicios` ASC) VISIBLE,
  INDEX `fk_Servicios_has_Cita_Empleado1_idx` (`Empleado_idEmpleado` ASC) VISIBLE,
  CONSTRAINT `fk_Servicios_has_Cita_Servicios1`
    FOREIGN KEY (`Servicios_idServicios`)
    REFERENCES `codecut_DB`.`Servicios` (`idServicios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Servicios_has_Cita_Cita1`
    FOREIGN KEY (`Cita_idCita`)
    REFERENCES `codecut_DB`.`Cita` (`idCita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Servicios_has_Cita_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `codecut_DB`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
