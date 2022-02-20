-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 20-02-2022 a las 17:45:13
-- Versión del servidor: 5.7.36
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empleados`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) COLLATE utf32_spanish_ci NOT NULL,
  `dui` varchar(60) COLLATE utf32_spanish_ci NOT NULL,
  `nit` varchar(60) COLLATE utf32_spanish_ci NOT NULL,
  `sueldoBase` float NOT NULL,
  `sueldoFinal` float NOT NULL,
  `tipo` varchar(60) COLLATE utf32_spanish_ci NOT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`idEmpleado`, `nombre`, `dui`, `nit`, `sueldoBase`, `sueldoFinal`, `tipo`) VALUES
(2, 'diego', '121212', '520', 10, 22, 'programador'),
(3, 'da', 'dsa', 'asd', 600, 467.22, 'das'),
(4, 'gabriel', '522-552-66', '666-441', 800, 3656, 'programador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

DROP TABLE IF EXISTS `tipos`;
CREATE TABLE IF NOT EXISTS `tipos` (
  `idTipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombreTipo` varchar(60) COLLATE utf32_spanish_ci NOT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`idTipo`, `nombreTipo`) VALUES
(1, 'Admin'),
(2, 'Analista Programador');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
