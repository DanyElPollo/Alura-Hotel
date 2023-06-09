-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.30 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para hotel_alura
DROP DATABASE IF EXISTS `hotel_alura`;
CREATE DATABASE IF NOT EXISTS `hotel_alura` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel_alura`;

-- Volcando estructura para tabla hotel_alura.huespedes
DROP TABLE IF EXISTS `huespedes`;
CREATE TABLE IF NOT EXISTS `huespedes` (
  `hues_id` int unsigned NOT NULL AUTO_INCREMENT,
  `hues_nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `hues_apellido` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `hues_telefono` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `hues_fechaNacimiento` date DEFAULT NULL,
  `hues_nacionalidad` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hues_idReserva` int unsigned NOT NULL,
  PRIMARY KEY (`hues_id`),
  KEY `FK_huespedes_reservas` (`hues_idReserva`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla hotel_alura.huespedes: ~3 rows (aproximadamente)
INSERT INTO `huespedes` (`hues_id`, `hues_nombre`, `hues_apellido`, `hues_telefono`, `hues_fechaNacimiento`, `hues_nacionalidad`, `hues_idReserva`) VALUES
	(12, 'Daniel Andres', 'Arenas', '3108396076', '2023-05-24', 'colombiano-colombiana', 36),
	(15, 'Claudia', 'Morales', '3145738004', '1977-02-27', 'colombiano-colombiana', 39);

-- Volcando estructura para tabla hotel_alura.reservas
DROP TABLE IF EXISTS `reservas`;
CREATE TABLE IF NOT EXISTS `reservas` (
  `rese_id` int unsigned NOT NULL AUTO_INCREMENT,
  `rese_fechaEntrada` date NOT NULL,
  `rese_fechaSalida` date NOT NULL,
  `rese_valor` int unsigned NOT NULL DEFAULT '0',
  `rese_medioPago` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`rese_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla hotel_alura.reservas: ~3 rows (aproximadamente)
INSERT INTO `reservas` (`rese_id`, `rese_fechaEntrada`, `rese_fechaSalida`, `rese_valor`, `rese_medioPago`) VALUES
	(36, '2023-05-18', '2023-05-19', 20000, 'Tarjeta de Débito'),
	(39, '2023-06-07', '2023-06-23', 160000, 'Tarjeta de Débito');

-- Volcando estructura para tabla hotel_alura.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `user_dni` int unsigned NOT NULL,
  `user_nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_apellido` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_telefono` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_correo` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_contrasena` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_dni`),
  UNIQUE KEY `user_correo` (`user_correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla hotel_alura.usuarios: ~3 rows (aproximadamente)
INSERT INTO `usuarios` (`user_dni`, `user_nombre`, `user_apellido`, `user_telefono`, `user_correo`, `user_contrasena`) VALUES
	(1003071623, 'Daniel', 'Arenas', '3145738004', 'arenas', 'admin'),
	(1003071624, 'Daniel', 'Arenas', '3108396076', 'Elpollo@gmail.com', '51f6d7386f81ed05f851a3c5467bb3549299f84298f71a37bf5bed3dcb9d14c1'),
	(1007763505, 'Juan', 'Arenas', '3108396076', 'dan15ar@hotmail.com', 'Juancho');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
