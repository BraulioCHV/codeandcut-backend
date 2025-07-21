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
  `precio` DECIMAL UNSIGNED NOT NULL,
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
  `montoTotal` DECIMAL UNSIGNED NOT NULL,
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

INSERT INTO Usuario (Nombre, Apellido, Correo, Contrasena) -- Usuarios
VALUES ('Ellen', 'Hernandez', 'ellenjarely.ht@gmail.com','contrasena1'),
('Sarai', 'Celeste', 'saraimontes170@gmail.com', 'contrasena2'),
('Fernanda', 'Rivas', 'ferrivas@gmail.com','contrasena3'),
('Angel', 'Molinero', 'angelmolinero8@gmail.com', 'contrasena4'),
('Alfonso', 'Esau', 'alfonsoesau@gmail.com', 'contrasena5'),
('Francisco', 'Gutierrez', 'franciscogtz@gmail.com', 'contrasena6'),
('Orlando', 'Chavez', 'chavez@gmail.com', 'contrasena7'),
('Yoandry', 'Caballero', 'yoandryc@gmail.com', 'contrasena8');

SELECT * FROM Usuario; -- Seleccionamos toda la tabla usuarios

INSERT INTO Empleado (nombre, apellido, edad) -- Empleados
VALUES ('Bryan','Chavez',18),
('Britany','Lopez',25),
('Vicente','Fernandez',30),
('Juan','Hernandez',28),
('Laura','Jimenez',22);

SELECT * FROM Empleado; -- Seleccionamos tod la tabla empleados

INSERT INTO productos (nombre, precio, descripcion, stock) VALUES -- Productos
('Acondicionador', 63.00, 'Acondicionador con recubrimiento contra rayos ultravioleta', 20),
('Balsamo de barba', 200.00, 'Estimulador de vello facial', 63),
('Cera', 40.00, 'Producto reparador de cabello y barba maltratado', 65),
('Crema para peinar', 90.00, 'Crema hidratante y desata nudos para niños', 35),
('Gel', 60.00, 'Poderoso fijador de pelo 48hrs', 50),
('Navajas rasuradoras', 100.00, 'Navajas de alta precisión para delineados perfectos', 40),
('Peines', 68.00, 'Peine de plástico con recubrimiento antibacteriano', 33),
('Tinte negro', 250.00, 'Crema para teñir cabello de alta duración color negro', 84),
('Tinte pelirojo', 250.00, 'Crema para teñir cabello de alta duración color pelirojo', 74),
('Tinte rubio', 250.00, 'Crema para teñir cabello de alta duración color rubio', 59),
('Tratamiento anticaida', 74.00, 'Tónico natural para fortalecimiento de folículos capilares', 49),
('Vaselina', 84.00, 'Vaselina como la que usan en la película!', 20);

SELECT * FROM productos; -- Seleccionamos toda la tabla productos

INSERT INTO servicios (nombre, descripcion, precio) VALUES -- servicios
('Afeitado del robot', 'Afeitado con máquina doble cero y shaver', 80.00),
('Afeitado full', 'Afeitado con toalla caliente, vaporizador, espuma o gel para afeitar', 130.00),
('Barba', 'Diseño y delineado de barba con gel para afeitar y navaja', 110.00),
('Bigote', 'Arreglo de bigote con navaja y gel para afeitar', 50.00),
('Corte adulto', 'Diseño, corte, delineado con navaja/shaver y peinado', 160.00),
('Corte niño', 'Niños menores de 8 años', 130.00),
('Mascarilla negra', 'Mascarilla de carbón activado', 40.00),
('Masaje facial', 'Exfoliante relajante, vaporizador, mascarilla de puntos negros, masaje hombros y espalda, emulsión de argán con rodillo de jade, mascarilla de hoja de arroz', 210.00),
('Paquete 1. Corte y barba', 'Corte adulto + Queremos hacerte la barba', 280.00),
('Paquete 2. Corte y facial', 'Masaje facial y corte adulto', 300.00),
('Perfilado de ceja', 'Diseño de barba quitado exceso de ceja', 40.00),
('Planchado de ceja', 'Laminado de ceja con alaciante', 60.00),
('Queremos hacerte la barba', 'Diseño, toalla caliente, limpieza con espuma, delineado con máquina de percusión y bálsamo', 160.00),
('Servicio VIP', 'Corte + Queremos hacerte la barba + facial ceja', 430.00);

SELECT * FROM servicios; -- Seleccionamos toda la tabla servicios

INSERT INTO pedido (direccion, montoTotal, Usuario_idUsuario) VALUES -- pedidos
('Manuel de Falla',63.00,1),
('Av 16 de Septiembre', 150.00,2),
('Miguel Bernal',250.00,3),
('Lopez Mateos',300.00,4),
('Quinta Avenida', 180.00,5);

SELECT * FROM pedido; -- seleccionamos toda la tabla pedidos

INSERT INTO DetallePedido(cantidadProducto,Pedido_idPedido,Productos_idProductos) VALUES -- Detalle pedidos
(1,1,1),
(2,2,2),
(5,3,3),
(3,4,4),
(8,5,5);

SELECT * FROM detallepedido; -- seleccionamos toda la tabla detalle Pedido

insert INTO Cita(FechaHora, Status, Usuario_idUsuario) VALUES -- cita
('2025-11-10 10:35:15','Agendado',1),
('2025-06-10 11:35:15','Hecho',2),
('2025-08-10 12:35:15','Agendado',3),
('2025-10-10 09:35:15','Cancelado',4),
('2025-05-10 10:35:15','Agendado',5);

select * FROM Cita; -- seleccionamos toda la tabla cita

select * FROM servicios_has_cita; -- seleccionamos toda la tabla pivote servicios_has_cita

INSERT INTO Pago(MetodoPago, Status, Pedido_idPedido) VALUES -- Pago
(1,'Tarjeta Credito',1),
(2,'Tarjeta Debito',2),
(1,'Tarjeta Credito',3),
(2,'Tarjeta Debito',4),
(1,'Tarjeta Credito',5);

SELECT * FROM pago; -- seleccionamos toda la tabla pago
