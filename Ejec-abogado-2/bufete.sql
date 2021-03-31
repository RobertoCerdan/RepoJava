-- phpMyAdmin SQL Dump
-- version 4.7.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 31-03-2021 a las 09:36:36
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
  `dni` varchar(20) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(40) NOT NULL,
  `dir` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `abogados`
--

INSERT INTO `abogados` (`dni`, `nombre`, `apellidos`, `dir`) VALUES
('58007738j', 'rober', 'cerdan', 'calle de la piruleta'),
('pep', 'pe', '3', '3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `dni` varchar(20) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`dni`, `nombre`, `apellidos`, `email`) VALUES
('1', '1', '1', '1'),
('23', '1', '1', '1'),
('44553322k', 'roberto', 'cerdan', 'pepe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juicios`
--

CREATE TABLE `juicios` (
  `id` varchar(20) NOT NULL,
  `cliente` varchar(20) NOT NULL,
  `fechainicio` date NOT NULL,
  `fechafin` date DEFAULT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `juicios`
--

INSERT INTO `juicios` (`id`, `cliente`, `fechainicio`, `fechafin`, `estado`) VALUES
('123', '23', '2021-03-14', NULL, 'TRAMITE'),
('1234567', '44553322k', '2021-03-13', NULL, 'TRAMITE'),
('222', '23', '2021-03-19', NULL, 'TRAMITE'),
('234', '222', '2020-03-23', '2020-03-23', 'TRAMITE'),
('345', '23', '2021-03-19', NULL, 'TRAMITE'),
('34567', '23', '2021-03-18', '2021-03-18', 'TRAMITE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juiciosabogados`
--

CREATE TABLE `juiciosabogados` (
  `dni` varchar(20) NOT NULL,
  `id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `juiciosabogados`
--

INSERT INTO `juiciosabogados` (`dni`, `id`) VALUES
('58007738j', '1234567'),
('58007738j', '222'),
('pep', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juiciosclientes`
--

CREATE TABLE `juiciosclientes` (
  `dni` varchar(20) NOT NULL,
  `id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abogados`
--
ALTER TABLE `abogados`
  ADD PRIMARY KEY (`dni`);

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
  ADD PRIMARY KEY (`dni`,`id`);

--
-- Indices de la tabla `juiciosclientes`
--
ALTER TABLE `juiciosclientes`
  ADD PRIMARY KEY (`dni`,`id`),
  ADD KEY `id` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
