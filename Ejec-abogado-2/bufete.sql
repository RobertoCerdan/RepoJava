-- phpMyAdmin SQL Dump
-- version 4.7.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 07-04-2021 a las 19:21:19
-- Versión del servidor: 5.6.34
-- Versión de PHP: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bufete`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abogados`
--

CREATE TABLE `abogados` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(20) NOT NULL,
  `dir` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `abogados`
--

INSERT INTO `abogados` (`dni`, `nombre`, `apellidos`, `dir`) VALUES
('234567p', 'mario', 'jimenez', 'calle 14'),
('58007738j', 'roberto', 'ccerdan', 'juntas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `dni` varchar(20) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`dni`, `nombre`, `apellidos`, `email`) VALUES
('2456789', 'manolo', 'garcia', 'juntas generales');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juicios`
--

CREATE TABLE `juicios` (
  `id` varchar(20) NOT NULL,
  `cliente` varchar(20) NOT NULL,
  `fechainicio` date NOT NULL,
  `fechafin` date NOT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juiciosabogados`
--

CREATE TABLE `juiciosabogados` (
  `id` varchar(20) NOT NULL,
  `dni` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juiciosclientes`
--

CREATE TABLE `juiciosclientes` (
  `id` varchar(20) NOT NULL,
  `dni` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abogados`
--
ALTER TABLE `abogados`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `juicios`
--
ALTER TABLE `juicios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente` (`cliente`);

--
-- Indices de la tabla `juiciosabogados`
--
ALTER TABLE `juiciosabogados`
  ADD PRIMARY KEY (`id`,`dni`),
  ADD KEY `juiciosabogados_ibfk_1` (`dni`);

--
-- Indices de la tabla `juiciosclientes`
--
ALTER TABLE `juiciosclientes`
  ADD PRIMARY KEY (`id`,`dni`),
  ADD KEY `dni` (`dni`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `juicios`
--
ALTER TABLE `juicios`
  ADD CONSTRAINT `juicios_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `juiciosabogados`
--
ALTER TABLE `juiciosabogados`
  ADD CONSTRAINT `juiciosabogados_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `abogados` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `juiciosabogados_ibfk_2` FOREIGN KEY (`id`) REFERENCES `juicios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `juiciosclientes`
--
ALTER TABLE `juiciosclientes`
  ADD CONSTRAINT `juiciosclientes_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `clientes` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `juiciosclientes_ibfk_2` FOREIGN KEY (`id`) REFERENCES `juicios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
